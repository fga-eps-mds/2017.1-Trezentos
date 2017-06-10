package fga.mds.gpp.trezentos.View;

import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserExamControl;

import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.R;

import static fga.mds.gpp.trezentos.R.id.button_refresh;

public class ShowStudentGroupFragment extends Fragment {

    private String userEmail;
    public ProgressBar progressBar;
    public UserExamControl userExamControl;
    private static ShowStudentGroupFragment fragment;
    private LinearLayout noInternetLayout;
    private  Boolean connection;
    private Button buttonRefresh;
    private ArrayList<Student>studentGroup;
    public ShowStudentGroupAdapter showStudentAdapter;


    public static ShowStudentGroupFragment getInstance() {
        if(fragment == null){
            fragment = new ShowStudentGroupFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        new ServerOperationGroupsFragment(getActivity().getApplication(),
                progressBar, noInternetLayout,
                showStudentAdapter, fragment, studentGroup).execute();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_class, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        noInternetLayout = (LinearLayout) view.findViewById(R.id.no_internet_layout);
        buttonRefresh = (Button) view.findViewById(button_refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noInternetLayout.setVisibility(View.GONE);
                initClasses();

            }
        });

        initClasses();

        return view;
    }

    public void initClasses(){
        progressBar.setVisibility(View.VISIBLE);
        new ServerOperationGroupsFragment(getActivity().getApplication(),
                progressBar, noInternetLayout, showStudentAdapter,
                fragment, studentGroup)
                .execute();
    }
}
