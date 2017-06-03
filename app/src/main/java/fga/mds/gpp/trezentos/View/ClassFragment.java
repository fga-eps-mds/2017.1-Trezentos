package fga.mds.gpp.trezentos.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;
import static fga.mds.gpp.trezentos.R.id.button_refresh;

public class ClassFragment extends Fragment{

    public ArrayList<UserClass> userClasses;
    private FloatingActionButton floatingActionButton;
    private  String userEmail;
    public ProgressBar progressBar;
    public UserClassControl userClassControl;
    private static ClassFragment fragment;
    private LinearLayout noInternetLayout;
    private  Boolean connection;
    private Button buttonRefresh;
    private String email;
    public ClassFragmentAdapter classFragmentAdapter;


    public static ClassFragment getInstance() {
        if(fragment == null){
            fragment = new ClassFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        new ServerOperationClassFragment(getActivity().getApplication(),
                progressBar, noInternetLayout,
                classFragmentAdapter, fragment, userClasses).execute();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_class, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        noInternetLayout = (LinearLayout) view.findViewById(R.id.no_internet_layout);
        buttonRefresh = (Button) view.findViewById(button_refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noInternetLayout.setVisibility(View.GONE);
                initClasses();

            }
        });

            initClasses();
            initFloatingActionButton(view);

        return view;
    }



    public void initClasses(){
        progressBar.setVisibility(View.VISIBLE);
        new ServerOperationClassFragment(getActivity().getApplication(),
                progressBar, noInternetLayout, classFragmentAdapter,
                fragment, userClasses)
                .execute();
    }

    public void initFloatingActionButton(View view){
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.class_image_button);
        floatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateClassActivity.class));
            }
        });
    }
}