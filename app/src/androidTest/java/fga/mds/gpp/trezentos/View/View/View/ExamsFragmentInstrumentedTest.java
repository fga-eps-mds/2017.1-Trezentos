package fga.mds.gpp.trezentos.View.View.View;

import android.support.test.espresso.matcher.ViewMatchers;
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

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.anything;

@RunWith(JUnit4.class)
public class ExamsFragmentInstrumentedTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    @Rule
    public ActivityTestRule<MainActivity> examRule =
            new ActivityTestRule<>(MainActivity.class);

    public ExamsFragmentInstrumentedTest() {
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
    public void shouldValidateExamsFragmentInitialization(){
        onView(withId(R.id.salas_item))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.recycler))
                .atPosition(0).perform(click());
        onView(ViewMatchers.withText("EXAMS"))
                .perform(click());

        assertNotNull(examRule);
    }

    @Test
    public void shouldClickOnExamButton(){
        onView(withId(R.id.salas_item))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.recycler))
                .atPosition(0).perform(click());
        onView(ViewMatchers.withText("EXAMS"))
                .perform(click());

        onView(withId(R.id.floating_btn)).perform(click());

        assertNotNull(examRule);
    }

}
