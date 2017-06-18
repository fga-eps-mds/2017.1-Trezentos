package fga.mds.gpp.trezentos.View.View.View;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
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
import fga.mds.gpp.trezentos.View.AreYouSureFragment;
import fga.mds.gpp.trezentos.View.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
public class AreYouSureFragmentInstrumentedTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class);

    public AreYouSureFragmentInstrumentedTest() {
        super(MainActivity.class);
    }


    @Before
    public void setUp() {
        UserAccountControl.getInstance(rule.getActivity())
                .authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(rule.getActivity())
                .validateSignInResponse();
    }

    @Test
    public void shouldValidateAreYouSureFragmentInitialization() {
        onView(withId(R.id.recycler)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));
        onView(withText("EXAMS"))
                .perform(click());
        onView(withId(R.id.recyclerExam)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, click()));
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Ordenar Grupos")).perform(click());

        assertNotNull(rule);
    }

//    @Test
//    public void shouldValidateAreYouSureFragmentCancel() {
//        onView(withId(R.id.recycler)).perform(RecyclerViewActions
//                .actionOnItemAtPosition(0, click()));
//        onView(withText("EXAMS"))
//                .perform(click());
//        onView(withId(R.id.recyclerExam)).perform(RecyclerViewActions
//                .actionOnItemAtPosition(0, click()));
//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
//        onView(withText("Ordenar Grupos")).perform(click());
//        onView(withId(R.id.cancel_are_you_sure)).perform(click());
//
//    }

}
