package fga.mds.gpp.trezentos.DAO;


import android.os.AsyncTask;
import java.io.IOException;

import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserAccount;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateExamPost extends AsyncTask<String, String, String> {
    private UserAccount user;
    private Exam exam;

    private String url = "https://trezentos-api.herokuapp.com/api/exam/register";

    public CreateExamPost(Exam exam){
        this.exam = exam;
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
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String getUrlWithParameters(){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        builder.addQueryParameter("name", exam.getNameExam());
        builder.addQueryParameter("userClassName", exam.getUserClassName());
        builder.addQueryParameter("classOwnerEmail", exam.getClassOwnerEmail());

        return builder.build().toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}