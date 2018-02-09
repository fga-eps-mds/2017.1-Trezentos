package fga.mds.gpp.trezentos.View;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Fragment.AboutFragment;

public class AboutOnLogin extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        int contentView = R.layout.activity_about_on_login;
        setContentView(contentView);

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.about_on_login, new AboutFragment(), "fragment_about");
        fragmentTransaction.commit();
    }
}
