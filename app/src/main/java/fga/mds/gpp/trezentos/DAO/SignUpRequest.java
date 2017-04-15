package fga.mds.gpp.trezentos.DAO;


import android.content.Context;
import android.location.Criteria;
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

public class SignUpRequest extends AsyncTask<String, String, String>{


    private UserAccount user;
    private String url = "https://trezentos-api.herokuapp.com/api/user/register";
    private Boolean isFromFacebook;

    public SignUpRequest(UserAccount user, Boolean isFromFacebook){
        this.user = user;
        this.isFromFacebook = isFromFacebook;
    }

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();

        String urlWithParameters = getUrlWithParameters();

        RequestBody body = RequestBody.create(null, "");
        Request request = new Request.Builder()
                .url(urlWithParameters)
                .post(body)
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
        builder.addQueryParameter("email", user.getEmail());
        builder.addQueryParameter("salt", user.getSalt());
        builder.addQueryParameter("password", user.getPassword());
        builder.addQueryParameter("name", user.getName());
        builder.addQueryParameter("facebook", isFromFacebook.toString());
        return builder.build().toString();
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
