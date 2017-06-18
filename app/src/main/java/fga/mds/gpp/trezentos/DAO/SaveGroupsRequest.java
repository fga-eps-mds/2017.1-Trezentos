package fga.mds.gpp.trezentos.DAO;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fga.mds.gpp.trezentos.Model.Evaluation;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SaveGroupsRequest extends AsyncTask<String, String, String>{

    private String email;
    private String userClassName;
    private String name;
    private String groups;
    private String url = "https://trezentos-api.herokuapp.com/api/exam/groups";

    public SaveGroupsRequest(String email, String userClassName, String name, String groups) {
        this.email = email;
        this.userClassName = userClassName;
        this.name = name;
        this.groups = groups;
    }

    @Override
    protected String doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();

        String json = getJsonBody();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("LOG", "IOException in doInBackground method");
        }

        return null;
    }

    private String getJsonBody() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", email);
            jsonObject.put("userClassName", userClassName);
            jsonObject.put("name", name);
            jsonObject.put("groups", groups);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
}
