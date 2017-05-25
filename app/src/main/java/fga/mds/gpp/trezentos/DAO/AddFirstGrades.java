package fga.mds.gpp.trezentos.DAO;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.View.StudentsFragment;
import okhttp3.HttpUrl;
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
        OkHttpClient client = new OkHttpClient();

        String urlWithParameters = getUrlWithParameters();

        RequestBody body = RequestBody.create(null, "");
        Request request = new Request.Builder()
                .url(urlWithParameters)
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

    private String getUrlWithParameters(){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        // owner email
        builder.addQueryParameter("email", userClass.getOwnerEmail());
        //nome da sala
        builder.addQueryParameter("userClassName", userClass.getClassName());
        // nome da prova
        builder.addQueryParameter("name", exam.getNameExam());
        Log.d("NOTASDAO", Integer.toString(studentsFragment.getHashEmailAndGrade().size()));
        // primeira nota
        builder.addQueryParameter("firstGrades", studentsFragment.getHashEmailAndGrade().toString());

        return builder.build().toString();
    }
}
