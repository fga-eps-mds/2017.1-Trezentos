package fga.mds.gpp.trezentos.DAO;


import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getAllClassRequest extends AsyncTask<String, String, String> {


    private final String url =
            "https://trezentos-api.herokuapp.com/api/class/find";

    public getAllClassRequest(){

    }

    @Override
    protected String doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();

        String urlWithParameters = getUrlWithParameters();

        Request request = new Request.Builder()
                .url(urlWithParameters)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getUrlWithParameters() {

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        return builder.build().toString();
    }

}
