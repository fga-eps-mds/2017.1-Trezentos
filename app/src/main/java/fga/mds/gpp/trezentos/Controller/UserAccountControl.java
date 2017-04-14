package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.DAO.SignInRequest;
import fga.mds.gpp.trezentos.DAO.SignUpRequest;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.View.UserDialog;

public class UserAccountControl {

    private static UserAccountControl instance;
    private final Context context;
    public UserAccount userAccount;


    private UserAccountControl(final Context context) {
        this.context = context;
    }

    public static UserAccountControl getInstance(final Context context){
        if (instance == null){
            instance = new UserAccountControl(context);
        }
        return instance;
    }

    public String validateSignUp(String name, String email, String password,
                               String passwordConfirmation) throws UserException {

        userAccount = new UserAccount(name, email, password, passwordConfirmation);

        SignUpRequest signUpRequest = new SignUpRequest(userAccount, false);

        String serverResponse = "404";

        try {
            serverResponse = signUpRequest.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return serverResponse;



    }

    public void insertModelUserFacebook(String email, String name) {

        UserAccount userAccount = getUserWithInfo(email, name);

        SignUpRequest signUprequest = new SignUpRequest(userAccount, true);
        signUprequest.execute();

    }

    private UserAccount getUserWithInfo(String email, String name) {
        UserAccount userAccount = new UserAccount();

        try {
            userAccount.setEmail(email);
            userAccount.setName(name);
        } catch (UserException e) {
            e.printStackTrace();
        }

        return userAccount;
    }

    public String insertModelUser(String email, String password) throws UserException {
        userAccount = new UserAccount();

        //Verify email
        userAccount.setEmail(email);

        //Verify the password
        userAccount.setPasswordConfirmation(password);
        userAccount.setPassword(password);

        SignInRequest signInRequest = new SignInRequest(userAccount);

        String serverResponse = "404";

        try {
            serverResponse = signInRequest.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (serverResponse.contains("true")){
            logInUser();
        }
        else{
            logOutUser();
        }

        return serverResponse;
    }

    public void logInUser() {
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