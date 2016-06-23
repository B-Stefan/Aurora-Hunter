package de.hs_bremen.aurora_hunter.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.commons.notifications.models.Notification;
import de.hs_bremen.aurora_hunter.exceptions.NotificationThresholdException;
import de.hs_bremen.aurora_hunter.helpers.permissions.PermissionManager;
import de.hs_bremen.aurora_hunter.notifications.NotificationManager;
import de.hs_bremen.aurora_hunter.ui.fragments.NotificationThresholdFragment;

public class NotificationActivity extends AppCompatActivity {
    public static final String TAG = NotificationActivity.class.getSimpleName();

    private NotificationThresholdFragment mNotificationThresholdFragment;

    private NotificationManager mNotificationManager;

    private Response.ErrorListener errorListener  = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNotificationManager = NotificationManager.getInstance();

        FragmentManager fm = getSupportFragmentManager();

        mNotificationThresholdFragment = (NotificationThresholdFragment) getSupportFragmentManager().findFragmentByTag("mNotificationThresholdFragment");


        if(mNotificationThresholdFragment == null){
            mNotificationThresholdFragment = new NotificationThresholdFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frameLayout_notification_threshold_container, mNotificationThresholdFragment, "mNotificationThresholdFragment");
            ft.commit();
            fm.executePendingTransactions();
        }


        getLevelFromServer();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                try {

                    mNotificationManager.setNotificationLevel(mNotificationThresholdFragment.getThreshold(), new Response.Listener<Notification>() {
                        @Override
                        public void onResponse(Notification response) {
                            finish();
                        }
                    }, errorListener);
                }catch (NotificationThresholdException e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void getLevelFromServer(){
        mNotificationManager.getNotification(new Response.Listener<Notification>() {
            @Override
            public void onResponse(Notification response) {
                if(response != null && response.getThreshold() != null){
                    mNotificationThresholdFragment.setThreshold(response.getThreshold());
                }else {
                    mNotificationThresholdFragment.setThreshold(0.2);
                }

            }
        }, errorListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        PermissionManager.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onStart() {
        super.onStart();
        PermissionManager.getInstance().ensureAllPermissions(this,null);

    }


}
