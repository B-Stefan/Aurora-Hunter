package de.hs_bremen.aurora_hunter.notifications;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

public class InstanceIDListenService extends  InstanceIDListenerService {
    private static final String TAG = InstanceIDListenService.class.getSimpleName();

    /**
     * Register again to backend when token change
     */
    @Override
    public void onTokenRefresh() {
        Log.d(TAG, "onTokenRefresh");

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}