package de.hs_bremen.aurora_hunter.notifications;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.ui.activities.TabsActivity;

public class NotificationListenerService extends GcmListenerService {
    private static final String TAG = NotificationListenerService.class.getSimpleName();

    /**
     * Get called by Android GCM Service
     * First point were application is running.
     *
     * @param from sender id
     * @param data message and meta data
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "Received Message " + message + " from " + from);

        if (from.startsWith("/topics/")) {
            // a message for all users

            if(from.endsWith("maintenance")) {
                // for example in case of maintenance
                createMaintenanceNotification(message);
            }
        } else {
            // a private downstream message for the user
            createNotification(message);
        }
    }

    /**
     * Create a notification containing the received message.
     *
     * @param message GCM message
     */
    private void createNotification(String message) {
        Intent intent = new Intent(this, TabsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // If activity is already launched, bring this instance to the top

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Bitmap appIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(appIcon)
                .setSmallIcon(R.drawable.ic_aurora_notification_white_24dp)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0 , notificationBuilder.build());
    }

    /**
     * Create a public notification containing the received message.
     *
     * @param message GCM message
     */
    private void createMaintenanceNotification(String message) {
        Log.d(TAG, message);
    }
}