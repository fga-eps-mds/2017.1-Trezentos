package fga.mds.gpp.trezentos.View.View.View;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.Activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
public class ExamActivityInstrumentedTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    @Rule
    public ActivityTestRule<MainActivity> examRule =
            new ActivityTestRule<>(MainActivity.class);

    public ExamActivityInstrumentedTest() {
        super(MainActivity.class);
    }


    @Before
    public void setUp() {
        UserAccountControl.getInstance(examRule.getActivity())
                .authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(examRule.getActivity())
                .validateSignInResponse();
    }

    @Test
    public void shouldValidateExamFragmentInitialization() {
        onView(withId(R.id.recycler)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));
        onView(withText("EXAMS"))
                .perform(click());
        onView(withId(R.id.recyclerExam)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));

        assertNotNull(examRule);
    }

    @Test
    public void shouldValidateStudentsFragmentInitialization() {
        onView(withId(R.id.recycler)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));
        onView(withText("EXAMS"))
                .perform(click());
        onView(withId(R.id.recyclerExam)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));
        onView(withText("STUDENTS"))
                .perform(click());

        assertNotNull(examRule);
    }

    @Test
    public void shouldValidateGroupsFragmentInitialization() {
        onView(withId(R.id.recycler)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));
        onView(withText("EXAMS"))
                .perform(click());
        onView(withId(R.id.recyclerExam)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));
        onView(withText("GROUPS"))
                .perform(click());

        assertNotNull(examRule);
    }

}