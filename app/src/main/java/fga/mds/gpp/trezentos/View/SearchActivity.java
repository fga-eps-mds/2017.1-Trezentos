package fga.mds.gpp.trezentos.View;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import java.util.ArrayList;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<UserClass> userClasses;
    public RecyclerView recyclerView;
    public ClassAdapter classAdapter;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ProgressBar progressBar;
    private  String userEmail;
    public UserClassControl userClassControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSearch);

        initClasses();
        initToolbar();
        initAppBarLayout();

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

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        JoinClassFragment joinClassFragment = new JoinClassFragment();
        joinClassFragment.setArguments(bundle);
        joinClassFragment.show(fragmentTransaction, "joinClass");
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initAppBarLayout(){
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
    }

    private void initRecyclerView(){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setAdapter(classAdapter);

        userClasses = getFormatedClasses(userClasses);
        classAdapter = new ClassAdapter(userClasses, getApplicationContext(), recyclerView);
        classAdapter.setOnItemClickListener(callJoinClass());
        recyclerView.setAdapter(classAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplication(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }
            @Override
            public void onShow() {
                showViews();
            }
        });
    }


    private void initClasses() {
        progressBar.setVisibility(View.VISIBLE);
        new ServerOperation().execute();
    }

    private void hideViews() {
        appBarLayout.animate().translationY(-appBarLayout.getHeight()).
                setInterpolator(new AccelerateInterpolator(2));
    }

    private void showViews() {
        appBarLayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_navigation, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);// Do not iconify the widget; expand it by defaul

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // Adapter that will be filtered
                classAdapter.getFilter().filter(newText);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {

                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
        return true;
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

    class ServerOperation extends AsyncTask<String, Void, String> {
        public ServerOperation(){}

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
                    getDefaultSharedPreferences(getApplication());
            userEmail = session.getString("userEmail","");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}


