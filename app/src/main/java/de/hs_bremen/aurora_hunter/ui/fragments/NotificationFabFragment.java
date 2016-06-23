package de.hs_bremen.aurora_hunter.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.exceptions.PredictionModeNotValidException;
import de.hs_bremen.aurora_hunter.ui.activities.NotificationActivity;
import de.hs_bremen.aurora_hunter.ui.activities.PurchaseNotificationsActivity;


public class NotificationFabFragment extends Fragment {

    private OnModeChangedInterface mListener;

    private PredictionFragment.NOTIFICATION_MODE mMode = PredictionFragment.NOTIFICATION_MODE.NORMAL;

    private FloatingActionButton mFabNotification;

    public NotificationFabFragment() {
        // Required empty public constructor
    }

    private void logFabClick(){
        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName("Purchase")
                .putContentType("User-Interaction")
                .putContentId("Open-Purchase-View"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_notification_fab, container, false);


        mFabNotification = (FloatingActionButton) view.findViewById(R.id.fab_notification);


        mFabNotification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {


                if (checkIfNotificationsArePurchased()) {
                    toggleMode();
                    Intent intent = new Intent(getActivity(), NotificationActivity.class);
                    startActivity(intent);
                } else {
                    logFabClick();
                    Intent intent = new Intent(getActivity(), PurchaseNotificationsActivity.class);
                    startActivity(intent);
                }
            }
        });
        updateInterface();
        view.bringToFront();

        return view;
    }

    private void toggleMode(){
        switch (mMode){
            case SET_NOTIFICATION_LEVEL:
                mMode = PredictionFragment.NOTIFICATION_MODE.NORMAL;
                break;
            case NORMAL:
                mMode = PredictionFragment.NOTIFICATION_MODE.SET_NOTIFICATION_LEVEL;
                break;
            default:
                throw new PredictionModeNotValidException(mMode);
        }
    }

    public void onModeChanged(PredictionFragment.NOTIFICATION_MODE mode) {
        if (mListener != null) {
            mListener.onModeChanged(mode);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() != null && getParentFragment() instanceof OnModeChangedInterface) {
            mListener = (OnModeChangedInterface) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnModeChangedInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateInterface();
    }

    private void updateInterface() {
        if(checkIfNotificationsArePurchased()) {
            mFabNotification.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_notifications_white_24dp));
        } else {
            mFabNotification.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_shop_white_48dp));
        }
    }

    public interface OnModeChangedInterface {

        void onModeChanged(PredictionFragment.NOTIFICATION_MODE mode);
    }

    private boolean checkIfNotificationsArePurchased() {
        Context context = getContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        return sharedPref.getBoolean(getString(R.string.preference_notifications_purchased), false);
    }
}
