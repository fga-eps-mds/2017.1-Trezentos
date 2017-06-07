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

import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class ExamActivity extends AppCompatActivity {


   // private FloatingActionButton floatingActionButton;
    private UserClass userClass;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private Exam exam;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initToolbar();
        initViewPager();
        initTabLayout();
        initRecover();

        if(exam != null){
            setTitle(exam.getNameExam());
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
        tabLayout.setupWithViewPager(viewPager);
    }


    public void initRecover(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        exam = (Exam) extras.getSerializable("Exam");
        userClass = (UserClass) extras.getSerializable("Class");
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StudentsFragment(), "STUDENTS");
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
        getMenuInflater().inflate(R.menu.menu_exam, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case  R.id.action_update_grades: {
                UserExamControl userExamControl = UserExamControl.getInstance(getApplicationContext());

                // Update exam with grades set in NumberPicker
                Intent intent = getIntent();
                Bundle extras = intent.getExtras();
                exam = (Exam) extras.getSerializable("Exam");

                Log.d("DATAEXAME", userClass.getClassName());
                Log.d("DATAEXAME", exam.getNameExam());
                Log.d("DATAEXAME", exam.getClassOwnerEmail());
                Log.d("DATAEXAME", exam.getFirstGrades());

                try {
                    userExamControl.validateAddsFirstGrade(userClass, exam);
                } catch (UserClassException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            }

            case R.id.action_sort_groups: {
                Bundle bundle = new Bundle();
                bundle.putSerializable("firstGrades", StudentsFragment.getMapEmailAndGrade());
                bundle.putSerializable("userClass", userClass);
                bundle.putSerializable("exam", exam);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                AreYouSureFragment areYouSureFragment = new AreYouSureFragment();
                areYouSureFragment.setArguments(bundle);
                areYouSureFragment.show(fragmentTransaction, "areYouSure");
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
