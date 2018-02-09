package fga.mds.gpp.trezentos.View.ServerOperation;

import android.os.AsyncTask;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import fga.mds.gpp.trezentos.Controller.EvaluationControl;
import fga.mds.gpp.trezentos.Controller.GroupController;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;

public class ServerOperationExamsActivity extends AsyncTask<String, Void, String> {

    private HashMap<String, Integer> groups;
    private HashMap<String, Double> grades;
    private Exam exam;
    private UserClass userClass;
    private EvaluationControl evaluationControl;

    public ServerOperationExamsActivity(Exam exam, UserClass userClass,
                                        EvaluationControl evaluationControl){
        this.exam = exam;
        this.userClass = userClass;
        this.evaluationControl = evaluationControl;
    }

    @Override
    protected String doInBackground(String... params) {
        if(isInternetAvailable() ){ //If internet is ok

//            groups = GroupController.getGroups
//                    (exam.getNameExam(),
//                            userClass.getClassName(),
//                            userClass.getOwnerEmail());
//
//            grades = GroupController.getFirstGrades(exam.getNameExam(),
//                    userClass.getClassName(), userClass.getOwnerEmail());
//
//            for(Map.Entry <String, Integer> entry : groups.entrySet()) {
//                evaluationControl.sendEvaluation(exam.getNameExam(), entry.getKey(),
//                        userClass.getClassName(), groups, grades, (double) userClass.getCutOff());
//            }

            return "true";

        }else{
            return null;
        }
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
