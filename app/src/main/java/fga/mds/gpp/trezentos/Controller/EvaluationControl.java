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

import fga.mds.gpp.trezentos.DAO.GetDao;
import fga.mds.gpp.trezentos.DAO.PostDao;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.UserAccount;
import okhttp3.HttpUrl;
import okhttp3.MediaType;

import static fga.mds.gpp.trezentos.R.drawable.group;

public class EvaluationControl {

    private static EvaluationControl instance;
    final Context context;
    private String email;
    private String userClassName;
    private String ownerEmail;
    private String evaluateEmail;
    private String ownerGrade;
    private String otherGrade;
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
                                  HashMap<String, Double> grades, String cutOff){

        userAccount = getUserWithInfo(email);

        evaluation.setClassName(userClassName);
        evaluation.setExamName(examName);

        Log.d("groups", groups.toString());
        Log.d("grades", grades.toString());

        boolean response = false;

        Log.d("email", email);

        Double nota = grades.get(email);
        Log.d("nota", nota.toString());
        Integer group = groups.get(email);
        Log.d("grupo", group.toString());

        Double notaDeCorte = Double.valueOf(cutOff);
        Log.d("notaDeCorte", notaDeCorte.toString());

        for (Map.Entry<String, Integer> entry : groups.entrySet()) {
            if (!email.equals(entry.getKey()) &&
                    group.equals(entry.getValue())) {
                Double gradeToEvaluate = grades.get(entry.getKey());

                if(gradeToEvaluate >= notaDeCorte && nota < notaDeCorte){
                    response = sendToDao(examName, userClassName,
                            group.toString(), email, entry.getKey());
                }
                if(gradeToEvaluate > notaDeCorte && nota <= notaDeCorte){
                    response = sendToDao(examName, userClassName,
                            group.toString(), email, entry.getKey());
                }
            }
        }

        return response;
    }

//    private boolean sendToDao(String examName, String userClassName, String groupNumber,
//                                  String email, String emailToEvaluate){
//
//        String response = "false";
//
//        evaluation.setStudentEmail(emailToEvaluate);
//
//        JSONObject jsonObject = new JSONObject();
//
//        try {
//            jsonObject.put("userClassName", evaluation.getClassName());
//            jsonObject.put("examName", evaluation.getExamName());
//            jsonObject.put("studentToEvaluate", evaluation.getStudentEmail());
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            response =
//                    new SendEvaluationRequest(userAccount, jsonObject)
//                            .execute()
//                            .get();
//
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        Log.d("RESULT", response);
//
//        return response.equals("true");
//    }

    private boolean sendToDao(String examName, String userClassName, String groupNumber,
                              String email, String emailToEvaluate){

        String response = "false";

        evaluation.setStudentEmail(emailToEvaluate);

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("userClassName", evaluation.getClassName());
            jsonObject.put("examName", evaluation.getExamName());
            jsonObject.put("studentToEvaluate", evaluation.getStudentEmail());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String urlWithParameters = getJsonBody(jsonObject);
            response = new PostDao(urlWithParameters, null, "")
                    .execute()
                    .get();

//
//            response =
//                    new SendEvaluationRequest(userAccount, jsonObject)
//                            .execute()
//                            .get();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        Log.d("RESULT", response);

        return response.equals("true");
    }

    private String getJsonBody(JSONObject json){

        try {
            json.put("email", userAccount.getEmail());
            json.put("rateToDo", evaluation);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("JSONARRAY", json.toString());

        return json.toString();
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

//    public ArrayList<Evaluation> getEvaluations(UserAccount userAccount){
//        GetEvaluationRequest evaluationRequest = new GetEvaluationRequest(userAccount);
//        String serverResponse = "404";
//        serverResponse = evaluationRequest.get();
//
//        ArrayList<Evaluation> evaluationList = new ArrayList<>();
//
//        try {
//            evaluationList = getEvaluationsList(serverResponse);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return evaluationList;
//    }

    public ArrayList<Evaluation> getEvaluations(UserAccount userAccount){
        String urlWithParameters = getEvaluationsUrlWithParameters(userAccount);
        GetDao getDao = new GetDao(urlWithParameters);

        String serverResponse = "404";
        serverResponse = getDao.get();

        ArrayList<Evaluation> evaluationList = new ArrayList<>();

        try {
            evaluationList = getEvaluationsList(serverResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return evaluationList;
    }

    private String getEvaluationsUrlWithParameters(UserAccount userAccount){
        String url = "https://trezentos-api.herokuapp.com/api/user/rateToDo";
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        builder.addQueryParameter("email", userAccount.getEmail());

        return builder.build().toString();
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
