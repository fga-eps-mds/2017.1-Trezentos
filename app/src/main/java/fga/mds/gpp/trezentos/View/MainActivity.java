package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import fga.mds.gpp.trezentos.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //startActivity(intent);

//        if (AccessToken.getCurrentAccessToken() == null){
//            goLoginScreen();
//        }



        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.salas_item:
                        Toast.makeText(MainActivity.this,"Button Salas", Toast.LENGTH_SHORT).show();

                        return true;

                    case R.id.usuario_item:
                        Toast.makeText(MainActivity.this,"Button Usuario", Toast.LENGTH_SHORT).show();

                        return true;

                    case R.id.sobre_item:
                        Toast.makeText(MainActivity.this,"Button Sobre", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.avaliacao_item:
                        Toast.makeText(MainActivity.this,"Button Avaliação", Toast.LENGTH_SHORT).show();
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

    public void logout(View view){
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
