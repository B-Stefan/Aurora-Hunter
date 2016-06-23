package de.hs_bremen.aurora_hunter.ui.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import de.hs_bremen.aurora_hunter.ApiManager;
import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.commons.prediction.api.ProbabilityApi;
import de.hs_bremen.aurora_hunter.commons.prediction.models.Probability;
import de.hs_bremen.aurora_hunter.commons.prediction.models.ProbabilityConclusion;
import de.hs_bremen.aurora_hunter.helpers.network.NetworkChangeReceiver;
import de.hs_bremen.aurora_hunter.ui.views.MainScrollView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PredictionFragment extends Fragment  {


    static final String TAG  = "PredictionFragment";

    public static enum NOTIFICATION_MODE {
        NORMAL,
        SET_NOTIFICATION_LEVEL
    }

    private NOTIFICATION_MODE mMode = NOTIFICATION_MODE.NORMAL;

    private ProbabilityChartFragment mProbabilityChartFragment;

    private KpIndexChartFragment mKpIndexChartFragment;

    private MainScrollView mMainScrollView;
    private HorizontalScrollView mHorizontalScrollView;
    private TextView mTextCurrentDate;

    private boolean mInvalidDate = true;

    private Date mDate;

    private TextView mProbabilityConclution;
    private TextView mProbabilityConclutionHightText;
    private LinearLayout mProbabilityConclutionLinearLayout;
    private Location mLocation;

    private Location location;

    private SimpleCacheManager mCacheManager = new SimpleCacheManager();

    private BroadcastReceiver mConnectionStatusUpdateRecevier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            boolean isConnected = intent.getBooleanExtra(NetworkChangeReceiver.EXTRA_IS_CONNECTED,false);
            if(mInvalidDate && isConnected) {
                if (isInLayout() && getView() != null && isAdded()){
                    updateData();
                }else {
                    Answers.getInstance().logCustom(new CustomEvent("Error")
                            .putCustomAttribute("name","Prediction")
                            .putCustomAttribute("source","User-Interaction")
                            .putCustomAttribute("event","internet-connection-there-but-view-invalid"));
                }
            }else if(!isConnected){

                showMissingInternetConnectionSnackbar();


            }
        }
    };

    public void showMissingInternetConnectionSnackbar(){
        if(isAdded() && getView() != null && getContext() != null){
            final Snackbar snackBar = Snackbar.make(getView(), getString(R.string.error_no_internet_connection), Snackbar.LENGTH_LONG);

            snackBar.setAction(getString(R.string.msg_ok), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackBar.dismiss();
                }
            });
            snackBar.show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getActivity().registerReceiver(mConnectionStatusUpdateRecevier, new IntentFilter(NetworkChangeReceiver.INTENT_FILTER));

        this.mCacheManager.setSavedBundle(savedInstanceState);

        final View view = inflater.inflate(R.layout.fragment_prediction, container, false);

        mHorizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.scroll_probability_chart);

        mProbabilityConclution = (TextView) view.findViewById(R.id.text_city);

        mTextCurrentDate = (TextView) view.findViewById(R.id.text_current_date);

        final LinearLayout fullScreenLayout = (LinearLayout) view.findViewById(R.id.layout_full_screen);
        mMainScrollView = (MainScrollView) view.findViewById(R.id.scroll_prediction_main);
        mHorizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.scroll_probability_chart);
        mProbabilityConclutionHightText = (TextView) view.findViewById(R.id.text_height);
        mProbabilityConclutionLinearLayout = (LinearLayout) view.findViewById(R.id.layout_probability_conclusion);
        mTextCurrentDate = (TextView) view.findViewById(R.id.text_current_date);

        setCurrentDateFormatted(mDate);
        resizeLayoutToFullScreenHeight(view, fullScreenLayout);


        FragmentManager fm = this.getChildFragmentManager();

        mProbabilityChartFragment = (ProbabilityChartFragment) getChildFragmentManager().findFragmentByTag("mProbabilityChartFragment");


        if(mProbabilityChartFragment == null){
            mProbabilityChartFragment = new ProbabilityChartFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frameLayout_probability_chart_container, mProbabilityChartFragment, "mProbabilityChartFragment");
            ft.commit();
            fm.executePendingTransactions();
        }


        mKpIndexChartFragment = (KpIndexChartFragment) getChildFragmentManager().findFragmentByTag("mKpIndexChartFragment");

        if(mKpIndexChartFragment == null){
            mKpIndexChartFragment = new KpIndexChartFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frameLayout_kp_chart_container, mKpIndexChartFragment, "mKpIndexChartFragment");
            ft.commit();
            fm.executePendingTransactions();
        }

        YoYo.with(Techniques.FadeOutDown)
                .duration(1)
                .playOn(this.mProbabilityConclutionLinearLayout);

        YoYo.with(Techniques.FadeOutDown)
                .duration(1)
                .playOn(this.mHorizontalScrollView);


        setCurrentDateFormatted(mDate);

        resizeLayoutToFullScreenHeight(view, fullScreenLayout);

        updatePrediction();
        scrollToTop();

        return view;
    }

    public void scrollToTop(){
        if(mMainScrollView!=null){
            mMainScrollView.post(new Runnable() {
                @Override
                public void run() {
                    mMainScrollView.smoothScrollTo(0,0);
                }
            });

            mMainScrollView.invalidate();
        }else {
            Toast.makeText(getContext(),"No ScrollView ... thats bad", Toast.LENGTH_SHORT).show();
        }


    }
    private void updateBackgroundImage(final View view, ProbabilityConclusion probabilityConclusion){

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        final LinearLayout bgLayout =  (LinearLayout)view.findViewById(R.id.layout_full_screen);


        List<Drawable> backgrounds = new ArrayList<Drawable>();
        backgrounds.add(ResourcesCompat.getDrawable(getResources(), R.drawable.background_night_24dp, null));
        backgrounds.add(ResourcesCompat.getDrawable(getResources(), R.drawable.background_day_24dp, null));

        final Date current = new Date();
        if(probabilityConclusion != null && probabilityConclusion.getSunrise() != null && probabilityConclusion.getSunset() != null ){
            if(current.after(probabilityConclusion.getSunset()) && current.before(probabilityConclusion.getSunrise())){
                Collections.reverse(backgrounds);
            }
        }

        TransitionDrawable crossfader = new TransitionDrawable(backgrounds.toArray(new Drawable[2]));
        bgLayout.setBackground(crossfader);
        crossfader.startTransition(1500);


    }



    private void resizeLayoutToFullScreenHeight(View view, LinearLayout layout){

        int statusBarHeight = 0;

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight += getResources().getDimensionPixelSize(resourceId);
        }

        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();

        Display screenDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        screenDisplay.getSize(size);
        layoutParams.height = size.y - statusBarHeight;// - getSoftButtonsBarHeight();
        layout.setLayoutParams(layoutParams);
        layout.invalidate();
        view.invalidate();
    }


    @Override
    public void onResume() {
        super.onResume();
        updatePrediction();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        this.mCacheManager.setDataToBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(mConnectionStatusUpdateRecevier);
    }

    private void updatePrediction() {

        ProbabilityApi mProbabilityApi = ApiManager.getInstance().configure(new ProbabilityApi());

        if(this.mLocation == null ){
            return;
        }


        YoYo.with(Techniques.FadeOutDown)
                .duration(1)
                .playOn(this.mProbabilityConclutionLinearLayout);


        mProbabilityApi.probabilityPrediction(this.mDate, this.mLocation.getLatitude(), this.mLocation.getLongitude(), new Response.Listener<ProbabilityConclusion>() {

            @Override
            public void onResponse(final ProbabilityConclusion response) {

                if(!isAdded()){
                    return;
                }
                updateBackgroundImage(getView(),response);
                YoYo.with(Techniques.FadeInUp)
                        .duration(700)
                        .withListener(new com.nineoldandroids.animation.Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {

                                if(getActivity() != null && mHorizontalScrollView != null ){
                                    ProbabilityChartFragment.scrollToCurrentTime(mHorizontalScrollView,response.getHours(),getActivity().getWindowManager());
                                }
                            }

                            @Override
                            public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {

                            }
                        })
                        .playOn(mHorizontalScrollView);


                mInvalidDate = false;
                setCurrentDateFormatted(response.getSunset());
                mCacheManager.setData(response);
                updateProbabilityConclusion(response);
                mProbabilityChartFragment.addProbabilities(response);
                mKpIndexChartFragment.addProbabilities(response.getHours());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                final ProbabilityConclusion probabilityConclusion = mCacheManager.getDataFromBundle().data;
                if(probabilityConclusion != null){
                    Toast.makeText(getContext(), probabilityConclusion.getHours().size() + " cache", Toast.LENGTH_LONG).show();
                }else {
                    if(error instanceof TimeoutError || error instanceof NoConnectionError){
                        showMissingInternetConnectionSnackbar();
                    }else {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    public void update() {

    }

    private void  updateProbabilityConclusion(ProbabilityConclusion response){

        String label = "";
        final Probability max = response.getMax();

        if(max == null){
            this.mProbabilityConclution.setText("Low");
            return;
        }
        if(max.getProbability() > 0.7){
            label = "High";
        }else if(max.getProbability() > 0.4 ){
            label = "Medium";
        }else {
            label = "Low";

        }
        this.mProbabilityConclution.setText(label);


        DateFormat dateFormat = android.text.format.DateFormat.getTimeFormat(getContext());
        String time = dateFormat.format(max.getDate());
        String maxString = Long.toString(Math.round(max.getProbability()*100)) + "%";
        this.mProbabilityConclutionHightText.setText(getResources().getString(R.string.prediction_height_message,maxString,time));

        YoYo.with(Techniques.FadeInUp)
                .duration(200)
                .playOn(this.mProbabilityConclutionLinearLayout);


    }
    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public void updateData(){
        if(mInvalidDate){
            updatePrediction();
            setCurrentDateFormatted(mDate);
        }
        final View view = getView();
        if(view != null){
           // updateBackgroundImage(view);
        }
    }

    public void setCityName(String string){
        this.mProbabilityConclution.setText(string);
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location mLocation) {
        this.mLocation = mLocation;
        mInvalidDate = true;
    }

    public void setLocation(double lat, double lng) {
        this.mLocation = new Location("ownProvider");
        this.mLocation.setLatitude(lat);
        this.mLocation.setLongitude(lng);
        mInvalidDate = true;
    }

    private void setCurrentDateFormatted(Date dateFormatted){
        if(dateFormatted != null ){

            DateFormat df = DateFormat.getDateInstance();
            this.mTextCurrentDate.setText(df.format(dateFormatted));
        }
    }

    public NOTIFICATION_MODE getMode() {
        return mMode;
    }

    public void setMode(NOTIFICATION_MODE pMode) {
        //Log.i(TAG,"mode changed" + pMode.toString());
        mMode = pMode;

        if(mMode == NOTIFICATION_MODE.SET_NOTIFICATION_LEVEL) {
            mMainScrollView.setEnableScrolling(false);
        } else {
            mMainScrollView.setEnableScrolling(true);
        }


        mProbabilityChartFragment.setMode(pMode);
    }
}