package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;


public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText emailEdit;
    private Button sendRecovery;
    private Button returnButton;

    private Toolbar toolbar = null;

    UserAccountControl userAccountControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        emailEdit = (EditText) findViewById(R.id.forgot_pass_text_email);
        sendRecovery = (Button) findViewById(R.id.button_forgot_pass_email);
        returnButton = (Button) findViewById(R.id.button_return);

        sendRecovery.setOnClickListener(this);
        returnButton.setOnClickListener(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar_forgot_password);
        setSupportActionBar(toolbar);
        setTitle("Esqueceu a senha?");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.button_forgot_pass_email:

                String recoverEmail = String.valueOf(emailEdit.getText());
                //validate field before send
                //TODO
                //


                userAccountControl = UserAccountControl
                        .getInstance(getApplicationContext());

                String serverResponse = userAccountControl.validateForgotPasswordResponse(recoverEmail);


                JSONObject objectJson = null;
                try {
                    objectJson = new JSONObject(serverResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //if no error in response
                try {
                    if (!objectJson.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), objectJson.getString("message"), Toast.LENGTH_LONG).show();

                        Intent goToLogin = new Intent(this, LoginActivity.class);
                        finish();
                        startActivity(goToLogin);

                    } else {
                        Toast.makeText(getApplicationContext(), objectJson.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;


            case R.id.button_return:
                onBackPressed();
                break;



        }
    }
}
