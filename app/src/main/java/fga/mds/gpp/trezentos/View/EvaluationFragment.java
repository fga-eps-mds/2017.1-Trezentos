package fga.mds.gpp.trezentos.View;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fga.mds.gpp.trezentos.R;

public class EvaluationFragment extends Fragment {


    public static EvaluationFragment newInstance() {
        EvaluationFragment fragment = new EvaluationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View returnView = inflater.inflate(R.layout.fragment_evaluation, container, false);

        return returnView;
    }
}
