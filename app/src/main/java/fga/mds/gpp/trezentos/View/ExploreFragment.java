package fga.mds.gpp.trezentos.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;


public class ExploreFragment extends Fragment {

    private static ExploreFragment fragment;
    private LinearLayout noInternetLayout;
    private ProgressBar progressBar = null;

    private ArrayList<UserClass> userClasses;
    public RecyclerView recyclerView;
    public ClassFragmentAdapter classFragmentAdapter;


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
        super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_explore, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        noInternetLayout = (LinearLayout) view.findViewById(R.id.no_internet_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_explore);
        initClasses();

        return view;
    }

    public void initClasses() {
        progressBar.setVisibility(View.VISIBLE);
        new ServerOperationExploreFragment(getActivity().getApplication(),
                progressBar, noInternetLayout,
                fragment)
                .execute();
    }



}
