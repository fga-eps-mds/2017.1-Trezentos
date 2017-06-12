package fga.mds.gpp.trezentos.DAO;


import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.UserAccount;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SaveRatePost extends AsyncTask<String, String, String> {



    private UserAccount user;
    private Evaluation evaluation;

    private String url = "https://trezentos-api.herokuapp.com/api/user/rate";

    public SaveRatePost(Evaluation evaluation){
        this.evaluation = evaluation;

    }

    @Override
    protected String doInBackground(String... params){
        OkHttpClient client = new OkHttpClient();

        String urlWithParameters = getUrlWithParameters();

        RequestBody body = RequestBody.create(null, "");
        Request request = new Request.Builder()
                .url(urlWithParameters)
                .post(body)
                .build();

        try{
            Response response = client.newCall(request).execute();
            return response.body().toString();
        }catch (IOException e){
            e.printStackTrace();
            Log.i("LOG", "IOException in doInBackground method");
        }
        return null;
    }

    private String getUrlWithParameters(){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        builder.addQueryParameter("email", evaluation.getStudentEmail());
        builder.addQueryParameter("rate", String.valueOf(evaluation.getRateEvaluation()));

        return builder.build().toString();
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}
