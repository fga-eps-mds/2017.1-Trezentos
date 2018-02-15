package fga.mds.gpp.trezentos.View.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import fga.mds.gpp.trezentos.Controller.GroupController;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Fragment.ShowStudentGroupFragment;
import fga.mds.gpp.trezentos.View.Adapters.ViewPagerAdapter;

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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void initViewPager(){
        viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);
    }

    public void initTabLayout(){
        TabLayout tabLayout = findViewById(R.id.tab_layout);
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
