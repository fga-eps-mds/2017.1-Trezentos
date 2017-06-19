package fga.mds.gpp.trezentos.View;

import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.Controller.EvaluationControl;
import fga.mds.gpp.trezentos.Controller.GroupController;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

public class ExamActivity extends AppCompatActivity {

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

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_exam, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update_grades: {
                UserExamControl userExamControl = UserExamControl.getInstance(getApplicationContext());

                recoverLastIntent();
                try {
                    userExamControl.validateAddsGrades(userClass, exam, "first_grades");
                }catch(UserClassException | ExecutionException | InterruptedException e){
                    e.printStackTrace();
                }

                break;
            }

            case R.id.action_update_trezentos_grades: {
                UserExamControl userExamControl = UserExamControl.getInstance(getApplicationContext());

                recoverLastIntent();
                try {
                    userExamControl.validateAddsGrades(userClass, exam, "second_grades");
                }catch(InterruptedException | ExecutionException | UserClassException e){
                    e.printStackTrace();
                }

                break;
            }

            case R.id.action_sort_groups: {
                Bundle bundle = new Bundle();
                bundle = buildBundleToSortGroups(bundle);
                initFragmentTransation(bundle);
                break;
            }

            case R.id.action_send_evaluation: {
                EvaluationControl evaluationControl =
                        EvaluationControl.getInstance(getApplication());

                HashMap<String, Integer> groups;
                groups = GroupController.getGroups(exam.getNameExam(),
                        userClass.getClassName(), userClass.getOwnerEmail());

                HashMap<String, Double> grades;
                grades = GroupController.getFirstGrades(exam.getNameExam(),
                        userClass.getClassName(), userClass.getOwnerEmail());

                addEvaluationToUser(grades, groups, evaluationControl);
                sendEvaluationNotification();

                break;
            }

        }
        return super.onOptionsItemSelected(item);

    }

    private void sendEvaluationNotification() {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.trezentos_icon)
                        .setContentTitle("Avaliação")
                        .setContentText("Você tem avaliações à serem feitas!")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources()
                                , R.drawable.trezentos_icon));

        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }

    // Update exam with grades set in NumberPicker
    private void recoverLastIntent(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        exam = (Exam) extras.getSerializable("Exam");
    }

    private void initFragmentTransation(Bundle bundle){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        AreYouSureFragment areYouSureFragment = new AreYouSureFragment();
        areYouSureFragment.setArguments(bundle);
        areYouSureFragment.show(fragmentTransaction, "areYouSure");
    }

    private Bundle buildBundleToSortGroups(Bundle bundle){
        bundle.putSerializable("firstGrades", StudentsFragment.getHashEmailAndGrade());
        bundle.putSerializable("userClass", userClass);
        bundle.putSerializable("exam", exam);

        return bundle;
    }

    private void addEvaluationToUser(HashMap<String, Double> grades,
                                     HashMap<String, Integer> groups,
                                     EvaluationControl evaluationControl){
        for(Map.Entry <String, Integer> entry : groups.entrySet()) {
            evaluationControl.sendEvaluation(exam.getNameExam(),
                    entry.getKey(), userClass.getClassName(),
                    groups, grades, (double) userClass.getCutOff());
        }
    }

}
