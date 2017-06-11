package fga.mds.gpp.trezentos.DAO;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.UserAccount;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendEvaluationRequest extends AsyncTask<String, String, String> {

    private UserAccount userAccount;
    private String emailToEvaluate;
    private String userClassName;
    private String examName;
    private String group;
    private String email;
    private JSONObject evaluation;
    private String url = "https://trezentos-api.herokuapp.com/api/user/rateToDo";

    public SendEvaluationRequest(UserAccount userAccount, JSONObject evaluation){
        this.userAccount = userAccount;
        this.evaluation = evaluation;
    }

    @Override
    protected String doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();

        String json = getJsonBody();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("LOG", "IOException in doInBackground method");
        }

        return null;
    }

//    private String getJsonBody() {
//        JSONObject jsonObject = new JSONObject();
//
//        try {
//            jsonObject.put("email", userAccount.getEmail());
//            jsonObject.put("userClassName", userClassName);
//            jsonObject.put("examName", examName);
//            jsonObject.put("studentToEvaluate", emailToEvaluate);
//            jsonObject.put("group", group);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Log.d("JSON", jsonObject.toString());
//
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return jsonObject.toString();
//    }

    private String getJsonBody(){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", userAccount.getEmail());
            jsonObject.put("rateToDo", evaluation);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("JSONARRAY", jsonObject.toString());

        return jsonObject.toString();
    }

//    public SendEvaluationRequest(String userClassName, String examName,
//                                 String studentGrade, String email,
//                                 String studentToEvaluate, String group){
//        this.userClassName = userClassName;
//        this.examName = examName;
//        this.studentGrade = studentGrade;
//        this.email = email;
//        this.studentToEvaluate = studentToEvaluate;
//        this.group = group;
//    }

//    private String getUrlWithParameters(){
//        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
//
//        builder.addQueryParameter("email", userAccount.getEmail());
//        builder.addQueryParameter("rate", String.valueOf(evaluation));
//
//        Log.d("EVALUATION", String.valueOf(evaluation));
//
//        return builder.build().toString();
//    }

}
