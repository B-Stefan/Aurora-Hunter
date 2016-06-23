package de.hs_bremen.aurora_hunter.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.hs_bremen.aurora_hunter.R;
import de.hs_bremen.aurora_hunter.commons.prediction.models.Probability;
import de.hs_bremen.aurora_hunter.ui.views.KpIndexChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class KpIndexChartFragment extends Fragment {


    public KpIndexChartFragment() {
        // Required empty public constructor
    }

    private KpIndexChartView mKpIndexChartView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_kp_index_chart, container, false);
        this.mKpIndexChartView = (KpIndexChartView) view.findViewById(R.id.view_kp_index_chart);
        return view;
    }

    public void addProbabilities(List<Probability> list){
        this.mKpIndexChartView.addProbabilities(list);
    }
}
