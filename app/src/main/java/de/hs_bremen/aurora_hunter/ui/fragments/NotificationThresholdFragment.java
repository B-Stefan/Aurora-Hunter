package de.hs_bremen.aurora_hunter.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.ui.views.PredictionGraphView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationThresholdFragment extends Fragment {


    private PredictionGraphView mPredictionGraphView;


    public NotificationThresholdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_notification_threshold, container, false);

        mPredictionGraphView = (PredictionGraphView) view.findViewById(R.id.view_prediction_graph);
        mPredictionGraphView.setViewMode(PredictionFragment.NOTIFICATION_MODE.SET_NOTIFICATION_LEVEL);
        mPredictionGraphView.setToStaticData();
        View.OnTouchListener olt = setUpGestureController(mPredictionGraphView);
        view.setOnTouchListener(olt);
        return view;
    }

    private View.OnTouchListener  setUpGestureController(final PredictionGraphView predictionGraphView){
        final GestureDetector.SimpleOnGestureListener sogl = new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

                if(predictionGraphView == null){
                    return true;
                }

                double newValue =  predictionGraphView.getNotificationLevel() + (distanceY/predictionGraphView.getHeight());

                if (newValue > 1) {
                    predictionGraphView.setNotificationLevel(1);
                } else if(newValue < 0) {
                    predictionGraphView.setNotificationLevel(0);
                } else {
                    predictionGraphView.setNotificationLevel(newValue);
                }

                predictionGraphView.invalidate();
                return true;
            }
        };

        final GestureDetectorCompat mDetector;
        mDetector = new GestureDetectorCompat(getContext(), sogl);

        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(predictionGraphView.getViewMode() == PredictionFragment.NOTIFICATION_MODE.SET_NOTIFICATION_LEVEL) {
                    mDetector.onTouchEvent(event);
                    if(event.getAction() == MotionEvent.ACTION_UP){

                    }
                    return true;
                } else {
                    return false;
                }
            }
        };

     }

    public void setThreshold(double level){
        if(mPredictionGraphView == null) {
            return;
        }
        this.mPredictionGraphView.setNotificationLevel(level);
        mPredictionGraphView.invalidate();
    }
    public double getThreshold(){
        if(mPredictionGraphView == null) {
            return 0 ;
        }
        return this.mPredictionGraphView.getNotificationLevel();
    }

}
