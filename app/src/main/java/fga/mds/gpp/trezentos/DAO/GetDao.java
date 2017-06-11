package fga.mds.gpp.trezentos.DAO;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetDao {
    private String url;

    public GetDao(String url){
        this.url = url;
    }

    public String get(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try{
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
