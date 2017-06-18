package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.DAO.GetEvaluationRequest;
import fga.mds.gpp.trezentos.DAO.SendEvaluationRequest;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.UserAccount;

import static fga.mds.gpp.trezentos.R.drawable.group;

public class EvaluationControl {

    private static EvaluationControl instance;
    final Context context;
    private UserAccount userAccount;
    private Evaluation evaluation = new Evaluation();

    public EvaluationControl(final Context context){
        this.context = context;

    }

    public static EvaluationControl getInstance(final Context context){
        if (instance == null) {
            instance = new EvaluationControl(context);
        }

        return instance;
    }

    public boolean sendEvaluation(String examName, String email, String userClassName,
                                  HashMap<String, Integer> groups,
                                  HashMap<String, Double> grades, Double cutOff){

        userAccount = getUserWithInfo(email);

        evaluation.setClassName(userClassName);
        evaluation.setExamName(examName);

        Double nota = grades.get(email);
        Integer group = groups.get(email);

        for (Map.Entry<String, Integer> entry : groups.entrySet()) {
            if (!email.equals(entry.getKey()) &&
                    group.equals(entry.getValue())) {

                return sendToDao(examName, userClassName, group.toString(), email, entry.getKey());
            }
        }

        return false;
    }

    private boolean sendToDao(String examName, String userClassName, String groupNumber,
                                  String email, String emailToEvaluate){

        evaluation.setStudentEmail(emailToEvaluate);

        JSONObject jsonObject = buildJSONObject();

        try {

            return (new SendEvaluationRequest(userAccount, jsonObject)
                            .execute().get()).equals("true");

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    private JSONObject buildJSONObject(){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("userClassName", evaluation.getClassName());
            jsonObject.put("examName", evaluation.getExamName());
            jsonObject.put("studentToEvaluate", evaluation.getStudentEmail());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private UserAccount getUserWithInfo(String email){
        UserAccount userAccountWithInfo = new UserAccount();

        try{
            userAccountWithInfo.setEmail(email);
        }catch (UserException e){
            e.printStackTrace();
        }
        return userAccountWithInfo;
    }

    public ArrayList<Evaluation> getEvaluations(UserAccount userAccount){
        GetEvaluationRequest evaluationRequest = new GetEvaluationRequest(userAccount);
        String serverResponse = "404";
        serverResponse = evaluationRequest.get();

        ArrayList<Evaluation> evaluationList = new ArrayList<>();

        try {
            evaluationList = getEvaluationsList(serverResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return evaluationList;
    }

    private ArrayList<Evaluation> getEvaluationsList(String serverResponse) throws JSONException{

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(serverResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Evaluation> evaluations = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            Evaluation evaluation = getEvaluationsFromJson(jsonArray.getJSONObject(i));
            evaluations.add(evaluation);
        }

        return evaluations;
    }

    private Evaluation getEvaluationsFromJson(JSONObject jsonObject){
        Evaluation evaluation = new Evaluation();

        try {
            evaluation.setClassName(jsonObject.getString("userClassName"));
            evaluation.setExamName(jsonObject.getString("examName"));
            evaluation.setStudentEmail(jsonObject.getString("studentToEvaluate"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return evaluation;
    }

}
