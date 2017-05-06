package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.DAO.SignInRequest;
import fga.mds.gpp.trezentos.DAO.SignUpRequest;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Util.PasswordUtil;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.UserDialog;

import static fga.mds.gpp.trezentos.R.id.edit_text_email_register;
import static fga.mds.gpp.trezentos.R.id.edit_text_name_register;
import static fga.mds.gpp.trezentos.R.id.edit_text_password_confirmation;
import static fga.mds.gpp.trezentos.R.id.edit_text_password_register;

public class UserAccountControl {

    private static UserAccountControl instance;
    private final Context context;
    public UserAccount userAccount;

    private UserAccountControl(final Context context){
        this.context = context;
    }

    public static UserAccountControl getInstance(final Context context){
        if (instance == null){
            instance = new UserAccountControl(context);
        }
        return instance;
    }

    public String validateSignUp(String name, String email, String password,
                                 String passwordConfirmation){
        String errorMessage = "";

        try{
            userAccount = new UserAccount(name, email, password, passwordConfirmation);
        }catch (UserException userException){
            errorMessage = userException.getMessage();
        }
        return errorMessage;
    }

    public String validateSignUpResponse (){
        SignUpRequest signUpRequest = new SignUpRequest(userAccount, false);
        String serverResponse = "404";

        try{
            serverResponse = signUpRequest.execute().get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (ExecutionException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    public void authenticateLoginFb(JSONObject object){
        try{
            String Name = object.getString("name");
            String FEmail = object.getString("email");

            UserAccount userAccount = getUserWithInfo(FEmail, Name);

            SignUpRequest signUprequest = new SignUpRequest(userAccount, true);
            signUprequest.execute();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private UserAccount getUserWithInfo(String email, String name){
        UserAccount userAccount = new UserAccount();

        try{
            userAccount.setEmail(email);
            userAccount.setName(name);
        }catch (UserException e){
            e.printStackTrace();
        }
        return userAccount;
    }

    public String authenticateLogin(String email, String password){
        String errorMessage = "";

        try{
            userAccount = new UserAccount();
            //Verify email
            userAccount.setEmail(email);
            //Verify the password
            userAccount.authenticatePassword(password);
        }catch (UserException userException){
            errorMessage = userException.getMessage();
        }

        return errorMessage;
    }

    public String validateSignInResponse(){
        SignInRequest signInRequest = new SignInRequest(userAccount);
        String serverResponse = "404";

        try{
            serverResponse = signInRequest.execute().get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

        return serverResponse;
    }

    public void validatePassword(String serverResponse, String password){
        JSONObject object = getObjectFromServerResponse(serverResponse);
        String hashedPassword = null;
        String salt = null;

        try{
            hashedPassword = object.getString("password");
            Log.d("Password", hashedPassword);
            salt = object.getString("salt");
            Log.d("Password", salt);
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (PasswordUtil.decryptPass(hashedPassword, salt, password)){
            logInUser();
        }
        else{
            logOutUser();
        }
    }

    private JSONObject getObjectFromServerResponse(String serverResponse){
        JSONObject object = null;

        try{
            object = new JSONObject(serverResponse);
        }catch (JSONException e){
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
}