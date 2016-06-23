package de.hs_bremen.aurora_hunter.ui.fragments;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.helpers.permissions.PermissionManager;
import de.hs_bremen.aurora_hunter.notifications.GooglePlayServiceUtils;
import de.hs_bremen.aurora_hunter.notifications.NotificationManager;
import de.hs_bremen.aurora_hunter.notifications.locationUpdate.LocationService;
import de.hs_bremen.aurora_hunter.ui.fragments.placeSearch.SearchFragment;


public class MainFragment extends Fragment implements SearchFragment.LocationChangedInterface, NotificationFabFragment.OnModeChangedInterface{


    public MainFragment() {
        // Required empty public constructor
    }

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private Location mCurrentLocation;

    private PredictionFragment mPredictionTodayFragment = new PredictionFragment();
    private PredictionFragment mPredictionTomorrowFragment = new PredictionFragment();
    private PredictionFragment mPredictionDayAfterTomorrowFragment = new PredictionFragment();


    private void logTabChange(int tabPosition){
        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName("Tab-Change")
                .putContentType("User-Interaction")
                .putContentId("TAB-"+tabPosition));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view  = inflater.inflate(R.layout.fragment_main, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        final SearchFragment mSearchBoxView =(SearchFragment) getChildFragmentManager().findFragmentById(R.id.fragment_search_box);

        final NotificationFabFragment notificationFabFragment = (NotificationFabFragment) getChildFragmentManager().findFragmentById(R.id.fragment_notification_fab);

        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                logTabChange(position);
            }

            @Override
            public void onPageSelected(int position) {
                logTabChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPredictionTodayFragment.setDate(new Date());
        mPredictionTomorrowFragment.setDate(new DateTime().plusDays(1).toDate());
        mPredictionDayAfterTomorrowFragment.setDate(new DateTime().plusDays(2).toDate());

        /**
         * Start the Notification service
         */
        PermissionManager.getInstance().ensureAllPermissions(this.getActivity(), new PermissionManager.PermissionResultCallback() {
            @Override
            public void granted() {

                String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);


                NotificationManager.getInstance().setId(android_id.hashCode());
                GooglePlayServiceUtils.startGMCTokenRegisterService(getActivity()); // Get a token for GCM
                getActivity().startService(new Intent(getActivity(), LocationService.class));

            }

            @Override
            public void denied() {

            }
        });


        if(mSearchBoxView.getView()!= null){
            mSearchBoxView.getView().bringToFront();
        }
        if(notificationFabFragment.getView() != null){
            notificationFabFragment.getView().bringToFront();
        }


        return view;
    }


    private void setupViewPager(ViewPager viewPager) {

        Date date = new DateTime().plusDays(2).toDate();
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(mPredictionTodayFragment, getString(R.string.tabs_today));
        adapter.addFragment(mPredictionTomorrowFragment, getString(R.string.tabs_tomorrow));
        adapter.addFragment(mPredictionDayAfterTomorrowFragment, dateFormat.format(date));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        triggerUpdate();
    }
    private void  triggerUpdate(){

        mPredictionTodayFragment.setLocation(mCurrentLocation);
        mPredictionTomorrowFragment.setLocation(mCurrentLocation);
        mPredictionDayAfterTomorrowFragment.setLocation(mCurrentLocation);


        if(mPredictionTodayFragment.isAdded()){
            mPredictionTodayFragment.updateData();
        }
        if(mPredictionTomorrowFragment.isAdded()){
            mPredictionTomorrowFragment.updateData();
        }
        if(mPredictionDayAfterTomorrowFragment.isAdded()){
            mPredictionDayAfterTomorrowFragment.updateData();
        }
    }

    @Override
    public void onRefreshRequested() {

        if(mCurrentLocation!= null){
            triggerUpdate();
        }else {
            Toast.makeText(getContext(),getString(R.string.msg_no_valid_city_selected),Toast.LENGTH_LONG).show();
            Answers.getInstance().logContentView(new ContentViewEvent()
                    .putContentName("Prediction")
                    .putContentType("User-Interaction")
                    .putContentId("location-not-threre"));
        }
    }

    @Override
    public void onModeChanged(PredictionFragment.NOTIFICATION_MODE mode) {
        mPredictionTodayFragment.setMode(mode);
        this.mPredictionTodayFragment.scrollToTop();
        this.mViewPager.setCurrentItem(0,true);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();

        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }



        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
