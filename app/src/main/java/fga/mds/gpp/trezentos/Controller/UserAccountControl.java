package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.logging.Logger;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.DAO.PostDao;
import fga.mds.gpp.trezentos.DAO.RequestHandler;
import fga.mds.gpp.trezentos.DAO.URLs;
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
                                 String passwordConfirmation, String telephoneDDI,
                                 String telephoneDDD, String telephoneNumber){
        try{
            userAccount = new UserAccount(name, email, password, passwordConfirmation, telephoneDDI, telephoneDDD, telephoneNumber);
        }catch(UserException userException){
            return userException.getMessage();
        }

        return "";
    }

    public String validateSignUpResponse(){

        RequestHandler requestHandler = new RequestHandler(URLs.URL_REGISTER, getRegisterParams(userAccount, false));

        String serverResponse = "404";

        try{
            serverResponse = requestHandler.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }
        Log.d("RESPONSE", ""+serverResponse);
        return serverResponse;
    }

    public String validateForgotPasswordResponse(String recoverEmail){

        RequestHandler requestHandler = new RequestHandler(URLs.URL_RESET_PASSWORD, getForgotPasswordParams(recoverEmail));

        String serverResponse = "";

        try{
            serverResponse = requestHandler.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }
        Log.d("RESPONSE", ""+serverResponse);
        return serverResponse;
    }


    public HashMap<String, String>  getLoginParams(UserAccount userAccount, Boolean isFromFacebook) {

        HashMap<String, String> params = new HashMap<>();
        params.put("PersonEmail", userAccount.getEmail());
        params.put("PersonPassword", userAccount.getPassword());

        return params;

    }

    public HashMap<String, String>  getRegisterParams(UserAccount userAccount, Boolean isFromFacebook) {

        HashMap<String, String> params = new HashMap<>();
        params.put("PersonName", userAccount.getName());
        params.put("PersonEmail", userAccount.getEmail());
        params.put("PersonPassword", userAccount.getPassword());
        params.put("PersonIsFromFacebook", isFromFacebook.toString());
        params.put("PersonTelephoneDDI", userAccount.getTelephoneDDI());
        params.put("PersonTelephoneDDD", userAccount.getTelephoneDDD());
        params.put("PersonTelephoneNumber", userAccount.getTelephoneNumber());

        return params;

    }

    public HashMap<String, String>  getForgotPasswordParams(String recoverEmail) {

        HashMap<String, String> params = new HashMap<>();

        params.put("PersonEmail", recoverEmail);

        return params;

    }


    public void authenticateLoginFb(JSONObject object){
        try{
            String name = object.getString("PersonName");
            String fEmail = object.getString("PersonEmail");
            UserAccount fbUserAccount = new UserAccount();
            fbUserAccount.setEmail(fEmail);
            fbUserAccount.setName(name);

            //String urlWithParameters = getUserUrl(fbUserAccount, true);
            //PostDao postDao = new PostDao(urlWithParameters, null, "");

            //postDao.execute();
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

        RequestHandler requestHandler = new RequestHandler(URLs.URL_LOGIN, getLoginParams(userAccount, false));

        String serverResponse = "404";

        try{
            serverResponse = requestHandler.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }

        return serverResponse;
    }

    // Method that creates a url with parameters and sends it to api, it returns a response if it worked or not
    private String getSignInUrl(UserAccount userAccount){
        String url = "metodo300.com/android/api/user/login.php";

        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.addQueryParameter("PersonEmail", userAccount.getEmail());
        builder.addQueryParameter("PersonPassword", userAccount.getPassword());

        return builder.build().toString();
    }


    public void validatePassword(String serverResponse, String password) throws UserException, JSONException {
        JSONObject object = getObjectFromServerResponse(serverResponse);

        JSONObject userJson = object.getJSONObject("person");

        //creating a new user object
                /*
                User user = new User(
                        userJson.getInt("id"),
                        userJson.getString("username"),
                        userJson.getString("email"),
                        userJson.getString("gender")
                );
                */
        String hashedPassword = null, salt = null;
        /*
        try{
            hashedPassword = object.getString("password");
            //salt = object.getString("salt");
        }catch(JSONException e){
            e.printStackTrace();
        }
        */
        try {

            userAccount.setName(userJson.getString("PersonName"));
            userAccount.setEmail(userJson.getString("PersonEmail"));
            userAccount.setIsFromFacebook(userJson.getBoolean("PersonIsFromFacebook"));
            userAccount.setTelephoneDDI(userJson.getString("PersonTelephoneDDI"));
            userAccount.setTelephoneDDD(userJson.getString("PersonTelephoneDDD"));
            userAccount.setTelephoneNumber(userJson.getString("PersonTelephoneNumber"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(!object.getBoolean("error")){
            logInUser();
        }else{
            logOutUser();
        }


        /*
        if(PasswordUtil.decryptPass(hashedPassword, salt, password)){
            logInUser();
        }else{
            logOutUser();
            throw new UserException(context.getString(R.string.invalid_login));
        }
        */

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