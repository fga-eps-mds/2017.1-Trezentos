//package fga.mds.gpp.trezentos.DAO;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import fga.mds.gpp.trezentos.Model.UserAccount;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class SendEvaluationRequest extends AsyncTask<String, String, String> {
//
//    private UserAccount userAccount;
//    private JSONObject evaluation;
//    private String url = "https://trezentos-api.herokuapp.com/api/user/rateToDo";
//
//    public SendEvaluationRequest(UserAccount userAccount, JSONObject evaluation){
//        this.userAccount = userAccount;
//        this.evaluation = evaluation;
//    }
//
//    @Override
//    protected String doInBackground(String... params) {
//
//        OkHttpClient client = new OkHttpClient();
//
//        String json = getJsonBody();
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            return response.body().string();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.i("LOG", "IOException in doInBackground method");
//        }
//
//        return null;
//    }
//
//    private String getJsonBody(){
//
//        JSONObject jsonObject = new JSONObject();
//
//        try {
//            jsonObject.put("email", userAccount.getEmail());
//            jsonObject.put("rateToDo", evaluation);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Log.d("JSONARRAY", jsonObject.toString());
//
//        return jsonObject.toString();
//    }
//
//}
