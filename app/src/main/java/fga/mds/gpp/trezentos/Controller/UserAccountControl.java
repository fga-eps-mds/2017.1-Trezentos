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


    private UserAccountControl(final Context context){
        this.context = context;
    }

    public static UserAccountControl getInstance(final Context context){
        if(instance == null){
            instance = new UserAccountControl(context);
        }
        return instance;
    }

    //Sign-up
    public String validateSignUp(String firstName, String lastName, String email, String telephoneDDI,
                                 String telephoneDDD, String telephoneNumber, String password, String passwordConfirmation){

        try{
            userAccount = new UserAccount(  firstName,
                                            lastName,
                                            email,
                                            telephoneDDI,
                                            telephoneDDD,
                                            telephoneNumber,
                                            password,
                                            passwordConfirmation);
        }catch(UserException userException){
            return userException.getMessage();
        }

        return "";
    }

    public String validateSignUpResponse(){

        RequestHandler requestHandler = new RequestHandler(URLs.URL_REGISTER, getSignUpParams( false));

        String serverResponse = "404";

        try{
            serverResponse = requestHandler.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }
        Log.d("RESPONSE", serverResponse);
        return serverResponse;
    }

    private HashMap<String, String>  getSignUpParams(Boolean isFromFacebook) {

        HashMap<String, String> params = new HashMap<>();
        params.put("PersonFirstName", userAccount.getFisrtName());
        params.put("PersonLastName", userAccount.getLastName());
        params.put("PersonEmail", userAccount.getEmail());
        params.put("PersonPassword", userAccount.getPassword());
        params.put("PersonIsFromFacebook", isFromFacebook.toString());
        params.put("PersonTelephoneDDI", userAccount.getTelephoneDDI());
        params.put("PersonTelephoneDDD", userAccount.getTelephoneDDD());
        params.put("PersonTelephoneNumber", userAccount.getTelephoneNumber());

        return params;

    }
    //End Sign-up



    //Sign-in
    public String authenticateSignIn(String email, String password){
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
        RequestHandler requestHandler = new RequestHandler(URLs.URL_LOGIN, getSignInParams(false));
        String serverResponse = "404";

        try{
            serverResponse = requestHandler.execute().get();
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch(ExecutionException e){
            e.printStackTrace();
        }
        Log.d("RESPONSE", serverResponse);
        return serverResponse;
    }

    private HashMap<String, String>  getSignInParams(Boolean isFromFacebook) {

        HashMap<String, String> params = new HashMap<>();
        params.put("PersonEmail", userAccount.getEmail());
        params.put("PersonPassword", userAccount.getPassword());

        return params;

    }

    public void createPerson(String serverResponse) throws UserException, JSONException {
        JSONObject object = getObjectFromServerResponse(serverResponse);
        JSONObject userJson = object.getJSONObject("person");

        try {
            userAccount.setId(userJson.getString("idPerson"));
            userAccount.setFirstName(userJson.getString("PersonFirstName"));
            userAccount.setLastName(userJson.getString("PersonLastName"));
            userAccount.setEmail(userJson.getString("PersonEmail"));
            userAccount.setTelephoneDDI(userJson.getString("PersonTelephoneDDI"));
            userAccount.setTelephoneDDD(userJson.getString("PersonTelephoneDDD"));
            userAccount.setTelephoneNumber(userJson.getString("PersonTelephoneNumber"));
            userAccount.setIsFromFacebook(userJson.getBoolean("PersonIsFromFacebook"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //End Sign-in



    //Sign-in Facebook TODO
    public void authenticateSignInFb(JSONObject object){
        try{
            String name = object.getString("PersonFirstName");
            String fEmail = object.getString("PersonEmail");
            UserAccount fbUserAccount = new UserAccount();
            fbUserAccount.setEmail(fEmail);
            //fbUserAccount.setName(name);


        }catch(JSONException | UserException e){
            e.printStackTrace();
        }
    }

    public void signInUserFromFacebook(JSONObject object){
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
    //End Sign-in Facebook


    //Reset Password
    public String validateResetPasswordResponse(String recoverEmail){

        RequestHandler requestHandler = new RequestHandler(URLs.URL_RESET_PASSWORD, getResetPasswordParams(recoverEmail));

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

    public HashMap<String, String>  getResetPasswordParams(String recoverEmail) {

        HashMap<String, String> params = new HashMap<>();

        params.put("PersonEmail", recoverEmail);

        return params;

    }
    //End Reset Password


    //Common
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
                .putString("userId", userAccount.getId())
                .putString("userEmail", userAccount.getEmail())
                .putString("userFirstName", userAccount.getFisrtName())
                .putString("userLastName", userAccount.getLastName())
                .putString("userTelephoneDDI", userAccount.getTelephoneDDI())
                .putString("userTelephoneDDD", userAccount.getTelephoneDDD())
                .putString("userTelephoneNumber", userAccount.getTelephoneNumber())
                .apply();

    }

    public void logOutUser(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);

        session.edit()
                .putBoolean("IsUserLogged", false)
                .putString("userFirstName", "")
                .putString("userLastName", "")
                .putString("userEmail", "")
                .putString("userTelephoneDDI", "")
                .putString("userTelephoneDDD", "")
                .putString("userTelephoneNumber", "")
                .apply();
    }

    public boolean isLoggedUser(){
        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
        return session.getBoolean("IsUserLogged", false);
    }
    //End Common

}