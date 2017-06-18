package fga.mds.gpp.trezentos.DAO;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RetrieveGroups {

    private final String classOwnerEmail;
    private final String userClassName;
    private final String name;
    private final String url = "https://trezentos-api.herokuapp.com/api/exam/groups";

    public RetrieveGroups(String name, String userClassName, String classOwnerEmail) {
        this.name = name;
        this.classOwnerEmail = classOwnerEmail;
        this.userClassName = userClassName;
    }

    public String get(){
        OkHttpClient client = new OkHttpClient();

        String urlWithParameters = getUrlWithParameters();

        Request request = new Request.Builder()
                .url(urlWithParameters)
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

        builder.addQueryParameter("classOwnerEmail", classOwnerEmail);
        builder.addQueryParameter("userClassName", userClassName);
        builder.addQueryParameter("name", name);
        return builder.build().toString();
    }
}
