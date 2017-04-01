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
import fga.mds.gpp.trezentos.R;

import com.facebook.login.widget.LoginButton;

import fga.mds.gpp.trezentos.R;

public class SignUpActivity extends AppCompatActivity {

    private UserAccountControl userAccountControl = new UserAccountControl(this);
    private static final String TAG = "SignUpActivity";
    private UserDialog dialog = new UserDialog();

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUp = (Button) findViewById(R.id.buttonSignUp);

        final EditText name = (EditText) findViewById(R.id.editTextNameRegister);
        final EditText email = (EditText) findViewById(R.id.editTextEmailRegister);
        final EditText password = (EditText) findViewById(R.id.editTextPasswordRegister);
        final EditText passwordConfirmation = (EditText) findViewById(R.id.editTextPasswordConfirmation);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    try {
            //        userAccountControl.validateInformation(name.getText().toString(), email.getText().toString(),
            //                password.getText().toString(), passwordConfirmation.getText().toString());
            //    } catch (UserException userException) {
            //        e.printStackTrace();
            //    }

                //Confirma dados usuário
                userAccountControl.insertModelUser(0, email.getText().toString(), password.getText().toString());

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                Log.d(TAG, "Button Registrar clicado");
                dialog.alert("Falha na Autenticação", "Dados inválidos");
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
