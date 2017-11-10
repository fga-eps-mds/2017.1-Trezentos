package fga.mds.gpp.trezentos.View;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ServerOperationSearchFragment extends AsyncTask<String, Void, String>{

    private ArrayList<UserClass> userClasses;

    public ClassFragmentAdapter classFragmentAdapter;

    LinearLayout noInternetLayout;
    private ProgressBar progressBar;
    private  String userEmail;
    public UserClassControl userClassControl;
    public Application application;
    private ExploreFragment exploreFragment;
    private RecyclerView recyclerView;

    public ServerOperationSearchFragment(Application application,
                                         ProgressBar progressBar,
                                         LinearLayout noInternetLayout,
                                         ExploreFragment exploreFragment,
                                         ClassFragmentAdapter classFragmentAdapter,
                                         ArrayList<UserClass> userClasses,
                                         RecyclerView recyclerView){


        this.application = application;
        this.progressBar = progressBar;
        this.noInternetLayout = noInternetLayout;
        this.exploreFragment = exploreFragment;
        this.classFragmentAdapter = classFragmentAdapter;
        this.userClasses = userClasses;
        this.recyclerView = recyclerView;
    }

    private void initRecyclerView(){

        //RecyclerView recyclerView = (RecyclerView) .findViewById(R.id.recycler_explore);
        recyclerView.setAdapter(classFragmentAdapter);

        //userClasses = getFormatedClasses(userClasses);

        exploreFragment.classFragmentAdapter = new ClassFragmentAdapter(userClasses, getApplicationContext());
        classFragmentAdapter = exploreFragment.classFragmentAdapter;
        classFragmentAdapter.setOnItemClickListener(callJoinClass());

        RecyclerView.LayoutManager layout = new LinearLayoutManager(application,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        recyclerView.setAdapter(classFragmentAdapter);
        recyclerView.setVisibility(View.VISIBLE);


        /*
        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                exploreFragment.hideViews();
            }
            @Override
            public void onShow() {
                exploreFragment.showViews();
            }
        });
        */
    }

    private ClassViewHolder.OnItemClickListener callJoinClass() {
        return new ClassViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                UserClass userClass = userClasses.get(position);
                //showJoinClassFragment(userClass);
            }
        };
    }
/*
    private void showJoinClassFragment(UserClass userClass) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("userClass", userClass);

        FragmentTransaction fragmentTransaction = exploreFragment
                .getSupportFragmentManager().beginTransaction();

        JoinClassFragment joinClassFragment = new JoinClassFragment();
        joinClassFragment.setArguments(bundle);
        joinClassFragment.show(fragmentTransaction, "joinClass");
    }
    */

    public ArrayList<UserClass> getFormatedClasses(ArrayList<UserClass> userClasses){
        ArrayList<UserClass> tempList = new ArrayList<UserClass>();
        for (UserClass userClass : userClasses) {
//            if (userClass.getOwnerEmail().equals(userEmail) ||
//                    userClass.getStudents().contains(userEmail)) {
//            }else{
//                tempList.add(userClass);
//                Log.d("PUT", userClass.getClassName());
//            }
        }
        return tempList;
    }

    @Override
    protected String doInBackground(String... params) {

        //userClasses = userClassControl.getClasses("11");

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(View.GONE);
        Log.d("TEST", userClasses.toString());
        initRecyclerView();
    }

    @Override
    protected void onPreExecute() {
        userClassControl = UserClassControl.getInstance(getApplicationContext());
        SharedPreferences session = PreferenceManager.
                getDefaultSharedPreferences(application);
        userEmail = session.getString("userEmail","");
    }

    @Override
    protected void onProgressUpdate(Void... values) {}

}
