package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;



import org.json.JSONException;
import org.json.JSONObject;


import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SignUpActivity";
    private UserAccountControl userAccountControl;
    private EditText nameEdit;
    private EditText lastNameEdit;
    private EditText emailEdit;
    private EditText passwordEdit;
    private EditText passwordConfirmationEdit;
    private EditText telephoneDDIEdit;
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

        toolbar = (Toolbar) findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbar);
        setTitle("Cadastrar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        signUp.setOnClickListener(this);
        alreadySignUp.setOnClickListener(this);

//        IntlPhoneInput phoneInputView = (IntlPhoneInput) findViewById(R.id.my_phone_input);
//        String myInternationalNumber = null;
//        if(phoneInputView.isValid()) {
//            myInternationalNumber = phoneInputView.getNumber();
//        }

//        Toast.makeText(getApplicationContext(), myInternationalNumber, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initButtons(){
        signUp = (Button) findViewById(R.id.sign_up_button);
        alreadySignUp = (Button) findViewById(R.id.already_sign_up);
    }

    // Method for confirmation
    public void confirmInformation(){

        String errorMessage = userAccountControl.validateSignUp(
                nameEdit.getText().toString() + " " + lastNameEdit.getText().toString(),
                emailEdit.getText().toString(),
                passwordEdit.getText().toString(),
                passwordConfirmationEdit.getText().toString(),
                "+55",
                telephoneDDDEdit.getText().toString(),
                telephoneNumberEdit.getText().toString());

        verifyMessage(errorMessage, userAccountControl);
    }

    private void verifyMessage(String errorMessage, UserAccountControl userAccountControl){
        if (errorMessage.equals("")){
            String serverResponse = userAccountControl.validateSignUpResponse();
            goToMain(serverResponse);
        }else{
            signUpErrorMessage(nameEdit, emailEdit, passwordEdit,
                    passwordConfirmationEdit, errorMessage);
        }
    }

    private void initFields(){
        nameEdit = (EditText) findViewById(R.id.edit_text_name_register);
        lastNameEdit = (EditText) findViewById(R.id.edit_text_last_name_register);
        emailEdit = (EditText) findViewById(R.id.edit_text_email_register);
        passwordEdit = (EditText) findViewById(R.id.edit_text_password_register);
        passwordConfirmationEdit = (EditText) findViewById(R.id.edit_text_password_confirmation);
        telephoneDDDEdit = (EditText) findViewById(R.id.edit_text_DDD_telephone);
        telephoneNumberEdit = (EditText) findViewById(R.id.edit_text_telephone);
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

        try {
            //converting response to json object
            JSONObject obj = new JSONObject(response);

            //if no error in response
            if (!obj.getBoolean("error")) {
                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                finish();
                startActivity(new Intent(this, LoginActivity.class));
                //getting the user from the response
                //JSONObject userJson = obj.getJSONObject("user");

                //creating a new user object
                /*
                User user = new User(
                        userJson.getInt("id"),
                        userJson.getString("username"),
                        userJson.getString("email"),
                        userJson.getString("gender")
                );
                */


                //storing the user in shared preferences
                //SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                //starting the profile activity
                //finish();
                //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
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
        */

    }

    @Override
    public void onClick(View view){
        int i = view.getId();
        if(i == R.id.already_sign_up){
            Intent returnToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(returnToLogin);
            finish();
        }else if (i == R.id.sign_up_button){
            //executing the async task




            /*JSON Response transform

            try {
                //converting response to json object
                JSONObject obj = new JSONObject(response);

                //if no error in response
                if (!obj.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    //getting the user from the response
                    JSONObject userJson = obj.getJSONObject("user");

                    //creating a new user object

                    User user = new User(
                            userJson.getInt("id"),
                            userJson.getString("username"),
                            userJson.getString("email"),
                            userJson.getString("gender")
                    );


                    //storing the user in shared preferences
                    //SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                    //starting the profile activity
                    finish();
                    //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

            confirmInformation();
        }
    }






}