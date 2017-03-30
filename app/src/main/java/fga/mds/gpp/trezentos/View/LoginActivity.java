package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.R;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private UserDialog dialog = new UserDialog();
    private UserAccountControl userAccountControl = new UserAccountControl(this);

    private String activityName = this.getClass().getSimpleName();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog.setContext(this);

        Button login = (Button) findViewById(R.id.buttonLogin);
        Button loginFacebook = (Button) findViewById(R.id.buttonSignInFacebook);
        Button register = (Button) findViewById(R.id.buttonRegister);
        Button forgotPass = (Button) findViewById(R.id.buttonForgotPassword);

        final EditText email = (EditText) findViewById(R.id.editTextEmail);
        final EditText password = (EditText) findViewById(R.id.editTextPassword);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Implementar aqui parte da verificação do login
                Log.d(TAG,"Button Login clicado");
                //dialog.setProgressMessage("Carregando...");
                //dialog.execute();
                userAccountControl.insertModelUser(0, email.getText().toString(), password.getText().toString());

                //LOG
                Log.d(TAG, email.getText().toString());
                Log.d(TAG, password.getText().toString());


            }
        });

        loginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Button Login facebook clicado");
                //dialog.alert("Falha na Autenticação", "Tente novamente...");

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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
