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
    private UserExamControl userExam;
    ArrayList<String> examName;
    ArrayList<String> classNames;

    public ServerOperationEvaluationFragment
            (Application application, TextView className, TextView examName){
        this.application = application;
    }

    @Override
    protected String doInBackground(String... params) {
        if(isInternetAvailable() ){ //If internet is ok
            userClasses = new ArrayList<>();
            ArrayList<UserClass> allClasses = userClassControl.getClasses();

            for (UserClass userClass : allClasses) {
                if (userClass.getOwnerEmail().equals(email) ||
                        userClass.getStudents().contains(email)) {
                    userClasses.add(userClass);
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
        userClasses = populateArrayListClasses(userClasses);
        userExams = userExam.getExamsFromUser(email, userClass.getClassName());

        ArrayList<String> students = populateArrayListStudents(userClasses, userExams);

        recyclerView.setAdapter(new StudentsAdapter
                (students, classNames, examName, EvaluationFragment.getInstance().getContext(), recyclerView));

        final LinearLayoutManager layoutManager = new LinearLayoutManager(application);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private ArrayList<UserClass> populateArrayListClasses(ArrayList<UserClass> userClasses){

        ArrayList<UserClass> tempList = new ArrayList<>();

        //Pegar arraylist de classes em que o usuario pertence como aluno

        if(userClasses != null) {

            for (UserClass userClass : userClasses) {
                if (userClass.getStudents().contains(email)){
                    //COLOCAR && + get para os grupos (sendo diferente de nulo)
                    tempList.add(userClass);
                    Log.d("CLASSFILTERED", userClass.getClassName());
                }
            }
        }
        return tempList;
    }

    private void initSharedPreferences(){
        userClassControl =
                UserClassControl.getInstance(getApplicationContext());
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(EvaluationFragment.getInstance().getActivity());
        email = session.getString("userEmail","");
    }

    private ArrayList<String> populateArrayListStudents(ArrayList<UserClass> filteredClasses
            , ArrayList<Exam> evaluationExam){
        ArrayList<String> studentsList = new ArrayList<>();
        classNames = new ArrayList<>();
        examName = new ArrayList<>();

        for(UserClass userClass : filteredClasses){
            for(Exam exam : evaluationExam){
                for(int i = 0; i < userClass.getStudents().size(); i++){
                    classNames.add(userClass.getClassName());
                    examName.add(exam.getNameExam());
                    studentsList.add(userClass.getStudents().get(i));
                }
            }
        }

        return studentsList;
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
