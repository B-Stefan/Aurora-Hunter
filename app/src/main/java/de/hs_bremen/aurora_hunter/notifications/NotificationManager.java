package de.hs_bremen.aurora_hunter.notifications;

import android.location.Location;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;

import java.math.BigDecimal;

import de.hs_bremen.aurora_hunter.ApiManager;
import de.hs_bremen.aurora_hunter.commons.notifications.api.NotificationApi;
import de.hs_bremen.aurora_hunter.commons.notifications.models.GeoPoint;
import de.hs_bremen.aurora_hunter.commons.notifications.models.Notification;
import de.hs_bremen.aurora_hunter.exceptions.NotificationThresholdException;

/**
 * Class to manage the notifications
 */
public class NotificationManager {
    private static final String TAG = NotificationManager.class.getSimpleName();

    // Singleton
    private static final NotificationManager mInstance = new NotificationManager();

    private final NotificationApi mNotificationApi;

    private  int mId = 1;

    private double mCachedThreshold = -1;

    public static NotificationManager getInstance(){
        return mInstance;
    };

    private final Response.Listener<Notification> mResultListener = new Response.Listener<Notification>() {
        @Override
        public void onResponse(Notification response) {
            if(response != null){
                if(response.getThreshold() == null || response.getThreshold() == 0){
                    mCachedThreshold = 0.2;
                }else {

                    mCachedThreshold = response.getThreshold();
                }
            }
            Answers.getInstance().logContentView(new ContentViewEvent()
                    .putContentName("Notification")
                    .putContentType("System-Interaction")
                    .putContentId("notification-update-complete"));
        }
    };
    private final Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Answers.getInstance().logContentView(new ContentViewEvent()
                    .putContentName("Notification")
                    .putContentType("System-Interaction")
                    .putCustomAttribute("networkTime", error.getNetworkTimeMs())
                    .putContentId("notification-update-failed"));
        }
    };


    public NotificationManager(){
        super();
        this.mNotificationApi = ApiManager.getInstance().configure(new NotificationApi());
    }

    private void verifyThreshold(final double threshold) throws NotificationThresholdException{
        if(threshold > NotificationThresholdException.MAX_THRESHOLD || threshold < NotificationThresholdException.MIN_THRESHOLD ){
            throw new NotificationThresholdException(threshold);
        }
    }

    public void setNotificationLevel(final double threshold) throws NotificationThresholdException{
        setNotificationLevel(threshold, mResultListener, mErrorListener);
    }

    public void setNotificationLevel(final double threshold, final  Response.Listener<Notification> listenerResponse, final Response.ErrorListener listenerError ) throws NotificationThresholdException{
        this.verifyThreshold(threshold);
        mCachedThreshold = threshold;

        final Notification n = new Notification();
        n.setThreshold(threshold);
        n.setId((double) getId());
        mNotificationApi.notificationUpsert(n,listenerResponse,listenerError);
    }


    public void updateGCMToken(final String newToken){
        updateGCMToken(newToken, mResultListener,mErrorListener);
    }

    public void updateGCMToken(final String newToken,final  Response.Listener<Notification> listenerResponse,final Response.ErrorListener listenerError ){
        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName("Notification")
                .putContentType("System-Interaction")
                .putContentId("token-update"));

        final Notification n = new Notification();
        n.setToken(newToken);
        n.setId(Double.valueOf(getId()));
        mNotificationApi.notificationUpsert(n,listenerResponse,listenerError);
    }

    public void disableNotification(){
        throw new RuntimeException("NotificationManager.disableNotification() is not implemented yet ");
    }

    public void updateUserPosition(final Location location){
        updateUserPosition(location,mResultListener, mErrorListener);
    }

    public void updateUserPosition(final Location location,final  Response.Listener<Notification> listenerResponse,final Response.ErrorListener listenerError ){

        if(getId() == 0 ){
            listenerError.onErrorResponse(new VolleyError("getId is 0"));
            return;
        }
        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName("Notification")
                .putContentType("System-Interaction")
                .putCustomAttribute("lat", location.getLatitude())
                .putCustomAttribute("lng", location.getLongitude())
                .putContentId("position-update"));

        final GeoPoint geoLoc = new GeoPoint();
        geoLoc.setLat(BigDecimal.valueOf(location.getLatitude()));
        geoLoc.setLng(BigDecimal.valueOf(location.getLongitude()));
        final Notification n = new Notification();
        n.setLocation(geoLoc);
        n.setId(Double.valueOf(getId()));
        mNotificationApi.notificationUpsert(n,listenerResponse,listenerError);
     }

    public void getNotification(Response.Listener<Notification> listener, Response.ErrorListener errorListener){
        mNotificationApi.notificationFindById(String.valueOf(getId()),null, listener,errorListener);
    }
    public double getCachedNotification(){
        return mCachedThreshold;
    }
    public int getId() {
        return mId;
    }
    public void setId(int mId) {
        this.mId = mId;
    }
}
