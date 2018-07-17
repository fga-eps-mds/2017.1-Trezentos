package fga.mds.gpp.trezentos.View.Activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Fragment.ExamsFragment;
import fga.mds.gpp.trezentos.View.Fragment.InfoClassFragment;
import fga.mds.gpp.trezentos.View.Adapters.ViewPagerAdapter;

public class StudentClassActivity extends AppCompatActivity {

    private UserClass userClass;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class);

        initToolbar();
        initViewPager();

        initRecover();


        if (userClass != null) {
            setTitle(userClass.getClassName());
        }
    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);
    }


    public void initRecover() {
        Intent intent = getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ExamsFragment(), "EXAMS");
        adapter.addFragment(new InfoClassFragment(), "STUDENTS");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
