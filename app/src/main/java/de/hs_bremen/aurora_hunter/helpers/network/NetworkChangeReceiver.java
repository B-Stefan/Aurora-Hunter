package de.hs_bremen.aurora_hunter.helpers.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {


    public static final String INTENT_FILTER= "connection-update";
    public static final String EXTRA_IS_CONNECTED = "isConnected";
    public static final String EXTRA_STATUS = "status";

    @Override
    public void onReceive(final Context context, final Intent intent) {

        Intent outIntent = new Intent(INTENT_FILTER);
        outIntent.putExtra(EXTRA_STATUS,NetworkUtil.getConnectivityStatus(context));
        outIntent.putExtra(EXTRA_IS_CONNECTED,NetworkUtil.isConnected(context));
        context.sendBroadcast(outIntent);


    }
}