package fga.mds.gpp.trezentos.View.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import java.util.ArrayList;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.ServerOperation.ServerOperationSearchFragment;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<UserClass> userClasses;
    public RecyclerView recyclerView;

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private ProgressBar progressBar;
    private  String userEmail;
    public UserClassControl userClassControl;
    private ServerOperationSearchFragment serverOperationSearchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSearch);

        initToolbar();
        initAppBarLayout();
        //initClasses();

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

    /*private void initClasses() {
        progressBar.setVisibility(View.VISIBLE);
        new ServerOperationSearchFragment(getApplication(), progressBar,
                ExploreFragment.getInstance(), appBarLayout, classAdapter, userClasses).execute();
    }*/

    public void hideViews() {
        appBarLayout.animate().translationY(-appBarLayout.getHeight()).
                setInterpolator(new AccelerateInterpolator(2));
    }

    public void showViews() {
        appBarLayout.animate().translationY(0)
                .setInterpolator(new DecelerateInterpolator(2));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_navigation, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);// Do not iconify the widget; expand it by defaul

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // Adapter that will be filtered
                //classAdapter.getFilter().filter(newText);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {

                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

}