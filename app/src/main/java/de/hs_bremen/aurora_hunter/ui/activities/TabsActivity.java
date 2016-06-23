package de.hs_bremen.aurora_hunter.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;
import java.util.List;


import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.helpers.permissions.PermissionManager;
import de.hs_bremen.aurora_hunter.ui.fragments.tabs.TabOneFragment;
import de.hs_bremen.aurora_hunter.ui.fragments.tabs.TabThreeFragment;

public class TabsActivity extends FragmentActivity {


    public TabsActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        JodaTimeAndroid.init(this);
        setContentView(R.layout.activity_tabbar_test);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        PermissionManager.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}