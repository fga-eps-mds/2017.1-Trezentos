package fga.mds.gpp.trezentos.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.ExamActivity;
import fga.mds.gpp.trezentos.View.Activity.StudentExamActivity;
import fga.mds.gpp.trezentos.View.Adapters.ExamAdapter;

public class ExamsFragment extends Fragment{

    public ArrayList<Exam> userExams;
    public UserExamControl userExamControl;
    private UserClass userClass;
    public ProgressBar progressBar;
    public  String userId;
    private RecyclerView recyclerView = null;
    private View view = null;

    private static ExamsFragment fragment;

    public static ExamsFragment getInstance() {
        if(fragment == null){
            fragment = new ExamsFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private void loadRecover(){

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userId = session.getString("userId","");

        Intent intent = getActivity().getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");

        userExamControl = UserExamControl.getInstance(getActivity());

        //ArrayList <HashMap<String, Double>> aluno = new ArrayList<HashMap<String, Double>>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exams, container, false);

        progressBar = view.findViewById(R.id.progressBarExam);
        recyclerView = view.findViewById(R.id.recyclerExam);
        progressBar.setVisibility(View.VISIBLE);

        loadRecover();

        try {
            userExams = userExamControl.getExamsFromUser(userId, userClass.getIdClass());
            Log.d("PARAMS", userId + "  " + userClass.getIdClass());
            Log.d("EXAM", String.valueOf(userExams.size()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initRecycleView();

        return view;
    }

        public void initRecycleView(){

        if (getActivity() != null) {
            progressBar.setVisibility(View.GONE);

            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(new ExamAdapter(userExams, getActivity().getApplicationContext(), recyclerView, userClass, userId));
        }
    }






}



