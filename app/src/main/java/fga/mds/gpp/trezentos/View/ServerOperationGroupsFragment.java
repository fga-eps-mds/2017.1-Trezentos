package fga.mds.gpp.trezentos.View;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import fga.mds.gpp.trezentos.Controller.GroupController;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.Student;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ServerOperationGroupsFragment extends AsyncTask<String, Void, String> {

    public ArrayList<Student> studentGroup;
    private  String userEmail;
    private ShowStudentGroupAdapter showStudentGroupAdapter;
    public ProgressBar progressBar;
    public GroupController groupController;
    private LinearLayout noInternetLayout;
    private String email;
    private Application application;
    private ShowStudentGroupFragment showStudentGroupFragment;
    private Exam exam;
    public UserClassControl userClassControl;



    public ServerOperationGroupsFragment(Application application,
                                        ProgressBar progressBar,
                                        LinearLayout noInternetLayout,
                                         ShowStudentGroupAdapter showStudentGroupAdapter,
                                       ShowStudentGroupFragment  showStudentGroupFragment,
                                        ArrayList<Student> studentGroup){
        this.application = application;
        this.progressBar = progressBar;
        this.noInternetLayout = noInternetLayout;
        this.showStudentGroupFragment = showStudentGroupFragment;
        this.showStudentGroupAdapter = showStudentGroupAdapter;
        this.studentGroup = studentGroup;
    }

    @Override
    protected String doInBackground(String... params) {
        if(isInternetAvailable() ){ //If internet is ok

            HashMap<String, Double> allFirstGrades = new HashMap<String, Double>();
            allFirstGrades = groupController
                    .getFirstGrades(exam.getNameExam(), exam.getUserClassName()
                            , exam.getClassOwnerEmail());
//            HashMap<String, Double> allSecondGrades = new HashMap<String, Double>();
//            allSecondGrades = groupController
//                    .getSecondGrades(exam.getNameExam(), exam.getUserClassName()
//                            , exam.getClassOwnerEmail());

            HashMap<String, Integer> allGroups = new HashMap<String, Integer>();
            allGroups = groupController
                    .getGroups(exam.getNameExam(), exam.getUserClassName()
                            , exam.getClassOwnerEmail(), 0.0);

            studentGroup = new ArrayList<>();
            //Put methods from GroupController
            //studentGroup =


            return "true";

        }else{
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(View.GONE);

        if(result.equals("true")){
            if (showStudentGroupFragment.getActivity() != null) {

                RecyclerView recyclerView =
                        (RecyclerView) showStudentGroupFragment.getActivity()
                                .findViewById(R.id.recycler);
                recyclerView.setVisibility(View.VISIBLE);

                recyclerView.setAdapter(showStudentGroupAdapter);

                showStudentGroupFragment.showStudentAdapter =
                        new ShowStudentGroupAdapter(studentGroup,
                                showStudentGroupFragment.getContext(), recyclerView);

                showStudentGroupAdapter = showStudentGroupFragment.showStudentAdapter;

                recyclerView.setAdapter(showStudentGroupAdapter);


                final LinearLayoutManager layoutManager =
                        new LinearLayoutManager(showStudentGroupFragment
                                .getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);

                RecyclerView.LayoutManager layout = new LinearLayoutManager
                        (application, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layout);
            }

        }else{

            noInternetLayout.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void onPreExecute() {
        userClassControl =
                UserClassControl.getInstance(getApplicationContext());
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(showStudentGroupFragment.getActivity());
        userEmail = session.getString("userEmail","");

        Intent intent = showStudentGroupFragment.getActivity().getIntent();
        Bundle extras = intent.getExtras();
        exam = (Exam) extras.getSerializable("Exam");


    }

    @Override
    protected void onProgressUpdate(Void... values) {}

    public boolean isNetworkAvailable(Context context) {

        final ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));

        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private boolean isInternetAvailable() {
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            if(!address.equals("")){
                return true;
            }
            //return !address.equals("");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return false;
    }

}

