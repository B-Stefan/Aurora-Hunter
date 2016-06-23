package de.hs_bremen.aurora_hunter.notifications.locationUpdate;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.CustomEvent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import de.hs_bremen.aurora_hunter.notifications.NotificationManager;
import io.fabric.sdk.android.Fabric;

public class LocationService extends Service{

    private final String TAG = "LocationService";

    private android.location.LocationManager locationManager;
    // minimum time interval between location updates (in milliseconds).
    private GoogleApiClient mGoogleApiClient;

    private OnLocationUpdateClass monLocationUpdateListener;

    private Geocoder mGeocoder;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final GoogleApiConnectionClass callbacks = new GoogleApiConnectionClass();
        Fabric.with(this, new Crashlytics());

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(callbacks)
                .addOnConnectionFailedListener(callbacks)
                .addApi(LocationServices.API)
                .build();
        // Code to execute when the service is first created
        //Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGoogleApiClient.disconnect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        super.onStartCommand(intent,flags,startid);

        monLocationUpdateListener = new OnLocationUpdateClass();
        mGeocoder = new Geocoder(this, Locale.getDefault());
        this.mGoogleApiClient.connect();
        //Log.i(TAG, "started");
        return START_STICKY;
    }

    private class OnLocationUpdateClass implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            //Log.i(TAG, "location changed  send to server and broadcast with intent");
            NotificationManager.getInstance().updateUserPosition(location);

            String cityName;

            try {
                Address address = mGeocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1).get(0);
                cityName = address.getLocality();
            }catch (IOException e){
                cityName = e.getMessage();
            }
            Intent intent = new Intent("location-update");
            intent.putExtra("lat", location.getLatitude());
            intent.putExtra("lng", location.getLongitude());
            intent.putExtra("city",cityName);

            LocalBroadcastManager.getInstance(LocationService.this).sendBroadcast(intent);
        }
    }
    private class GoogleApiConnectionClass implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

        @Override
        public void onConnected(Bundle bundle) {


            final Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if(location != null){
                final AsyncTask<Object,Object,Object> task = new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object... objects) {
                        monLocationUpdateListener.onLocationChanged(location);
                        return null;
                    }
                };
                task.execute();
            }else {

                final LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);
                //Log.i("test", "no location found" + locationAvailability.toString());
            }

            LocationRequest locationRequest = new LocationRequest();
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, locationRequest,monLocationUpdateListener);
        }

        @Override
        public void onConnectionSuspended(int i) {
            Answers.getInstance().logCustom(new CustomEvent("Error")
                    .putCustomAttribute("name","Notification")
                    .putCustomAttribute("source","System-Interaction")
                    .putCustomAttribute("event","google-connection-suspended"));
        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            Answers.getInstance().logCustom(new CustomEvent("Error")
                    .putCustomAttribute("name","Notification")
                    .putCustomAttribute("source","System-Interaction")
                    .putCustomAttribute("event","google-connection-failed"));
        }
    }

}
