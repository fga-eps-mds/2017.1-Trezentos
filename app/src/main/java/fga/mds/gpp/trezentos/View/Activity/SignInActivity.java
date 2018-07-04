package fga.mds.gpp.trezentos.View.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.AboutOnLogin;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SignInActivity";
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

                userAccountControl = UserAccountControl.getInstance(getApplicationContext());

                if(userAccountControl.isNetworkAvailable()){

                    String emailString = emailEditText.getText().toString();
                    String passwordString = passwordEditText.getText().toString();

                    String errorMessage = userAccountControl.authenticateSignIn(emailString, passwordString);

                    if(errorMessage.isEmpty()){
                        progressBar.setVisibility(View.VISIBLE);
                        String serverResponse = userAccountControl.validateSignInResponse();

                        try {
                            validateServerResponse(serverResponse);
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
                    Toast.makeText(
                            this,
                            "Verifique sua conexão com a internet e tente novamente",
                            Toast.LENGTH_LONG
                    ).show();
                }


                break;

            case R.id.button_forgot_password:
                // Go to forgot password screen
                Intent showForgotPassword = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(showForgotPassword);
                break;

            case R.id.button_register:
                // Go to register screen
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;

            case R.id.button_about:
                // Go to about screen
                Intent showAbout = new Intent(getApplicationContext(), AboutOnLogin.class);
                startActivity(showAbout);
                break;
        }
    }

    private void validateServerResponse(String serverResponse) throws JSONException, UserException {
        JSONObject responseJson = new JSONObject(serverResponse);

        if(!responseJson.getBoolean("error")) {
            userAccountControl.createPerson(serverResponse);
            userAccountControl.logInUser();
            goToMain();
        } else {
            userAccountControl.logOutUser();
            Toast.makeText(getApplicationContext(), responseJson.getString("message"), Toast.LENGTH_SHORT).show();
        }
    }



    private void facebookAPI(){
        callbackManager = CallbackManager.Factory.create();
        loginFacebook = findViewById(R.id.button_sign_in_facebook);
        loginFacebook.setReadPermissions(Arrays.asList("email", "public_profile"));
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){

            @Override
            public void onSuccess(LoginResult loginResult){
                facebookLogin(loginResult);
                goToMain();
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
        login = findViewById(R.id.button_login);
        forgotPassword = findViewById(R.id.button_forgot_password);
        register = findViewById(R.id.button_register);
        about = findViewById(R.id.button_about);

        progressBar = findViewById(R.id.progressBar);

        emailEditText = findViewById(R.id.edit_text_email);
        passwordEditText = findViewById(R.id.edit_text_password);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        about.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
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
                        userAccountControl.authenticateSignInFb(object);
                        userAccountControl.signInUserFromFacebook(jsonObject);
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

    private void goToMain() {
        Intent intentGoMainActivity = new Intent(SignInActivity.this, MainActivity.class);

        intentGoMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentGoMainActivity);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}