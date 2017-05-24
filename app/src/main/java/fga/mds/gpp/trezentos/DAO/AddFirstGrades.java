package fga.mds.gpp.trezentos.DAO;

import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by carolina on 24/05/17.
 */

public class AddFirstGrades extends AsyncTask<String, String, String>{
    private final String email;
    private final String userClassName;
    private final String nameExam;
    private final ArrayList<HashMap<String,String>> arrayFirstGrades;
    private StudentsFragment studentsFragment;
    private UserClass userClass;
    private Exam exam;

    private final String url = "https://trezentos-api.herokuapp.com/api/exam/first_grades";

    private AddFirstGrades(String email, String userClassName, String nameExam,
                           ArrayList<HashMap<String, String>> arrayFirstGrades){
        this.email = email;
        this.userClassName = userClassName;
        this.nameExam = nameExam;
        this.arrayFirstGrades = arrayFirstGrades;
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

        builder.addQueryParameter("firstGrades", String.valueOf(studentsFragment.getArrayGrades()));
        return builder.build().toString();
    }
}
