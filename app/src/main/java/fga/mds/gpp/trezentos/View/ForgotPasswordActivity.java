package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import fga.mds.gpp.trezentos.R;

import static fga.mds.gpp.trezentos.R.id.button_forgot_pass_email;

public class ForgotPasswordActivity extends AppCompatActivity {

    private UserDialog dialog = new UserDialog();
    private String activityName = this.getClass().getSimpleName();
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        dialog.setContext(this);

        EditText forgotPassText = (EditText) findViewById(R.id.forgot_pass_text_email);
        Button buttonForgotPass = (Button) findViewById(R.id.button_forgot_pass_email);
        Button buttonReturn = (Button) findViewById(R.id.button_return_login);

        buttonForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  //              Intent forgotIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
//                startActivity(forgotIntent);

                //Log.d(TAG, "Button Forgot Password clicado");
                //dialog.alert("Email enviado!", "Caso demore mais de 10 minutos, tente novamente");


            }
        });

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(forgotIntent);

                //Log.d(TAG, "Button Return Login clicado");
                //dialog.alert("Falha na Autenticação", "Tente novamente...");


            }
        });

    }
}
