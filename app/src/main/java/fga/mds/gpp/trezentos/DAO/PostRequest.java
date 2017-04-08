package fga.mds.gpp.trezentos.DAO;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import fga.mds.gpp.trezentos.Model.UserAccount;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostRequest extends AsyncTask<String, String, String>{


    private UserAccount user;
    private String url;
    private Context context;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private String responseCode;

    public PostRequest(UserAccount user, String url, Context context){
        this.user = user;
        this.url = url;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter("email", user.getEmail());
        builder.addQueryParameter("password", user.getPassword());
        builder.addQueryParameter("name", user.getName());
        String urlWithParams = builder.build().toString();

        RequestBody body = RequestBody.create(null, "");
        Request request = new Request.Builder()
                .url(urlWithParams)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            responseCode = String.valueOf(response.code());
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(context, responseCode, Toast.LENGTH_SHORT).show();
    }
}
