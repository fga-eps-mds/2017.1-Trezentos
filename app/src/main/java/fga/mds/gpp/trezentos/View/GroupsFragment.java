package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import fga.mds.gpp.trezentos.Controller.GroupController;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.Groups;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static android.content.Intent.getIntent;

public class GroupsFragment  extends Fragment implements RecyclerViewOnClickListener{
    private RecyclerView mRecyclerView;
    private GroupController groupController;
    private List<Groups> groupses;
    private Exam exam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_groups
                , container, false);
        Intent intent = getActivity().getIntent();
        exam = (Exam) intent.getSerializableExtra("Exam");
        groupses = new ArrayList<>();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_groups);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager)
                        mRecyclerView.getLayoutManager();
                GroupsAdapter adapter = (GroupsAdapter) mRecyclerView.getAdapter();

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        GroupController groupController = new GroupController();
        new ServerOperation().execute();
        GroupsAdapter adapter = new GroupsAdapter(getActivity(), groupses);
        adapter.setRecyclerViewOnClickListener(this);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    class ServerOperation extends AsyncTask<String, Void, String> {


        public ServerOperation(){
        }

        @Override
        protected String doInBackground(String... params) {
            if(isNetworkAvailable(getContext()) && isInternetAvailable()){ //If internet is ok
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String email = preferences.getString("userEmail", "");


                return "true";

            }else{
                return null;

            }
        }

        @Override
        protected void onPostExecute(String result) {
            if(result == "true"){
                if (getActivity() != null) {

                    RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_groups);
                    recyclerView.setVisibility(View.VISIBLE);

                    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);

                }
            }else{

            }
        }

        @Override
        protected void onPreExecute() {
            SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getActivity());
        }

        @Override
        protected void onProgressUpdate(Void... values) {}

        public boolean isNetworkAvailable(Context context) {
            if (context == null){return false;}

            final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        }

        public boolean isInternetAvailable() {
            try {
                final InetAddress address = InetAddress.getByName("www.google.com");
                if(!address.equals("")){
                    return true;
                }

            } catch (UnknownHostException e) {

            }
            return false;
        }
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_SHORT).show();
    }
}