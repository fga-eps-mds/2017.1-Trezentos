package fga.mds.gpp.trezentos.View;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fga.mds.gpp.trezentos.R;

public class AboutOnLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_on_login);

        FragmentTransaction fragmentTransaction;
        AboutFragment aboutFragment = new AboutFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.about_on_login, aboutFragment, "fragment_about");
        fragmentTransaction.commit();
    }
}
