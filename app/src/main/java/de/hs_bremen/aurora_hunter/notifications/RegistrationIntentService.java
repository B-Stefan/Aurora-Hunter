/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.hs_bremen.aurora_hunter.notifications;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import de.hs_bremen.aurora_hunter.R;
import io.fabric.sdk.android.Fabric;

public class RegistrationIntentService extends IntentService {
    private static final String TAG = RegistrationIntentService.class.getSimpleName();

    private static final String[] TOPICS = {"global"};

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Fabric.with(this, new Crashlytics());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            //Log.i(TAG, "GCM Registration Token: " + token);

            // send token to our backend to make it possible to send downstream messages with that token.
            NotificationManager.getInstance().updateGCMToken(token);

            // subscribe to topics
            GcmPubSub pubSub = GcmPubSub.getInstance(this);
            for (String topic : TOPICS) {
                pubSub.subscribe(token, "/topics/" + topic, null);
            }

            sharedPreferences.edit().putBoolean(getString(R.string.app_SENT_TOKEN_TO_SERVER), true).apply();
        } catch (Exception e) {
           // Log.d(TAG, "Failed to complete token refresh", e);
            sharedPreferences.edit().putBoolean(getString(R.string.app_SENT_TOKEN_TO_SERVER), false).apply();
        }

        Intent registrationComplete = new Intent(getString(R.string.app_REGISTRATION_COMPLETE));
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}