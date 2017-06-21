package fga.mds.gpp.trezentos.View;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ServerOperationClassFragment extends AsyncTask<String, Void, String> {

    public ArrayList<UserClass> userClasses;
    private  String userEmail;
    private ClassFragmentAdapter classFragmentAdapter;
    public ProgressBar progressBar;
    public UserClassControl userClassControl;
    private LinearLayout noInternetLayout;
    private String email;
    private Application application;
    private ClassFragment classFragment;

    public ServerOperationClassFragment(Application application,
                              ProgressBar progressBar,
                              LinearLayout noInternetLayout,
                              ClassFragmentAdapter classFragmentAdapter,
                              ClassFragment classFragment,
                              ArrayList<UserClass> userClasses){
        this.application = application;
        this.progressBar = progressBar;
        this.noInternetLayout = noInternetLayout;
        this.classFragmentAdapter = classFragmentAdapter;
        this.classFragment = classFragment;
        this.userClasses = userClasses;
    }

    @Override
    protected String doInBackground(String... params) {
            if(isInternetAvailable() ){ //If internet is ok

            userClasses = new ArrayList<>();
            ArrayList<UserClass> allClasses = userClassControl.getClasses();

            for (UserClass userClass : allClasses) {
                if (userClass.getOwnerEmail().equals(email) ||
                userClass.getStudents().contains(email)) {
                    userClasses.add(userClass);
                }
            }
            return "true";

            }else{
                return null;
            }
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(View.GONE);

        if(result.equals("true")){
                if (classFragment.getActivity() != null) {

                    RecyclerView recyclerView =
                            (RecyclerView) classFragment.getActivity()
                                    .findViewById(R.id.recycler);
                    recyclerView.setVisibility(View.VISIBLE);

                    recyclerView.setAdapter(classFragmentAdapter);

                    userClasses = getFormatedClasses(userClasses);

                    classFragment.classFragmentAdapter =
                            new ClassFragmentAdapter(userClasses,
                                    classFragment.getContext(), recyclerView);

                    classFragmentAdapter = classFragment.classFragmentAdapter;

                    recyclerView.setAdapter(classFragmentAdapter);

                    classFragmentAdapter.setOnItemClickListener(callJoinClass());

                    final LinearLayoutManager layoutManager =
                            new LinearLayoutManager(classFragment
                            .getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);

                    RecyclerView.LayoutManager layout = new LinearLayoutManager
                            (application, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);
            }

        }else{

            noInternetLayout.setVisibility(View.VISIBLE);

        }
    }

    private ClassViewHolder.OnItemClickListener callJoinClass() {
        return new ClassViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                UserClass userClass = userClasses.get(position);
                showJoinClassFragment(userClass);
            }
        };
    }

    private void showJoinClassFragment(UserClass userClass){
        if(userClass.getOwnerEmail().equals(email)){
            Log.d("INTENT","ClassOwner");
            Log.d("INTENT",userClass.getOwnerEmail());
            Intent goClass = new  Intent(getApplicationContext(),
                    ClassActivity.class);
            goClass.putExtra("Class", userClass);
            goClass.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(goClass);
        }else{
            Log.d("INTENT","Student");
            Log.d("INTENT",email);
            Log.d("INTENT",userClass.getOwnerEmail());

            Intent goClass = new  Intent(getApplicationContext(),
                    StudentClassActivity.class);
            goClass.putExtra("Class", userClass);
            goClass.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(goClass);
        }

    }

    private ArrayList<UserClass> getFormatedClasses(ArrayList<UserClass> userClasses){
        ArrayList<UserClass> tempList = new ArrayList<UserClass>();
        for (UserClass userClass : userClasses) {
            if (userClass.getOwnerEmail().equals(email) ||
                    userClass.getStudents().contains(email)) {
                tempList.add(userClass);
                Log.d("PUT", userClass.getClassName());
            }
        }
        return tempList;
    }

    @Override
    protected void onPreExecute() {
        userClassControl =
                UserClassControl.getInstance(getApplicationContext());
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        email = session.getString("userEmail","");
    }

    @Override
    protected void onProgressUpdate(Void... values) {}

    public boolean isNetworkAvailable(Context context) {

        final ConnectivityManager connectivityManager =
            ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));

        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private boolean isInternetAvailable() {
            try {
                final InetAddress address = InetAddress.getByName("www.google.com");
            if(!address.equals("")){
                return true;
            }
            //return !address.equals("");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            return false;
    }

}
