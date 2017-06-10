package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


import fga.mds.gpp.trezentos.DAO.GetDao;
import fga.mds.gpp.trezentos.DAO.PostDao;
import fga.mds.gpp.trezentos.DAO.PutDao;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import okhttp3.HttpUrl;
import okhttp3.MediaType;

public class UserExamControl{
    private static UserExamControl instance;
    private final Context context;
    private Exam exam;

    public UserExamControl(final Context context){
        this.context = context;
    }

    public static UserExamControl getInstance(final Context context){
        if(instance == null){
            instance = new UserExamControl(context);
        }
        return instance;
    }

    public String validateInformation(String examName, String userClassName, String classOwnerEmail)
            throws UserException{
        String erro;

        try{
            exam = new Exam(examName, userClassName, classOwnerEmail);
            erro = "Sucesso";
            return erro;

        }catch (UserException userException){
            erro = userException.getMessage();
            return erro;
        }
    }

    public void validateCreateExam(String examName, String userClassName, String classOwnerEmail)
            throws UserException{

        try{
            String url = "https://trezentos-api.herokuapp.com/api/exam/register";

            Exam exam = new Exam(examName, userClassName, classOwnerEmail);
            String urlWithParameters = getExamUrlWithParameters(url, exam);

            PostDao postDao = new PostDao(urlWithParameters, null, "");
            postDao.execute();

        }catch (UserException userException){
            userException.printStackTrace();
        }
    }

    public String validateAddsFirstGrades(UserClass userClass, Exam exam)
            throws UserClassException, ExecutionException, InterruptedException{
        Log.d("DADOS", exam.getNameExam() + " " + userClass.getClassName() + exam.getFirstGrades());
        MediaType json = MediaType.parse("application/json; charset=utf-8");

        String body = createFirstGradesBody(userClass, exam);

        String url = "https://trezentos-api.herokuapp.com/api/exam/first_grades";
        PutDao putDao = new PutDao(url, json, body);
        String serverResponse = putDao.execute().get();
        Log.d("SERVERRESPONSECONTROL", serverResponse);

        return serverResponse;
    }

    public String validateAddsSecondGrades(UserClass userClass, Exam exam)
            throws ExecutionException, InterruptedException {

        MediaType json = MediaType.parse("application/json; charset=utf-8");
        String body = createSecondGradesBody(userClass, exam);
        String url = "https://trezentos-api.herokuapp.com/api/exam/second_grades";
        PutDao putDao = new PutDao(url, json, body);

        String serverResponse = putDao.execute().get();

        Log.d("SERVERRESPONSECONTROL", serverResponse);
        return serverResponse;
    }

    private String getExamUrlWithParameters(String url, Exam exam){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        builder.addQueryParameter("name", exam.getNameExam());
        builder.addQueryParameter("userClassName", exam.getUserClassName());
        builder.addQueryParameter("classOwnerEmail", exam.getClassOwnerEmail());

        Log.d("JSONBUILDER", builder.build().toString());
        return builder.build().toString();
    }

    //GET FROM API
    public ArrayList<Exam> getExamsFromUser(String email, String userClassName){
        String url = "https://trezentos-api.herokuapp.com/api/exam/class/user/find";
        String returnedExamUrlWithParameters = getReturnedExamUrlWithParameters(email, userClassName, url);

        GetDao getDao = new GetDao(returnedExamUrlWithParameters);

        String serverResponse = "404";
        serverResponse = getDao.get();

        ArrayList<Exam> userExams = new ArrayList<Exam>();

        try{
            userExams = getArrayList(serverResponse);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return userExams;
    }

    private String getReturnedExamUrlWithParameters(String email, String userClassName, String url){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        builder.addQueryParameter("email", email);
        builder.addQueryParameter("userClassName", userClassName);
        return builder.build().toString();
    }

    private ArrayList<Exam> getArrayList(String serverResponse) throws JSONException{
        JSONArray array = null;

        try{
            array = new JSONArray(serverResponse);
        }catch (JSONException e){
            e.printStackTrace();
        }

        ArrayList<Exam> userExams = new ArrayList<>();

        for(int i = 0; i < array.length(); i++){
            Exam exam = getUserExamFromJson(array.getJSONObject(i));
            userExams.add(exam);
        }

        return userExams;
    }

    private Exam getUserExamFromJson(JSONObject jsonObject) {
        Exam exam = new Exam();

        try{
            exam.setNameExam(jsonObject.getString("name"));
            exam.setUserClassName(jsonObject.getString("userClassName"));
            exam.setClassOwnerEmail(jsonObject.getString("classOwnerEmail"));
            exam.setFirstGrades(jsonObject.getString("firstGrades"));
        }catch (JSONException | UserException e){
            e.printStackTrace();
        }

        return exam;
    }


    public String createFirstGradesBody(UserClass userClass, Exam exam) {
        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("email", exam.getClassOwnerEmail());
            Log.d("JSON", exam.getClassOwnerEmail());
        } catch (JSONException e) {
            Log.d("JSONEXCEPTION", e.toString());
            e.printStackTrace();
        }
        try {
            jsonBody.put("userClassName", userClass.getClassName());
            Log.d("JSONCLASS", userClass.getClassName());
        } catch (JSONException e) {
            Log.d("JSONEXCEPTION", e.toString());
            e.printStackTrace();
        }
        try {
            jsonBody.put("name", exam.getNameExam());
            Log.d("JSON", exam.getNameExam());
        } catch (JSONException e) {
            Log.d("JSONEXCEPTION", e.toString());
            e.printStackTrace();
        }
        try {
            jsonBody.put("firstGrades", exam.getFirstGrades());
            Log.d("JSON", exam.getFirstGrades());
        } catch (JSONException e) {
            Log.d("JSONEXCEPTION", e.toString());
            e.printStackTrace();
        }

        return jsonBody.toString();
    }

    public String createSecondGradesBody(UserClass userClass, Exam exam) {
        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("email", exam.getClassOwnerEmail());
            Log.d("JSON", exam.getClassOwnerEmail());
        } catch (JSONException e) {
            Log.d("JSONEXCEPTION", e.toString());
            e.printStackTrace();
        }
        try {
            jsonBody.put("userClassName", userClass.getClassName());
            Log.d("JSONCLASS", userClass.getClassName());
        } catch (JSONException e) {
            Log.d("JSONEXCEPTION", e.toString());
            e.printStackTrace();
        }
        try {
            jsonBody.put("name", exam.getNameExam());
            Log.d("JSON", exam.getNameExam());
        } catch (JSONException e) {
            Log.d("JSONEXCEPTION", e.toString());
            e.printStackTrace();
        }
        try {
            jsonBody.put("secondGrades", exam.getSecondGrades());
            Log.d("JSON", exam.getSecondGrades());
        } catch (JSONException e) {
            Log.d("JSONEXCEPTION", e.toString());
            e.printStackTrace();
        }
        return jsonBody.toString();
    }
}
