package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private  ClassFragment fragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (AccessToken.getCurrentAccessToken() == null && !session.getBoolean("IsUserLogged", false)){
            goLoginScreen();
        }

        ClassFragment classFragment = new ClassFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,classFragment, "fragmentclass" );
        fragmentTransaction.commit();



        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction fragmentTransaction;

                switch (item.getItemId()) {
                    case R.id.salas_item:
                        Toast.makeText(MainActivity.this,"Button Salas", Toast.LENGTH_SHORT).show();

                        ClassFragment classFragment = new ClassFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,classFragment, "fragment_class" );
                        fragmentTransaction.commit();

                        return true;

                    case R.id.usuario_item:
                        Toast.makeText(MainActivity.this,"Button Usuario", Toast.LENGTH_SHORT).show();

                        UserFragment userFragment = new UserFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,userFragment, "fragment_user" );
                        fragmentTransaction.commit();

                        return true;

                    case R.id.sobre_item:

                        AboutFragment aboutFragment = new AboutFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,aboutFragment, "fragment_about" );
                        fragmentTransaction.commit();

                        return true;

                    case R.id.avaliacao_item:
                        Toast.makeText(MainActivity.this,"Button Avaliação", Toast.LENGTH_SHORT).show();

                        EvaluationFragment evaluationFragment = new EvaluationFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,evaluationFragment, "fragment_evaluation" );
                        fragmentTransaction.commit();
                        return true;
                }
                return true;
            }

        });
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
