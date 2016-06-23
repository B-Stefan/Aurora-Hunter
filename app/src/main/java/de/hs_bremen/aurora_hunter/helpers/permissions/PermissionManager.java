package de.hs_bremen.aurora_hunter.helpers.permissions;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;


public class PermissionManager {
    private static PermissionManager ourInstance = new PermissionManager();

    public static PermissionManager getInstance() {
        return ourInstance;
    }

    private static enum RESPONSE_CODES  {
        ACCESS_FINE_LOCATION,
        C2D_MESSAGE
    }

    public interface PermissionResultCallback {
        public void granted();
        public void denied();
    }

    private PermissionResultCallback callback = null;

    private PermissionManager() {

    }


    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if(PermissionUtil.verifyPermissions(grantResults)) {
            this.mOnPermissionGranted();
            // other 'case' lines to check for other
            // permissions this app might request
        }else {
            if(this.callback != null){
                callback.denied();
            }
        }
    }


    private void mOnPermissionGranted(){
        //Log.i(PermissionManager.class.getName(), "Permission granted ");
        if(this.callback != null){
            callback.granted();
        }

    }


    private void mEnsurePermissions(final  Activity activity, final String permission, final RESPONSE_CODES responseCode){

        final String[] permissions = {permission};
        this.mEnsurePermissions(activity, permissions, responseCode);
    }
    private void mEnsurePermissions(final  Activity activity, final String[] permissions, final RESPONSE_CODES responseCode){

        final int[] permissionsChecks = new int[permissions.length];

        //Use simple for loop because android don't support Java 8 lambada
        for(int i = 0 ; i < permissions.length; i++){
            permissionsChecks[i] =  ContextCompat.checkSelfPermission(activity,permissions[i]);
        }


        if (!PermissionUtil.verifyPermissions(permissionsChecks)) {
            activity.requestPermissions(permissions, responseCode.ordinal());
        }else {
            this.mOnPermissionGranted();
        }

    }

    public void ensureAllPermissions(final Activity activity, final PermissionResultCallback callback){

        this.callback = callback;
        final String[] permissions = {
                "com.google.android.c2dm.permission.RECEIVE",
                //"de.hs_bremen.aurorahunter.permission.C2D_MESSAGE",
                "android.permission.WAKE_LOCK",
               // "com.google.android.c2dm.permission.SEND",
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.ACCESS_NETWORK_STATE"
        };
        mEnsurePermissions(activity, permissions, RESPONSE_CODES.C2D_MESSAGE);


    }
}
