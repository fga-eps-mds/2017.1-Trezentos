package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.facebook.AccessToken;
import fga.mds.gpp.trezentos.R;


public class MainActivity extends AppCompatActivity{

    private BottomNavigationView bottomNavigationView;
    private Fragment selectedFragment;
    private Toolbar toolbar = null;
    MenuItem itemSeach = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.

        itemSeach = (MenuItem) findViewById(R.id.search);


        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        // Noinspection SimplifiableIfStatement
        if(id == R.id.search_classes){
            goClassScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedFragment = null;


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Salas");
        setSupportActionBar(toolbar);



        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                        .OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.salas_item:
                        toolbar.setTitle("Salas");
                        selectedFragment = ClassFragment.getInstance();
                        break;

                    case R.id.usuario_item:
                        toolbar.setTitle("Explorar");
                        selectedFragment = ExploreFragment.getInstance();
                        break;

                    case R.id.about_item:
                        toolbar.setTitle("Perfil");
                        selectedFragment = UserFragment.getInstance();
                        break;

                    case R.id.avaliacao_item:
                        toolbar.setTitle("Sobre");
                        selectedFragment = AboutFragment.getInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, selectedFragment);
                transaction.commit();
                return true;
            }

        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, ClassFragment.getInstance());
        transaction.commit();
    }


    private void goClassScreen() {
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
    }

}
