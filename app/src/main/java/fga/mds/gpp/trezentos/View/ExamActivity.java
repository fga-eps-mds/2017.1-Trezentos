package fga.mds.gpp.trezentos.View;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class ExamActivity extends AppCompatActivity {


    private FloatingActionButton floatingActionButton;
    private UserClass userClass;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        initToolbar();
        initViewPager();
        initTabLayout();
        initFloatingButton();
        initRecover();


        if(userClass != null){setTitle(userClass.getClassName());}

    }

    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);
    }

    public void initTabLayout(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void initFloatingButton(){
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_btn);

        floatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCreateExam = new  Intent(getApplicationContext(), CreateExamActivity.class);
                UserClass userClassCalled = (UserClass) userClass;
                goCreateExam.putExtra("Class", userClassCalled);
                startActivity(goCreateExam);
            }
        });

    }

    public void initRecover(){
        Intent intent = getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new StudensFragment(), "STUDENTS");
        adapter.addFragment(new GroupsFragment(), "GROUPS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_class, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }
        else if(id == R.id.action_edit_class){

//            Intent intentEditClass = new  Intent(getApplicationContext(), EditClassActivity.class);
//            UserClass userClassCalled = (UserClass) userClass;
//            intentEditClass.putExtra("Class", userClassCalled);
//
//            startActivity(intentEditClass);
        }

        return super.onOptionsItemSelected(item);
    }

}
