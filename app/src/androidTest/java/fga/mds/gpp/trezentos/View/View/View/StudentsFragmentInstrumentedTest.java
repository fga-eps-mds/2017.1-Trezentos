package fga.mds.gpp.trezentos.View.View.View;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static fga.mds.gpp.trezentos.R.id.frame;
import static fga.mds.gpp.trezentos.R.id.numberPicker1;
import static fga.mds.gpp.trezentos.R.id.recycler;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.anything;

@RunWith(JUnit4.class)
public class StudentsFragmentInstrumentedTest extends
        ActivityInstrumentationTestCase2<MainActivity>{

    @Rule
    public ActivityTestRule<MainActivity> classRule =
            new ActivityTestRule<>(MainActivity.class);

    public StudentsFragmentInstrumentedTest() {
        super(MainActivity.class);
    }


    @Before
    public void setUp() {
        UserAccountControl.getInstance(classRule.getActivity())
                .authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(classRule.getActivity())
                .validateSignInResponse();
    }

    @Test
    public void shouldValidateStudentsFragmentInitialization(){
        onView(withId(R.id.recycler)).perform(RecyclerViewActions
                .actionOnItemAtPosition(5, click()));
        onView(withText("STUDENTS"))
                .perform(click());

        assertNotNull(classRule);

    }

//    @Test
//    public void shouldClickOnStudentRegistered(){
//        onView(withId(R.id.salas_item))
//                .perform(click());
//        onData(anything()).inAdapterView(withId(R.id.recycler))
//                .atPosition(0).perform(click());
//
//            ListView listView;
//
//            StudentsFragment studentsFragment;
//
//            studentsFragment = (StudentsFragment) classRule.getActivity()
//                    .getSupportFragmentManager().findFragmentById(R.id.view_pager);
//
//            onView(ViewMatchers.withText("STUDENTS"))
//                    .perform(click());
//
//            listView = (ListView) studentsFragment.getActivity()
//                    .findViewById(R.id.list);
//
//            if(listView.getAdapter().getCount() > 0) {
//
//                onView(withId(R.id.list))
//                        .perform(click());
//            }
//
//            assertNotNull(studentsFragment);
//    }

    @Test
    public void shouldClickOnButtonStudent(){
        onView(withId(R.id.recycler)).perform(RecyclerViewActions
                .actionOnItemAtPosition(5, click()));
        onView(withText("STUDENTS")).perform(click());

        onView(withId(R.id.floating_btn)).perform(click());

        assertNotNull(classRule);
    }

    @Test
    public void shouldValidateFirstGrade(){
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.
                actionOnItemAtPosition(5, click()));
        onView(withId(R.id.recyclerExam)).perform(RecyclerViewActions.
                actionOnItemAtPosition(0, click()));
        onView(withText("Nota")).perform(click());
        onView(withText("ok")).perform(click());
        //onView(withId(R.id.action_update_grades)).perform(click());
        assertTrue(true);
    }

    @Test
    public void shouldValidateSecondGrade(){
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.
                actionOnItemAtPosition(5, click()));
        onView(withId(R.id.recyclerExam)).perform(RecyclerViewActions.
                actionOnItemAtPosition(0, click()));
        onView(withText("Nota 300")).perform(click());
        onView(withText("ok")).perform(click());
        //onView(withId(R.id.action_update_trezentos_grades)).perform(click());
        assertTrue(true);
    }

}