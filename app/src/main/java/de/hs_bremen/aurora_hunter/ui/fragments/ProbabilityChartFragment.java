package de.hs_bremen.aurora_hunter.ui.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.commons.prediction.models.Probability;
import de.hs_bremen.aurora_hunter.commons.prediction.models.ProbabilityConclusion;
import de.hs_bremen.aurora_hunter.ui.views.PredictionGraphView;
import de.hs_bremen.aurora_hunter.ui.views.sunmooninfo.MoonView;
import de.hs_bremen.aurora_hunter.ui.views.sunmooninfo.SunView;


public class ProbabilityChartFragment extends Fragment {
    public static final String TAG = ProbabilityChartFragment.class.getSimpleName();
    enum SEASONS {
        WINTER,
        SUMMER
    }
    private PredictionGraphView mPredictionGraphView;
    private LinearLayout mLabelsHolderLayout;
    private LinearLayout mOverviewLayout;
    private LinearLayout mMoonHolderLayout;

    private OnFragmentInteractionListener mListener;

    private PredictionFragment.NOTIFICATION_MODE mMode = PredictionFragment.NOTIFICATION_MODE.NORMAL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_probability_chart, container, false);
        this.mPredictionGraphView = (PredictionGraphView) view.findViewById(R.id.probability_chart_view);
        this.mOverviewLayout = (LinearLayout) view.findViewById(R.id.layout_overlay_chart);
        this.mLabelsHolderLayout = (LinearLayout) this.mOverviewLayout.findViewById(R.id.text_probability_lables);
        this.mMoonHolderLayout = (LinearLayout) this.mOverviewLayout.findViewById(R.id.layout_moon_and_sun);

        return view;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static void scrollToCurrentTime(HorizontalScrollView view, final List<Probability> probabilities, WindowManager windowManager) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        final long currentDate  = new Date().getTime();
        final List<Probability> sortedList =  new ArrayList<>(probabilities);
        //Sort list by delta to current time
        Collections.sort(sortedList, new Comparator<Probability>() {
            @Override
            public int compare(Probability probability, Probability t1) {
                long diff = (new DateTime(probability.getDate()).getMillis() - currentDate);

                return diff > 0 ? 1 : -1;
            }
        });

        //get the index of the item that is next to the current time
        int hourIndex = probabilities.indexOf(sortedList.get(0));

        if(hourIndex != 0){
            //jump to current hour not the next one
            hourIndex -= 1;
        }else {
            //no animation
            return;
        }

        int x = view.getWidth() / 24  * hourIndex;

        DisplayMetrics metrics = new DisplayMetrics();
        if(windowManager != null){
            windowManager.getDefaultDisplay().getMetrics(metrics);
        }

        x = x + metrics.widthPixels - 100;


        ObjectAnimator yTranslate = ObjectAnimator.ofInt(view, "scrollX", x);
        AnimatorSet animators = new AnimatorSet();
        animators.setDuration(1000L);
        animators.setInterpolator(new AccelerateDecelerateInterpolator());
        animators.play(yTranslate);
        animators.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationCancel(Animator arg0) {
                // TODO Auto-generated method stub

            }
        });
        animators.start();
    }

    public void addProbabilities(ProbabilityConclusion probabilityConclusion){

        if(this.isAdded() && !(this.isRemoving() || this.isDetached())){
            mPredictionGraphView.addProbabilities(probabilityConclusion.getHours());
            this.updateSunAndMoonInfo(probabilityConclusion);
            this.updateXAxis(probabilityConclusion.getHours());
        }
    }

    private void updateXAxis(List<Probability> list){
        this.mOverviewLayout.bringToFront();
        this.mLabelsHolderLayout.bringToFront();
        this.mLabelsHolderLayout.removeAllViewsInLayout();

        for(Probability p: list){
            TextView valueTV = new TextView(mLabelsHolderLayout.getContext());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(p.getDate());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(0,10,0,10);

            valueTV.setWidth((int)(50 * Resources.getSystem().getDisplayMetrics().density));
            valueTV.setText(String.format(Locale.getDefault(),"%d:00", calendar.get(Calendar.HOUR_OF_DAY)));
            valueTV.setTypeface(null, Typeface.BOLD);
            valueTV.setTextColor(Color.WHITE);
            valueTV.setLayoutParams(params);
            valueTV.setTextSize(10);
            valueTV.setGravity(View.TEXT_ALIGNMENT_CENTER);

            mLabelsHolderLayout.addView(valueTV);

        }
    }

    public void updateSunAndMoonInfo(final ProbabilityConclusion probabilityConclusion){
        mMoonHolderLayout.removeAllViews();
        mMoonHolderLayout.bringToFront();
        int height  = (int)(25 * Resources.getSystem().getDisplayMetrics().density);
        List<Probability> list = probabilityConclusion.getHours();
        for(Probability p: list){

            final View view;

            if(p.getDate() == null){
                MoonView valueTV = new MoonView(this.getContext());
                valueTV.setPhasePercent(0.0);
                valueTV.setAltitude(-1);
                view = valueTV;
            }
            else if(probabilityConclusion.getSunset() == null || probabilityConclusion.getSunrise() == null){

                SEASONS season;
                int monthOfYear= new DateTime(p.getDate()).getMonthOfYear();
                if(monthOfYear > 2 &&  monthOfYear < 9){
                    season = SEASONS.SUMMER;
                }else  {
                    season = SEASONS.WINTER;
                }

                switch (season){
                    case WINTER:
                        MoonView valueTV = new MoonView(this.getContext());
                        valueTV.setPhasePercent(p.getMoonInformation().getPhase());
                        valueTV.setAltitude(p.getMoonInformation().getAltitude());
                        view = valueTV;
                        break;
                    case SUMMER:
                        SunView local = new SunView(mMoonHolderLayout.getContext());
                        local.setAltitude(p.getSunInformation().getAltitude());
                        view = local;
                        break;
                    default:
                        throw  new RuntimeException("Not expected season" + season);

                }
                //Log.i("Moon", "the dark night");
            }else if(p.getDate().after(probabilityConclusion.getSunset()) && p.getDate().before(probabilityConclusion.getSunrise())){

                MoonView valueTV = new MoonView(this.getContext());
                valueTV.setPhasePercent(p.getMoonInformation().getPhase());
                valueTV.setAltitude(p.getMoonInformation().getAltitude());
                view = valueTV;
                //Log.i("Moon", "the dark night");
            }else {
                SunView valueTV = new SunView(mMoonHolderLayout.getContext());
                valueTV.setAltitude(p.getSunInformation().getAltitude());
                view = valueTV;
                //Log.i("Sun", "sun sun sun ");
            }


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(height,height);
            int margin = Double.valueOf(height).intValue();
            if(list.indexOf(p) == 0 ){
                params.setMargins(0,margin,0,0);

            }else {
                params.setMargins(margin,margin,0,0);
            }
            view.setLayoutParams(params);
            mMoonHolderLayout.addView(view);

            //Log.i("test", p.getSunInformation().getAltitude() + "");
        }

        this.mOverviewLayout.removeView(mMoonHolderLayout);
        this.mOverviewLayout.invalidate();
        this.mOverviewLayout.addView(mMoonHolderLayout,0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnModeChangedInterface");
        */}
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public double getNotificationLevel() {
        return mPredictionGraphView.getNotificationLevel();
    }

    public void setNotificationLevel(double pNotificationLevel) {
        mPredictionGraphView.setNotificationLevel(pNotificationLevel);
        mPredictionGraphView.invalidate();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onNewDataIsThere(List<Probability> probabilities);
    }

    public PredictionFragment.NOTIFICATION_MODE getMode() {
        return mMode;
    }

    public void setMode(PredictionFragment.NOTIFICATION_MODE pMode) {
        mMode = pMode;
        mPredictionGraphView.setViewMode(pMode);
        mPredictionGraphView.invalidate();
    }
}
