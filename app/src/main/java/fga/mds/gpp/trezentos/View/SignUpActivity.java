package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.R;

import com.facebook.login.widget.LoginButton;

import fga.mds.gpp.trezentos.R;

import static fga.mds.gpp.trezentos.R.id.editTextEmailRegister;
import static fga.mds.gpp.trezentos.R.id.editTextNameRegister;
import static fga.mds.gpp.trezentos.R.id.editTextPasswordConfirmation;
import static fga.mds.gpp.trezentos.R.id.editTextPasswordRegister;
import static fga.mds.gpp.trezentos.R.id.sign_up_button;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private UserAccountControl userAccountControl = new UserAccountControl(this);
    private static final String TAG = "SignUpActivity";
    private UserDialog dialog = new UserDialog();

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText passwordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUp = (Button) findViewById(R.id.sign_up_button);

        name = (EditText) findViewById(editTextNameRegister);
        email = (EditText) findViewById(editTextEmailRegister);
        password = (EditText) findViewById(editTextPasswordRegister);
        passwordConfirmation = (EditText) findViewById(editTextPasswordConfirmation);


        signUp.setOnClickListener(this);

        Button alreadySignUp = (Button) findViewById(R.id.already_sign_up);
        alreadySignUp.setOnClickListener(this);

    }



    // Method for confirmation

    public void confirmInformation(UserAccountControl userAccountControl, EditText name, EditText email,
                                   EditText password, EditText passwordConfirmation){

        try {
            userAccountControl.validateInformation(name.getText().toString(), email.getText().toString(),
                    password.getText().toString(), passwordConfirmation.getText().toString());
        }catch (UserException userException) {

            String errorMessage = userException.getMessage();

            if(errorMessage.equals("O nome deve ter de 3 a 50 caracteres.")){
                name.requestFocus();
                name.setError("O nome deve ter de 3 a 50 caracteres.");
            }

            if(errorMessage.equals("A senha deve ter de 6 a 16 caracteres.")){
                password.requestFocus();
                password.setError("A senha deve ter de 6 a 16 caracteres.");
            }

            if(errorMessage.equals("Preencha todos os campos!")){

                name.setError("Preencha todos os campos!");
            }

            if(errorMessage.equals("Senhas não Coincidem. Tente novamente.")){
                passwordConfirmation.requestFocus();
                passwordConfirmation.setError("Senhas não Coincidem. Tente novamente.");
            }

            if(errorMessage.equals("A senha deve ter ao menos um caracter maiusculo.")){
                password.requestFocus();
                password.setError("A senha deve ter ao menos um caracter maiusculo.");
            }
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.already_sign_up:{
                Intent returnToLogin = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(returnToLogin);
                finish();
                break;

            }

            case R.id.sign_up_button:{
                confirmInformation(userAccountControl, name, email, password, passwordConfirmation);

                //Confirma dados usuário
                userAccountControl.insertModelUserRegister(0, name.getText().toString()
                        ,email.getText().toString(), password.getText().toString());

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                Log.d(TAG, "Button Registrar clicado");
                dialog.alert("Falha na Autenticação", "Dados inválidos");
            }
        }
    }
}
