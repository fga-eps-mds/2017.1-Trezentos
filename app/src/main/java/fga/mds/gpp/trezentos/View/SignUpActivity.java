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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SignUpActivity";
    private UserAccountControl userAccountControl;
    private EditText nameEdit;
    private EditText emailEdit;
    private EditText passwordEdit;
    private EditText passwordConfirmationEdit;
    private Button signUp;
    private Button alreadySignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userAccountControl = UserAccountControl
                .getInstance(getApplicationContext());

        initButtons();
        signUp.setOnClickListener(this);
        alreadySignUp.setOnClickListener(this);
    }

    private void initButtons(){
        signUp = (Button) findViewById(R.id.sign_up_button);
        alreadySignUp = (Button) findViewById(R.id.already_sign_up);
    }

    // Method for confirmation
    public void confirmInformation(){
        initFields();
        String errorMessage = userAccountControl.validateSignUp(
                nameEdit.getText().toString(), emailEdit.getText().toString(),
                passwordEdit.getText().toString(),
                passwordConfirmationEdit.getText().toString());

        verifyMessage(errorMessage, userAccountControl);
    }

    private void verifyMessage(String errorMessage, UserAccountControl userAccountControl){
        if (errorMessage.equals("")){
            goToMain(userAccountControl.validateSignUpResponse());
        }else{
            signUpErrorMessage(nameEdit, emailEdit, passwordEdit,
                    passwordConfirmationEdit, errorMessage);
        }
    }

    private void initFields(){
        nameEdit = (EditText) findViewById(edit_text_name_register);
        emailEdit = (EditText) findViewById(edit_text_email_register);
        passwordEdit = (EditText) findViewById(edit_text_password_register);
        passwordConfirmationEdit = (EditText) findViewById(edit_text_password_confirmation);
    }

    private void signUpErrorMessage(EditText nameEdit, EditText emailEdit, EditText passwordEdit,
                                    EditText passwordConfirmationEdit, String errorMessage){
        if (errorMessage.equals(getString(R.string.msg_null_name_error_message))){
            nameEdit.requestFocus();
            nameEdit.setError(getString(R.string.msg_null_name_error_message));
        }else if (errorMessage.equals(getString(R.string.msg_len_name_error_message))){
            nameEdit.requestFocus();
            nameEdit.setError(getString(R.string.msg_len_name_error_message));
        }else if (errorMessage.equals(getString(R.string.msg_len_password_error_message))){
            passwordEdit.requestFocus();
            passwordEdit.setError(getString(R.string.msg_len_password_error_message));
        }else if (errorMessage.equals(getString(R.string.msg_password_conf_error_message))){
            passwordConfirmationEdit.requestFocus();
            passwordConfirmationEdit.setError(getString(R.string.msg_password_conf_error_message));
        }else if (errorMessage.equals(getString(R.string.msg_upper_case_error_message))){
            passwordEdit.requestFocus();
            passwordEdit.setError(getString(R.string.msg_upper_case_error_message));
        }else if (errorMessage.equals(getString(R.string.msg_null_email_error_message))){
            emailEdit.requestFocus();
            emailEdit.setError(getString(R.string.msg_null_email_error_message));
        }else if (errorMessage.equals(getString(R.string.msg_len_email_error_message))){
            emailEdit.requestFocus();
            emailEdit.setError(getString(R.string.msg_len_email_error_message));
        }else if (errorMessage.equals(getString(R.string.msg_special_characters_email_error_message))){
            emailEdit.requestFocus();
            emailEdit.setError(getString(R.string.msg_special_characters_email_error_message));
        }else if (errorMessage.equals(getString(R.string.msg_null_password_error_message))){
            passwordEdit.requestFocus();
            passwordEdit.setError(getString(R.string.msg_null_password_error_message));
        }
    }

    public void goToMain(String response){
        if (response.contains("\"code\":\"200\"")){
            userAccountControl.logInUser();
            Toast.makeText(getApplicationContext(),getString(R.string.msg_signup_success),
                    Toast.LENGTH_SHORT).show();

            Intent goToMain = new Intent(this, MainActivity.class);
            startActivity(goToMain);
        }else if (response.contains("\"code\":11000")){
            Toast.makeText(getApplicationContext(), "Email inválido, tente novamente",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Ocorreu um erro",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view){
        int i = view.getId();
        if(i == R.id.already_sign_up){
            Intent returnToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(returnToLogin);
            finish();
        }else if (i == R.id.sign_up_button){
            confirmInformation();
        }
    }
}