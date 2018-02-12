package fga.mds.gpp.trezentos.View.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import fga.mds.gpp.trezentos.View.Adapters.ClassFragmentAdapter;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.CreateClassActivity;
import fga.mds.gpp.trezentos.View.ServerOperation.ServerOperationClassFragment;

import static fga.mds.gpp.trezentos.R.id.button_refresh;

public class ClassFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    public ProgressBar progressBar;
    public UserClassControl userClassControl;
    private static ClassFragment fragment;
    private LinearLayout noInternetLayout;
    SwipeRefreshLayout swipeLayout;

    private Button buttonRefresh;

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
        initClasses();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_class, container, false);

        progressBar = view.findViewById(R.id.progressBar);

        swipeLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ServerOperationClassFragment(getActivity().getApplication(),
                        progressBar, noInternetLayout,
                        fragment)
                        .execute();
                swipeLayout.setRefreshing(false);
            }


        });


        noInternetLayout = view.findViewById(R.id.no_internet_layout);
        buttonRefresh = view.findViewById(button_refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noInternetLayout.setVisibility(View.GONE);
                initClasses();

            }
        });

        initFloatingActionButton(view);

        return view;
    }

    public void initClasses(){
        progressBar.setVisibility(View.VISIBLE);
        new ServerOperationClassFragment(getActivity().getApplication(),
                progressBar, noInternetLayout,
                fragment)
                .execute();
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