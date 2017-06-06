package fga.mds.gpp.trezentos.View;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class EvaluationFragment extends Fragment {

    private static EvaluationFragment fragment;

    public static EvaluationFragment getInstance() {

        if(fragment == null){
            fragment = new EvaluationFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_evaluation, container, false);

        //Generates students static
        ArrayList<String> students = new ArrayList<>();
        students.add("Arthur Diniz");
        students.add("Cafe");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerEvaluation);
        recyclerView.setAdapter(new StudentsAdapter(students, getActivity().getApplicationContext(), recyclerView));

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}
