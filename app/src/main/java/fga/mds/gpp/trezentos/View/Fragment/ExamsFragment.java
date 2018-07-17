package fga.mds.gpp.trezentos.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.ExamActivity;
import fga.mds.gpp.trezentos.View.Activity.StudentExamActivity;
import fga.mds.gpp.trezentos.View.Adapters.ExamAdapter;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ExamsFragment extends Fragment{

    public ArrayList<Exam> userExams;
    public UserExamControl userExamControl;
    private UserClass userClass;
    public ProgressBar progressBar;
    public  String userId;
    private RecyclerView recyclerView = null;
    private View view = null;
    private LinearLayout noInternetLayout;
    private Button buttonRefresh;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exams, container, false);

        progressBar = view.findViewById(R.id.progressBarExam);
        recyclerView = view.findViewById(R.id.recyclerExam);
        noInternetLayout = view.findViewById(R.id.no_internet_exam);
        buttonRefresh = view.findViewById(R.id.exam_refresh);

        progressBar.setVisibility(View.VISIBLE);

        initExams();

        return view;
    }

    private void initExams(){

        UserAccountControl userAccountControl =
                UserAccountControl.getInstance(getApplicationContext());

        if(userAccountControl.isNetworkAvailable()) {
            loadRecover();
            try {
                userExams = userExamControl.getExamsFromUser(userId, userClass.getIdClass());
                Log.d("PARAMS", userId + "  " + userClass.getIdClass());
                Log.d("EXAM", String.valueOf(userExams.size()));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (getActivity() != null) {
                progressBar.setVisibility(View.GONE);

                final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(new ExamAdapter(userExams, getActivity().getApplicationContext(), recyclerView, userClass, userId));
            }

        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            noInternetLayout.setVisibility(View.VISIBLE);
            buttonRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noInternetLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    initExams();
                }
            });
        }

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
    public void onResume() {
        initExams();
        super.onResume();
    }



}



