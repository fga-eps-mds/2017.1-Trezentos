package fga.mds.gpp.trezentos.View;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ServerOperationSearchActivity extends AsyncTask<String, Void, String>{

    private ArrayList<UserClass> userClasses;
    public RecyclerView recyclerView;
    public ClassAdapter classAdapter;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ProgressBar progressBar;
    private  String userEmail;
    public UserClassControl userClassControl;
    public Application application;
    private SearchActivity searchActivity;

    public ServerOperationSearchActivity(Application application, ProgressBar progressBar,
             SearchActivity searchActivity, AppBarLayout appBarLayout,
             ClassAdapter classAdapter, ArrayList<UserClass> userClasses){
        this.application = application;
        this.progressBar = progressBar;
        this.searchActivity = searchActivity;
        this.appBarLayout = appBarLayout;
        this.classAdapter = classAdapter;
        this.userClasses = userClasses;
    }

    private void initRecyclerView(){

        RecyclerView recyclerView = (RecyclerView) searchActivity.findViewById(R.id.recycler);
        recyclerView.setAdapter(classAdapter);

        userClasses = getFormatedClasses(userClasses);
        searchActivity.classAdapter = new ClassAdapter(userClasses, getApplicationContext(), recyclerView);
        classAdapter = searchActivity.classAdapter;
        classAdapter.setOnItemClickListener(callJoinClass());
        recyclerView.setAdapter(classAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(application,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                searchActivity.hideViews();
            }
            @Override
            public void onShow() {
                searchActivity.showViews();
            }
        });
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

    private void showJoinClassFragment(UserClass userClass) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("userClass", userClass);

        FragmentTransaction fragmentTransaction = searchActivity
                .getSupportFragmentManager().beginTransaction();

        JoinClassFragment joinClassFragment = new JoinClassFragment();
        joinClassFragment.setArguments(bundle);
        joinClassFragment.show(fragmentTransaction, "joinClass");
    }

    public ArrayList<UserClass> getFormatedClasses(ArrayList<UserClass> userClasses){
        ArrayList<UserClass> tempList = new ArrayList<UserClass>();
        for (UserClass userClass : userClasses) {
            if (userClass.getOwnerEmail().equals(userEmail) ||
                    userClass.getStudents().contains(userEmail)) {
            }else{
                tempList.add(userClass);
                Log.d("PUT", userClass.getClassName());
            }
        }
        return tempList;
    }

    @Override
    protected String doInBackground(String... params) {

        userClasses = userClassControl.getClasses();
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(View.GONE);
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
