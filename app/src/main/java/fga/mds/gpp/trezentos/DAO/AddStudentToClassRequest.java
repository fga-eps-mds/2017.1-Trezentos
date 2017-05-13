package fga.mds.gpp.trezentos.DAO;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddStudentToClassRequest extends AsyncTask<String, String, String>{
    private final String student;
    private UserClass userClass;

    private String url = "https://trezentos-api.herokuapp.com/api/class/user/student";

    public AddStudentToClassRequest (UserClass userClass, String student){
        this.userClass = userClass;
        this.student = student;
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

        builder.addQueryParameter("email", userClass.getOwnerEmail());
        builder.addQueryParameter("name", userClass.getClassName());
        builder.addQueryParameter("student", student);

        return builder.build().toString();
    }
}