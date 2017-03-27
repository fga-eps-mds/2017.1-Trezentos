package fga.mds.gpp.trezentos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private UserDialog dialog = new UserDialog();
    private String activityName = this.getClass().getSimpleName();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog.setContext(this);

        Button login = (Button) findViewById(R.id.buttonLogin);
        Button register = (Button) findViewById(R.id.buttonRegister);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Implementar aqui parte da verificação do login
                Log.d(TAG,"Button Login clicado");
                dialog.setProgressMessage("Carregando...");
                dialog.execute();




            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Levar o usuario para a tela de cadastro
//              Intent intent = new Intent(LoginActivityActivity.this, RegisterActivity.class);
//              startActivity(intent);
                Log.d(TAG, "Button Registar clicado");
                dialog.alert("Falha na Autenticação", "Tente novamente...");



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
