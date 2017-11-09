package fga.mds.gpp.trezentos.View;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ServerOperationExploreFragment extends AsyncTask<String, Void, String> {

    public ArrayList<UserClass> userClasses = null;
    private  String userEmail;
    private ClassFragmentAdapter classFragmentAdapter;
    public ProgressBar progressBar;
    public UserClassControl userClassControl;
    private LinearLayout noInternetLayout;
    private String email;
    private String userId;
    private Application application;
    private ExploreFragment exploreFragment;

    private RecyclerView recyclerView;

    public ServerOperationExploreFragment(Application application,
                                          ProgressBar progressBar,
                                          LinearLayout noInternetLayout,
                                          ExploreFragment exploreFragment){
        this.application = application;
        this.progressBar = progressBar;
        this.noInternetLayout = noInternetLayout;

        this.exploreFragment = exploreFragment;
    }

    @Override
    protected void onPreExecute() {

        userClassControl =
                UserClassControl.getInstance(getApplicationContext());
        SharedPreferences session = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        email = session.getString("userEmail","");
        userId = session.getString("userId","");
    }

    @Override
    protected void onProgressUpdate(Void... values) {}

    @Override
    protected String doInBackground(String... params) {

        if(isInternetAvailable() ) { //If internet is ok

            try {
                userClasses = userClassControl.getExploreClasses();
            } catch (JSONException e) {
                e.printStackTrace();
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
                if (exploreFragment.getActivity() != null) {

                    recyclerView = (RecyclerView) exploreFragment.getActivity().findViewById(R.id.recycler_explore);
                    recyclerView.setVisibility(View.VISIBLE);


                    classFragmentAdapter = new ClassFragmentAdapter(userClasses,
                            exploreFragment.getContext());


                    classFragmentAdapter.setOnItemClickListener(callJoinClass());

                    RecyclerView.LayoutManager layout = new LinearLayoutManager
                            (application, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);

                    recyclerView.setAdapter(classFragmentAdapter);
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
//        if(userClass.getOwnerEmail().equals(email)){
//            Log.d("INTENT","ClassOwner");
//            Log.d("INTENT",userClass.getOwnerEmail());
//            Intent goClass = new  Intent(getApplicationContext(),
//                    ClassActivity.class);
//            goClass.putExtra("Class", userClass);
//            goClass.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            getApplicationContext().startActivity(goClass);
//        }else{
//            Log.d("INTENT","Student");
//            Log.d("INTENT",email);
//            Log.d("INTENT",userClass.getOwnerEmail());
//
//            Intent goClass = new  Intent(getApplicationContext(),
//                    StudentClassActivity.class);
//            goClass.putExtra("Class", userClass);
//            goClass.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            getApplicationContext().startActivity(goClass);
//        }

    }


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
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return false;
    }

}
