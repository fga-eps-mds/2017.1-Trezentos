package fga.mds.gpp.trezentos.DAO;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.View.StudentsFragment;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddFirstGrades extends AsyncTask<String, String, String>{

    private  HashMap<String,String> hashFirstGrades;
    private StudentsFragment studentsFragment;
    private UserClass userClass;
    private Exam exam;
    private HashMap<String, String> toBeParsed;

    private final String url = "https://trezentos-api.herokuapp.com/api/exam/first_grades";

    public AddFirstGrades(UserClass userClass, Exam exam, HashMap<String, String> hashFirstGrades){
        this.userClass = userClass;
        this.exam = exam;
        this.hashFirstGrades = hashFirstGrades;
    }

    @Override
    protected String doInBackground(String... params){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String newBodyFirstGrades = new String();

        try{
            newBodyFirstGrades = createBody(userClass, exam, hashFirstGrades);
        }catch (JSONException e){
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(JSON, newBodyFirstGrades);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        try{
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException e){
            e.printStackTrace();
            Log.i("LOG", "IOException in doInBackground method");
        }
        return null;
    }

    public String createBody(UserClass userClass, Exam exam, HashMap<String, String> hashFirstGrades) throws JSONException {
        String firstGrades = hashFirstGrades.toString();

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", userClass.getOwnerEmail());
        jsonBody.put("userClassName", userClass.getClassName());
        jsonBody.put("name", exam.getNameExam());
        jsonBody.put("firstGrades", firstGrades);

        return jsonBody.toString();
    }

}
