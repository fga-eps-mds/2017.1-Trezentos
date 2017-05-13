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

public class ClassActivity extends AppCompatActivity{

    private FloatingActionButton floatingActionButton;
    private UserClass userClass;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        userClass = (UserClass) intent.getSerializableExtra("Class");

        if(userClass != null){
//            TextView textView = (TextView) findViewById(R.id.testClass);
//            textView.setText(userClass.getClassName());
            setTitle(userClass.getClassName());
        }

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_btn);

        floatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCreateExam = new  Intent(getApplicationContext(), CreateExamActivity.class);
                UserClass userClassCalled = (UserClass) userClass;
                goCreateExam.putExtra("Class", userClassCalled);

                startActivity(goCreateExam);
                //Toast.makeText(ClassActivity.this,"Criar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ExamsFragment(), "EXAMS");
        adapter.addFragment(new StudensFragment(), "STUDENTS");
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

            Intent intentEditClass = new  Intent(getApplicationContext(), EditClassActivity.class);
            UserClass userClassCalled = (UserClass) userClass;
            intentEditClass.putExtra("Class", userClassCalled);

            startActivity(intentEditClass);
        }

        return super.onOptionsItemSelected(item);
    }

}

