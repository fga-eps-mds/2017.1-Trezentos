package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import fga.mds.gpp.trezentos.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private  ClassFragment fragment;
    private FragmentTransaction fragmentTransaction;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        //startActivity(intent);

//        if (AccessToken.getCurrentAccessToken() == null){
//            goLoginScreen();
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();


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
                        Toast.makeText(MainActivity.this,"Button Sobre", Toast.LENGTH_SHORT).show();

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

    public void logout(View view){
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
