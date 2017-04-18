package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.R;

import static fga.mds.gpp.trezentos.R.id.edit_text_email_register;
import static fga.mds.gpp.trezentos.R.id.edit_text_name_register;
import static fga.mds.gpp.trezentos.R.id.edit_text_password_confirmation;
import static fga.mds.gpp.trezentos.R.id.edit_text_password_register;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUp = (Button) findViewById(R.id.sign_up_button);
        signUp.setOnClickListener(this);

        Button alreadySignUp = (Button) findViewById(R.id.already_sign_up);
        alreadySignUp.setOnClickListener(this);

    }

    // Method for confirmation
    public void confirmInformation() {

        EditText nameEdit = (EditText) findViewById(edit_text_name_register);
        EditText emailEdit = (EditText) findViewById(edit_text_email_register);
        EditText passwordEdit = (EditText) findViewById(edit_text_password_register);
        EditText passwordConfirmationEdit = (EditText) findViewById(edit_text_password_confirmation);

        String name = nameEdit.getText().toString();
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String passwordConfirmation = passwordConfirmationEdit.getText().toString();

        UserAccountControl userAccountControl = UserAccountControl.getInstance(getApplicationContext());
        String errorMessage = userAccountControl.validateSignUp(name, email, password, passwordConfirmation);

        if (errorMessage.equals("")){
            String response = userAccountControl.validateSignUpResponse();
            goToMain(response);
        } else {
            if (errorMessage.equals(getString(R.string.msg_null_name_error_message))) {
                nameEdit.requestFocus();
                nameEdit.setError(getString(R.string.msg_null_name_error_message));
            }

            if (errorMessage.equals(getString(R.string.msg_len_name_error_message))) {
                nameEdit.requestFocus();
                nameEdit.setError(getString(R.string.msg_len_name_error_message));
            }

            if (errorMessage.equals(getString(R.string.msg_len_password_error_message))) {
                passwordEdit.requestFocus();
                passwordEdit.setError(getString(R.string.msg_len_password_error_message));
            }

            if (errorMessage.equals(getString(R.string.msg_password_conf_error_message))) {
                passwordConfirmationEdit.requestFocus();
                passwordConfirmationEdit.setError(getString(R.string.msg_password_conf_error_message));
            }

            if (errorMessage.equals(getString(R.string.msg_upper_case_error_message))) {
                passwordEdit.requestFocus();
                passwordEdit.setError(getString(R.string.msg_upper_case_error_message));
            }
            if (errorMessage.equals(getString(R.string.msg_null_email_error_message))) {
                emailEdit.requestFocus();
                emailEdit.setError(getString(R.string.msg_null_email_error_message));

            }
            if (errorMessage.equals(getString(R.string.msg_len_email_error_message))) {
                emailEdit.requestFocus();
                emailEdit.setError(getString(R.string.msg_len_email_error_message));

            }
            if (errorMessage.equals(getString(R.string.msg_special_characters_email_error_message))) {
                emailEdit.requestFocus();
                emailEdit.setError(getString(R.string.msg_special_characters_email_error_message));

            }
            if (errorMessage.equals(getString(R.string.msg_null_password_error_message))) {
                passwordEdit.requestFocus();
                passwordEdit.setError(getString(R.string.msg_null_password_error_message));
            }
        }
    }

    public void goToMain(String response){
        if (response.contains("\"code\":\"200\"")){
            UserAccountControl userAccountControl = UserAccountControl.getInstance(getApplicationContext());
            userAccountControl.logInUser();
            Intent goToMain = new Intent(this, MainActivity.class);
            Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            startActivity(goToMain);
        } else if (response.contains("\"code\":11000")){
            Toast.makeText(getApplicationContext(), "Email inválido, tente novamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.already_sign_up: {
                Intent returnToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(returnToLogin);
                finish();
                break;

            }

            case R.id.sign_up_button: {
                confirmInformation();

            }
        }
    }
}

