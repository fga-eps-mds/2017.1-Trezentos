//package fga.mds.gpp.trezentos.DAO;
//
//import android.util.Log;
//
//import java.io.IOException;
//
//import fga.mds.gpp.trezentos.Model.UserAccount;
//import okhttp3.HttpUrl;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class GetEvaluationRequest {
//
//    private UserAccount userAccount;
//    private final String url = "https://trezentos-api.herokuapp.com/api/user/rateToDo";
//
//    public GetEvaluationRequest(UserAccount userAccount){
//        this.userAccount = userAccount;
//    }
//
//    public String get(){
//        OkHttpClient client = new OkHttpClient();
//
//        String urlWithParameters = getUrlWithParameters();
//
//        Request request = new Request.Builder()
//                .url(urlWithParameters)
//                .build();
//
//        try{
//            Response response = client.newCall(request).execute();
//            return response.body().string();
//        }catch (IOException e){
//            e.printStackTrace();
//            Log.i("LOG", "IOException in doInBackground method");
//        }
//        return null;
//    }
//
//    private String getUrlWithParameters(){
//        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
//
//        builder.addQueryParameter("email", userAccount.getEmail());
//
//        return builder.build().toString();
//    }
//
//}
