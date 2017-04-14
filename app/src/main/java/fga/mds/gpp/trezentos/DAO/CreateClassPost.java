package fga.mds.gpp.trezentos.DAO;

import android.os.AsyncTask;

import java.io.IOException;

import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class CreateClassPost extends AsyncTask<String, String, String>{

    private UserAccount user;
    private UserClass userClass;

    private String url = "https://trezentos-api.herokuapp.com/api/class/register";

    public CreateClassPost( UserClass userClass){
        this.userClass = userClass;
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
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getUrlWithParameters() {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        builder.addQueryParameter("name", userClass.getClassName());
        builder.addQueryParameter("institution", userClass.getInstitution());
        builder.addQueryParameter("passingScore", String.valueOf(userClass.getCutOff()));
        builder.addQueryParameter("additionScore", String.valueOf(userClass.getAddition()));
        builder.addQueryParameter("password", userClass.getPassword());
        builder.addQueryParameter("students", "");
        builder.addQueryParameter("numberOfStudentsPerGroup", String.valueOf(userClass.getSizeGroups()));

        return builder.build().toString();

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }



}




