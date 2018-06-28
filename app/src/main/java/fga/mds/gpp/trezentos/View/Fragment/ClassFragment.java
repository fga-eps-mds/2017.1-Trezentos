package fga.mds.gpp.trezentos.View.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.View.Adapters.ClassFragmentAdapter;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.CreateClassActivity;
import fga.mds.gpp.trezentos.View.ServerOperation.ServerOperationClassFragment;
import fga.mds.gpp.trezentos.View.ServerOperation.ServerOperationExploreFragment;

import static fga.mds.gpp.trezentos.R.id.button_refresh;

public class ClassFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    public ProgressBar progressBar;
    public UserClassControl userClassControl;
    private static ClassFragment fragment;
    private LinearLayout noInternetLayout;
    SwipeRefreshLayout swipeLayout;
    private ArrayList<UserClass> userClasses = null;
    private RecyclerView recyclerView;

    private Button buttonRefresh;

    public ClassFragmentAdapter classFragmentAdapter;

    public static ClassFragment getInstance() {
        if(fragment == null){
            fragment = new ClassFragment();
        }
        return fragment;
    }

    public ArrayList<UserClass> getUserClasses() {
        return userClasses;
    }

    public void setUserClasses(ArrayList<UserClass> userClasses) {
        this.userClasses = userClasses;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_class, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        swipeLayout = view.findViewById(R.id.swipeRefreshLayout);
        noInternetLayout = view.findViewById(R.id.no_internet_layout);
        recyclerView = view.findViewById(R.id.recycler_class);

        buttonRefresh = view.findViewById(button_refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noInternetLayout.setVisibility(View.GONE);

            }
        });

        if(userClasses != null){
            new ServerOperationClassFragment(true, swipeLayout, progressBar, recyclerView, fragment, noInternetLayout).setLayout();

        }else {
            new ServerOperationClassFragment(true, swipeLayout, progressBar, recyclerView, fragment, noInternetLayout).execute();
        }

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ServerOperationClassFragment(false, swipeLayout, progressBar, recyclerView, fragment, noInternetLayout).execute();
            }
        });

        initFloatingActionButton(view);

        return view;
    }


    public void initFloatingActionButton(View view){
        floatingActionButton = view.findViewById(R.id.class_image_button);
        floatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateClassActivity.class));

            }
        });
    }

}