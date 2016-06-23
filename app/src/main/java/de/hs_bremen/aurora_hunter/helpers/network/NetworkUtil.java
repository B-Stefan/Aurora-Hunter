package de.hs_bremen.aurora_hunter.helpers.network;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    static enum CONNECTION_STATUS{
        CONNECTED,
        NOT_CONNECTED
    }

    public static CONNECTION_STATUS getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return CONNECTION_STATUS.CONNECTED;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return CONNECTION_STATUS.CONNECTED;
        }
        return CONNECTION_STATUS.NOT_CONNECTED;
    }
    public static boolean isConnected(Context context){
        return getConnectivityStatus(context) == CONNECTION_STATUS.CONNECTED;
    }


}