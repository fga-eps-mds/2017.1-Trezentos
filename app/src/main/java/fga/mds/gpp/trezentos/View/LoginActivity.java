package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Model.UserException;
import fga.mds.gpp.trezentos.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private UserDialog dialog = new UserDialog();
    private UserAccountControl userAccountControl = new UserAccountControl(this);

    private String activityName = this.getClass().getSimpleName();
    private Handler mHandler = new Handler();

    private LoginButton loginFacebook;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        dialog.setContext(this);

        final Button login = (Button) findViewById(R.id.buttonLogin);
        Button register = (Button) findViewById(R.id.buttonRegister);
        Button forgotPass = (Button) findViewById(R.id.buttonForgotPassword);
        final EditText email = (EditText) findViewById(R.id.editTextEmail);
        final EditText password = (EditText) findViewById(R.id.editTextPassword);

        callbackManager = CallbackManager.Factory.create();

        loginFacebook = (LoginButton) findViewById(R.id.buttonSignInFacebook);
        loginFacebook.setReadPermissions(Arrays.asList("email", "public_profile"));

        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
                Log.d(TAG, "Button Login facebook clicado");

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                //Implementar aqui parte da verificação do login
                Log.d(TAG,"Button Login clicado");
                //dialog.setProgressMessage("Carregando...");
                //dialog.execute();

                //LOG
                Log.d(TAG, email.getText().toString());
                Log.d(TAG, password.getText().toString());

                try{
                    userAccountControl.insertModelUser(0, email.getText().toString(), password.getText().toString());

                }
                catch(UserException userException){
                    String errorMessage = userException.getMessage();


                    if(errorMessage.equals("O email não pode estar vazio")){
                        email.requestFocus();
                        email.setError("O email não pode estar vazio");
                    }

                    if(errorMessage.equals("Digite um email de até 30 caracteres")){
                        email.requestFocus();
                        email.setError("Digite um email de até 30 caracteres");
                    }

                    if(errorMessage.equals("A senha não pode estar vazia")){
                        password.requestFocus();
                        password.setError("A senha não pode estar vazia");
                    }

                    if(errorMessage.equals("Digite uma senha de até 20 caracteres")){
                        password.requestFocus();
                        password.setError("Digite uma senha de até 20 caracteres");
                    }


                }


            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Levar o usuario para a tela de cadastro
//              Intent intent = new Intent(LoginActivityActivity.this, RegisterActivity.class);
//              startActivity(intent);
                Log.d(TAG, "Button Registar clicado");
                //dialog.alert("Falha na Autenticação", "Tente novamente...");

            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotIntent);

                Log.d(TAG, "Button Forgot Password clicado");
                //dialog.alert("Falha na Autenticação", "Tente novamente...");


            }
        });
    }

    private void goMainScreen() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
        Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}

