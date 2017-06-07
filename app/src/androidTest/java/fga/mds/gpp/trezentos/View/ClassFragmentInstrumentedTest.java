package fga.mds.gpp.trezentos.View;


import android.support.design.widget.FloatingActionButton;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static fga.mds.gpp.trezentos.R.id.frame;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class ClassFragmentInstrumentedTest extends
        ActivityInstrumentationTestCase2<MainActivity> {

    @Rule
    public ActivityTestRule<MainActivity> mainRule =
            new ActivityTestRule<>(MainActivity.class);

    public ClassFragmentInstrumentedTest(){
        super(MainActivity.class);
    }

    @Before
    public void setUp() {
        UserAccountControl.getInstance(mainRule.getActivity())
                .authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(mainRule.getActivity())
                .validateSignInResponse();

    }

    @Test
    public void shouldValidateClassFragmentInitialization() {
        onView(ViewMatchers.withId(R.id.salas_item))
                .perform(click());
        assertNotNull(mainRule);
        }

    @Test
    public void shouldClickOnClassCreated(){

        ClassFragment classFragment;
        classFragment = (ClassFragment) mainRule.getActivity()
                .getSupportFragmentManager()
                .findFragmentById(frame);

        RecyclerView recyclerView =
                (RecyclerView) classFragment.getActivity()
                        .findViewById(R.id.recycler);

        if(recyclerView.getAdapter().getItemCount() > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            onView(ViewMatchers.withId(R.id.recycler))
                    .perform(click());

        }

        assertNotNull(classFragment);

    }

    @Test
    public void shouldClickOnButtonClass(){

        onView(ViewMatchers.withId(R.id.salas_item))
                .perform(click());

        onView(withId(R.id.class_image_button))
                .perform(click());

        assertNotNull(mainRule);

    }

}
