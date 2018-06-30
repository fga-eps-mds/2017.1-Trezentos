package fga.mds.gpp.trezentos.View.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.facebook.AccessToken;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.View.Adapters.ClassFragmentAdapter;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.SignInActivity;
import fga.mds.gpp.trezentos.View.ServerOperation.ServerOperationExploreFragment;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ExploreFragment extends Fragment {

    private static ExploreFragment fragment;
    private LinearLayout noInternetLayout;
    private ProgressBar progressBar = null;
    private ArrayList<UserClass> userClasses = null;
    public RecyclerView recyclerView;
    public ClassFragmentAdapter classFragmentAdapter;
    SwipeRefreshLayout swipeLayout;
    private Button buttonRefresh;

    public ArrayList<UserClass> getUserClasses() {
        return userClasses;
    }

    public void setUserClasses(ArrayList<UserClass> userClasses) {
        this.userClasses = userClasses;
    }

    public static ExploreFragment getInstance() {
        if(fragment == null){
            fragment = new ExploreFragment();
        }
        return fragment;
    }

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_explore, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        noInternetLayout = view.findViewById(R.id.no_internet_explore);
        recyclerView = view.findViewById(R.id.recycler_explore);
        swipeLayout = view.findViewById(R.id.swipeRefreshLayout);
        buttonRefresh = view.findViewById(R.id.explore_refresh);

        callServerOperation(true);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        if(AccessToken.getCurrentAccessToken() == null && !session.getBoolean("IsUserLogged", false)){
            goLoginScreen(view);
        }

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callServerOperation(false);
            }
        });

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callServerOperation(true);
            }
        });

        return view;
    }

    private void goLoginScreen(View view) {
        Intent intent = new Intent(view.getContext(), SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void callServerOperation(Boolean isInit){

        UserAccountControl userAccountControl =
                UserAccountControl.getInstance(getApplicationContext());

        if(!userAccountControl.isNetworkAvailable()) {
            recyclerView.setVisibility(View.GONE);
            noInternetLayout.setVisibility(View.VISIBLE);
        } else if(userClasses == null || !isInit){
            noInternetLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            new ServerOperationExploreFragment(
                    isInit,
                    swipeLayout,
                    progressBar,
                    recyclerView,
                    fragment
            ).execute();
        } else {
            noInternetLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            new ServerOperationExploreFragment(
                    isInit,
                    swipeLayout,
                    progressBar,
                    recyclerView,
                    fragment
            ).setLayout();

        }

    }

}
