package fga.mds.gpp.trezentos.DAO;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import fga.mds.gpp.trezentos.Model.UserAccount;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignInRequest extends AsyncTask<String, String, String> {

    private UserAccount user;
    private String url = "https://trezentos-api.herokuapp.com/api/user/login";

    public SignInRequest(UserAccount user){
        this.user = user;
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
        builder.addQueryParameter("password", user.getPassword());

        return builder.build().toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
