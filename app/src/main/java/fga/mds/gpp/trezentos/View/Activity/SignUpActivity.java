package fga.mds.gpp.trezentos.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import org.json.JSONException;
import org.json.JSONObject;


import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SignUpActivity";
    private UserAccountControl userAccountControl;
    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText emailEdit;
    private EditText passwordEdit;
    private EditText passwordConfirmationEdit;
    private EditText telephoneDDDEdit;
    private EditText telephoneNumberEdit;
    private Button signUp;
    private Button alreadySignUp;

    private Toolbar toolbar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userAccountControl = UserAccountControl
                .getInstance(getApplicationContext());

        initButtons();
        initFields();
        initToolbar();

        signUp.setOnClickListener(this);
        alreadySignUp.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initButtons(){
        signUp = findViewById(R.id.sign_up_button);
        alreadySignUp = findViewById(R.id.already_sign_up);
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbar);
        setTitle("Cadastrar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initFields(){
        firstNameEdit= findViewById(R.id.edit_text_name_register);
        lastNameEdit = findViewById(R.id.edit_text_last_name_register);
        emailEdit = findViewById(R.id.edit_text_email_register);
        passwordEdit = findViewById(R.id.edit_text_password_register);
        passwordConfirmationEdit = findViewById(R.id.edit_text_password_confirmation);
        telephoneDDDEdit = findViewById(R.id.edit_text_DDD_telephone);
        telephoneNumberEdit = findViewById(R.id.edit_text_telephone);
    }


    // Method for confirmation
    public void confirmInformation(){

        String errorMessage = userAccountControl.validateSignUp(
                                            firstNameEdit.getText().toString(),
                                            lastNameEdit.getText().toString(),
                                            emailEdit.getText().toString(),
                                            "+55",
                                            telephoneDDDEdit.getText().toString(),
                                            telephoneNumberEdit.getText().toString(),
                                            passwordEdit.getText().toString(),
                                            passwordConfirmationEdit.getText().toString());
        verifyMessage(errorMessage);
    }

    private void verifyMessage(String errorMessage){
        if (errorMessage.equals("")){
            String serverResponse = userAccountControl.validateSignUpResponse();
            validateServerResponse(serverResponse);
        }else{
            Log.d("Erro Message", errorMessage);
            showErrorMessage(errorMessage);
        }
    }

    private void showErrorMessage( String errorMessage){
        if (errorMessage.equals(getString(R.string.msg_null_name_error_message))){
            firstNameEdit.requestFocus();
            firstNameEdit.setError(getString(R.string.msg_null_name_error_message));
        }else if (errorMessage.equals(getString(R.string.msg_len_name_error_message))){
            firstNameEdit.requestFocus();
            firstNameEdit.setError(getString(R.string.msg_len_name_error_message));
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

    private void validateServerResponse(String response){
        try {
            //converting response to json object
            JSONObject obj = new JSONObject(response);

            //if no error in response
            if (!obj.getBoolean("error")) {
                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                goToLogin();

            } else {
                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void goToLogin(){
        finish();
        startActivity(new Intent(this, SignInActivity.class));
    }

    @Override
    public void onClick(View view){
        int i = view.getId();
        if(i == R.id.already_sign_up){
            Intent returnToLogin = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(returnToLogin);
            finish();
        }else if (i == R.id.sign_up_button){
            confirmInformation();
        }
    }

}