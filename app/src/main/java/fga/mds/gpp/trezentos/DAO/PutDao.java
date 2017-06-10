package fga.mds.gpp.trezentos.DAO;
import android.app.job.JobInfo;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;

import okhttp3.*;

public class PutDao extends AsyncTask<String, String, String> {
    private String url;
    private MediaType mediaType;
    private String stringBody;

    public PutDao(String url, MediaType mediaType, String stringBody){
        this.url = url;
        this.mediaType = mediaType;
        this.stringBody = stringBody;
    }

    @Override
    public String doInBackground(String... params){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, stringBody);

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .put(body)
                .build();
        try{
            Response response = client.newCall(request).execute();
            Log.d("SERVERRESPONSEDAO", response.body().toString());
            return response.body().string();
        }catch (IOException e){
            e.printStackTrace();
            Log.i("LOG", "IOException in doInBackground method");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}
