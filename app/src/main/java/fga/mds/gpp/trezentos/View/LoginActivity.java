package fga.mds.gpp.trezentos.View;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookBroadcastReceiver;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "LoginActivity";
    private CallbackManager callbackManager;
    private LoginButton loginFacebook;
    private UserAccountControl userAccountControl;

    private EditText emailEditText = null;
    private EditText passwordEditText = null;

    private Button login = null;
    private Button forgotPassword = null;
    private Button register = null;
    private Button about = null;

    private ProgressBar progressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initView();
        facebookAPI();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.button_login:

                if(isNetworkAvailable(getApplicationContext()) && isOnline()){
                    

                    userAccountControl = UserAccountControl.getInstance(getApplicationContext());

                    String emailString = emailEditText.getText().toString();
                    String passwordString = passwordEditText.getText().toString();

                    String errorMessage = userAccountControl.authenticateLogin(emailString,
                            passwordString);

                    if(errorMessage.isEmpty()){
                        progressBar.setVisibility(View.VISIBLE);
                        String serverResponse = userAccountControl.validateSignInResponse();

                        try {
                            validatePasswordAndLogsUser(serverResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UserException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);
                    } else{
                        loginErrorMessage(errorMessage, emailEditText, passwordEditText);
                    }

                }else{
                    Toast.makeText(this, "Verifique sua conexão com a internet e tente novamente", Toast.LENGTH_LONG).show();
                }


                break;

            case R.id.button_forgot_password:
                // Go to forgot password screen
                Intent showForgotPassword = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(showForgotPassword);
                break;

            case R.id.button_register:
                // Go to register screen
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;

            case R.id.button_about:
                // Go to about screen
                Intent showAbout = new Intent(getApplicationContext(), AboutOnLogin.class);
                startActivity(showAbout);
                break;
        }
    }

    private void facebookAPI(){
        callbackManager = CallbackManager.Factory.create();
        loginFacebook = (LoginButton) findViewById(R.id.button_sign_in_facebook);
        loginFacebook.setReadPermissions(Arrays.asList("email", "public_profile"));
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){

            @Override
            public void onSuccess(LoginResult loginResult){
                facebookLogin(loginResult);
                changeRouteToMain();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.msg_cancel_login,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e){
                Toast.makeText(getApplicationContext(), R.string.msg_error_login,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(){
        login = (Button) findViewById(R.id.button_login);
        forgotPassword = (Button) findViewById(R.id.button_forgot_password);
        register = (Button) findViewById(R.id.button_register);
        about = (Button) findViewById(R.id.button_about);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        emailEditText = (EditText) findViewById(R.id.edit_text_email);
        passwordEditText = (EditText) findViewById(R.id.edit_text_password);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        about.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
    }

    private void validatePasswordAndLogsUser(String serverResponse) throws JSONException, UserException {
        JSONObject responseJson = new JSONObject(serverResponse);

        if(!responseJson.getBoolean("error")) {
            userAccountControl.validatePerson(serverResponse);
            changeRouteToMain();
        } else {
            Toast.makeText(getApplicationContext(), responseJson.getString("message"), Toast.LENGTH_SHORT).show();
        }
    }

    private void facebookLogin(LoginResult loginResult){
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback(){
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response){
                        JSONObject jsonObject = response.getJSONObject();
                        UserAccountControl userAccountControl = UserAccountControl
                                .getInstance(getApplicationContext());
                        userAccountControl.authenticateLoginFb(object);
                        userAccountControl.logInUserFromFacebook(jsonObject);
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void loginErrorMessage(String errorMessage, EditText email, EditText password){
        if(errorMessage.equals(getString(R.string.msg_len_email_error_message))){
            email.requestFocus();
            email.setError("Email inválido. Tente novamente");
        }

        if(errorMessage.equals(getString(R.string.msg_special_characters_email_error_message))){
            email.requestFocus();
            email.setError("Email inválido. Tente novamente");
        }

        if(errorMessage.equals(getString(R.string.msg_null_email_error_message))){
            email.requestFocus();
            email.setError(getString(R.string.msg_null_email_error_message));
        }

        if(errorMessage.equals(getString(R.string.msg_len_password_error_message))){
            password.requestFocus();
            password.setError("Senha inválida. Tente Novamente");
        }

        if(errorMessage.equals(getString(R.string.msg_null_password_error_message))){
            password.requestFocus();
            password.setError(getString(R.string.msg_null_password_error_message));
        }
    }

    private void changeRouteToMain() {
        Intent intentGoMainActivity = new Intent(LoginActivity.this, MainActivity.class);

        intentGoMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentGoMainActivity);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));

        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /*
    private boolean isInternetAvailable() {
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            if(!address.equals("")){
                return true;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return false;
    }
    */

    /* TEST IT IN REAL DEVISE */
}