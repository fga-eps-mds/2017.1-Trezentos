package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    public void validateSignUp(String name, String email, String password,
                               String passwordConfirmation) throws UserException {

        userAccount = new UserAccount(name, email, password, passwordConfirmation);

        SignUpRequest signUpRequest = new SignUpRequest(userAccount);
        signUpRequest.execute();
    }

    public void insertModelUserFacebook(JSONObject object) {

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

        if (serverResponse.equals("200")){
            SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(context);
            session.edit()
                    .putBoolean("IsUserLogged", true)
                    .putString("userEmail", userAccount.getEmail())
                    .putString("userName", userAccount.getName())
                    .apply();
        }
        else{

        }

        return serverResponse;
    }


    public void updateModelUser(String name, String email, String password) throws UserException {

        userAccount.setName(name);
        userAccount.setEmail(email);
        userAccount.setPasswordConfirmation(password);
        userAccount.setPassword(password);
    }

    public String getUserAccountEmail() {
        return userAccount.getEmail();

    }

    public String getUserAccountPassword() {
        return userAccount.getPassword();

    }

    public void insert(UserAccount userAccount) {

    }

    public void authenticateLogin() {

    }

    public boolean loginValidate(String user, String password) {
        UserAccount userAccount = new UserAccount();
        if (userAccount == null || userAccount.getEmail() == null || userAccount.getPassword() == null) {
            return false;
        }

        return true;
    }


}