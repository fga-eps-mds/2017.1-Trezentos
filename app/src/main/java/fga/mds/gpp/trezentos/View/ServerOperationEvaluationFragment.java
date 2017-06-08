package fga.mds.gpp.trezentos.View;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.Groups;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ServerOperationEvaluationFragment extends AsyncTask<String, Void, String>{

    private Application application;
    private ArrayList<UserClass> userClasses;
    private UserClass userClass;
    private String email;
    private UserClassControl userClassControl;
    private ArrayList<Exam> userExams;
    private Groups userGroups;
    private UserExamControl userExamControl;
    ArrayList<String> examName;
    ArrayList<String> classNames;
    ArrayList<String> studentsList;

    public ServerOperationEvaluationFragment
            (Application application, TextView className, TextView examName){
        this.application = application;
    }

    @Override
    protected String doInBackground(String... params) {
        if(isInternetAvailable() ){ //If internet is ok
            userClasses = new ArrayList<>();
            userExams = new ArrayList<>();
            studentsList = new ArrayList<>();
            classNames = new ArrayList<>();
            examName = new ArrayList<>();

            ArrayList<UserClass> allClasses = userClassControl.getClasses();
            for (UserClass userClass : allClasses) {
                ArrayList<Exam> allExams = userExamControl.getExamsFromUser(email, userClass.getClassName());
                if (userClass.getStudents().contains(email)) {
                    userClasses.add(userClass);
                    for(Exam exam : allExams){
                        for(int i = 0; i < userClass.getStudents().size(); i++) {
                            if(exam.getFirstGrades() != null &&
                                    !userClass.getStudents().get(i).equals("")) {
                                classNames.add(userClass.getClassName());
                                examName.add(exam.getNameExam());
                                studentsList.add(userClass.getStudents().get(i));
                            }else{
                                Log.d("TESTANDO", "passou");
                            }
                        }
                    }
                }
            }

            return "true";

        }else{
            return null;
        }
    }

    @Override
    protected void onPreExecute(){
        userClassControl =
                UserClassControl.getInstance(getApplicationContext());
        userExamControl = UserExamControl.getInstance(getApplicationContext());
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(EvaluationFragment.getInstance().getActivity());
        email = session.getString("userEmail","");
    }

    @Override
    protected void onPostExecute(String result){
        RecyclerView recyclerView = (RecyclerView) EvaluationFragment
                        .getInstance()
                        .getActivity()
                        .findViewById(R.id.recyclerEvaluation);

        initSharedPreferences();

        recyclerView.setAdapter(new StudentsAdapter
                (studentsList, classNames, examName, EvaluationFragment.getInstance().getContext(), recyclerView));

        final LinearLayoutManager layoutManager = new LinearLayoutManager(application);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initSharedPreferences(){
        userClassControl =
                UserClassControl.getInstance(getApplicationContext());
        userExamControl = UserExamControl.getInstance(getApplicationContext());
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(EvaluationFragment.getInstance().getActivity());
        email = session.getString("userEmail","");
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
