package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.logging.Logger;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.DAO.PostDao;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Util.PasswordUtil;
import fga.mds.gpp.trezentos.R;
import okhttp3.HttpUrl;

public class UserAccountControl {
    private static final String TAG = UserAccountControl.class.getSimpleName();

    private static UserAccountControl instance;
    final Context context;
    private UserAccount userAccount;
    private UserAccount fbUserAccount;
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    private UserAccountControl(final Context context){
        this.context = context;
    }

    public static UserAccountControl getInstance(final Context context){
        if(instance == null){
            instance = new UserAccountControl(context);
        }
        return instance;
    }

    public String validateSignUp(String name, String email, String password,
                                 String passwordConfirmation){
        try{
            userAccount = new UserAccount(name, email, password, passwordConfirmation);
        }catch(UserException userException){
            return userException.getMessage();
        }

        return "";
    }

    public String validateSignUpResponse(){
        PostDao postDao = new PostDao(getUserUrl(userAccount, false), null, "");
        String serverResponse = "404";

        try{
            serverResponse = postDao.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }

        return serverResponse;
    }

    // Method that creates a url with parameters and sends it to api, it returns a response if it worked or not
    public String getUserUrl(UserAccount userAccount, Boolean isFromFacebook) {
        String url = "https://trezentos-api.herokuapp.com/api/user/register";
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        builder.addQueryParameter("email", userAccount.getEmail());
        builder.addQueryParameter("salt", userAccount.getSalt());
        builder.addQueryParameter("password", userAccount.getPassword());
        builder.addQueryParameter("name", userAccount.getName());
        builder.addQueryParameter("facebook", isFromFacebook.toString());

        return builder.build().toString();

    }

    public void authenticateLoginFb(JSONObject object){
        try{
            String name = object.getString("name");
            String fEmail = object.getString("email");
            UserAccount fbUserAccount = new UserAccount();
            fbUserAccount.setEmail(fEmail);
            fbUserAccount.setName(name);

            String urlWithParameters = getUserUrl(fbUserAccount, true);
            PostDao postDao = new PostDao(urlWithParameters, null, "");

            postDao.execute();
        }catch(JSONException | UserException e){
            e.printStackTrace();
        }
    }

    public String authenticateLogin(String email, String password){
        try{
            userAccount = new UserAccount();
            userAccount.setEmail(email);
            userAccount.authenticatePassword(password);
        }catch(UserException userException){
            return userException.getMessage();
        }

        return "";
    }

    public String validateSignInResponse(){
        String urlWithParameters = getSignInUrl(userAccount);
        PostDao postDao = new PostDao(urlWithParameters, null, "");
        String serverResponse = "404";

        try{
            serverResponse = postDao.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }

        return serverResponse;
    }

    // Method that creates a url with parameters and sends it to api, it returns a response if it worked or not
    private String getSignInUrl(UserAccount userAccount){
        String url = "https://trezentos-api.herokuapp.com/api/user/login";

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter("email", userAccount.getEmail());
        builder.addQueryParameter("password", userAccount.getPassword());

        return builder.build().toString();
    }


    public void validatePassword(String serverResponse, String password) throws UserException {
        JSONObject object = getObjectFromServerResponse(serverResponse);
        String hashedPassword = null, salt = null;

        try{
            hashedPassword = object.getString("password");
            salt = object.getString("salt");
        }catch(JSONException e){
            e.printStackTrace();
        }
        try {
            userAccount.setName(object.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(PasswordUtil.decryptPass(hashedPassword, salt, password)){
            logInUser();
        }else{
            logOutUser();
            throw new UserException(context.getString(R.string.invalid_login));
        }
    }

    private JSONObject getObjectFromServerResponse(String serverResponse){
        JSONObject object = null;

        try{
            object = new JSONObject(serverResponse);
        }catch(JSONException e){
            e.printStackTrace();
        }

        return object;
    }

    public void logInUser(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);

        session.edit()
                .putBoolean("IsUserLogged", true)
                .putString("userEmail", userAccount.getEmail())
                .putString("userName", userAccount.getName())
                .apply();
    }

    public void logOutUser(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);

        session.edit()
                .putBoolean("IsUserLogged", false)
                .putString("userName", "")
                .apply();
    }



    public void logInUserFromFacebook(JSONObject object){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
        String nome = null, email = null;

        try {
            nome = object.getString("name");
            email = object.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        session.edit()
                .putBoolean("IsUserLogged", true)
                .putString("userEmail", email)
                .putString("userName", nome)
                .apply();
    }

}