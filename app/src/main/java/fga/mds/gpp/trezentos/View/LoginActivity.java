package fga.mds.gpp.trezentos.View;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Arrays;
import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;

public class LoginActivity extends AppCompatActivity{

    private static final String TAG = "LoginActivity";
    private UserDialog dialog = new UserDialog();
    String activityName = this.getClass().getSimpleName();
    Handler mHandler = new Handler();
    private CallbackManager callbackManager;
    private LoginButton loginFacebook;
    private UserAccountControl userAccountControl;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        dialog.setContext(this);

        final Button login = (Button) findViewById(R.id.button_login);

        Button register = (Button) findViewById(R.id.button_register);
        Button about = (Button) findViewById(R.id.button_about);

        final EditText email = (EditText) findViewById(R.id.edit_text_email);
        final EditText password = (EditText) findViewById(R.id.edit_text_password);

        callbackManager = CallbackManager.Factory.create();

        loginFacebook = (LoginButton) findViewById(R.id.button_sign_in_facebook);
        loginFacebook.setReadPermissions(Arrays.asList("email", "public_profile"));
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){

            @Override
            public void onSuccess(LoginResult loginResult){
                facebookLogin(loginResult);
                goMainScreen();
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

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Log.d(TAG, email.getText().toString());
                Log.d(TAG, password.getText().toString());

                userAccountControl = UserAccountControl.getInstance(getApplicationContext());
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                String errorMessage = userAccountControl.authenticateLogin(emailString,
                        passwordString);

                if(errorMessage.isEmpty()){
                    String serverResponse = userAccountControl.validateSignInResponse();
                    try {
                        validatePasswordAndLogsUser(serverResponse, passwordString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (UserException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else{
                    loginErrorMessage(errorMessage, email, password);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to register screen
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                startActivity(intent);
                finish();
            }
        });

        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent showAbout = new Intent(getApplicationContext(), AboutOnLogin.class);
                startActivity(showAbout);
            }
        });
    }

    private void validatePasswordAndLogsUser(String serverResponse, String passwordString) throws JSONException, UserException {
        JSONObject responseJson = new JSONObject(serverResponse);
        if(responseJson.getString("name") != null) {
            userAccountControl.validatePassword(serverResponse, passwordString);
            goToMain(serverResponse);
        } else {
            Toast.makeText(getApplicationContext(), R.string.invalid_login, Toast.LENGTH_SHORT).show();
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

    private void goToMain(String response){
        if (response.contains("true")){
            Intent goToMain = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(goToMain);
        }
        else{
            String tryAgain = "Email ou Senha inválidos, por favor " +
                    "tente novamente";
            Toast.makeText(getApplicationContext(), tryAgain, Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}