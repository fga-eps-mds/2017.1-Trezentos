package fga.mds.gpp.trezentos.View;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.Controller.GroupController;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class StudentExamActivity extends AppCompatActivity {

    private UserClass userClass;
    private GroupController groups;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private Exam exam;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exam);

        initToolbar();
        initViewPager();
        initTabLayout();
        initRecover();

        if(exam != null){
            setTitle(exam.getNameExam() );
        }
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
    }


    public void initRecover(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        exam = (Exam) extras.getSerializable("Exam");
        userClass = (UserClass) extras.getSerializable("Class");
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ShowStudentGroupFragment(), "GROUPS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

}
