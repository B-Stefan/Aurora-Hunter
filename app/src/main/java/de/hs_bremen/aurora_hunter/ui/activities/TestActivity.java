package de.hs_bremen.aurora_hunter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import de.hs_bremen.aurora_hunter.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void startPurchaseNotificationsActivity(View view) {
        Intent intent = new Intent(this, PurchaseNotificationsActivity.class);
        startActivity(intent);
    }
    public void startAuroraNotificationActivity(View view) {
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }

    public void startTabsView(View view) {
        Intent intent = new Intent(this, TabsActivity.class);
        startActivity(intent);
    }

}
