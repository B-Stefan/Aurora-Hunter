package de.hs_bremen.aurora_hunter.notifications;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class GooglePlayServiceUtils {
    private static final String TAG = GooglePlayServiceUtils.class.getSimpleName();

    public static void startGMCTokenRegisterService(Activity activity){
        if (checkPlayServices(activity)) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(activity, RegistrationIntentService.class);
            activity.startService(intent);
        }
    }

    private static boolean checkPlayServices(Activity activity) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();

        int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE)
                        .show();
            } else {
                //Log.i(TAG, "This device is not supported.");
                activity.finish();
            }
            return false;
        }
        return true;
    }

}

