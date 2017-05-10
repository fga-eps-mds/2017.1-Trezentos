package fga.mds.gpp.trezentos.View;


import android.support.design.widget.FloatingActionButton;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
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

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static fga.mds.gpp.trezentos.R.id.frame;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class ClassFragmentInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainRule =
            new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() {
        UserAccountControl.getInstance(mainRule.getActivity()).authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(mainRule.getActivity()).validateSignInResponse();
    }

    @Test
    public void shouldValidateClassFragmentInitialization() {
        onView(ViewMatchers.withId(R.id.salas_item))
                .perform(click());
        assertNotNull(mainRule);
        }

    @Test
    public void shouldClickOnClassCreated(){

        onView(ViewMatchers.withId(R.id.salas_item))
                .perform(click());

        ClassFragment classFragment;
        classFragment = (ClassFragment) mainRule.getActivity()
                .getSupportFragmentManager()
                .findFragmentById(frame);

        ListView listView =
                (ListView) classFragment.getActivity()
                        .findViewById(R.id.class_list_view);

        if(listView.getAdapter().getCount() > 0) {

            onView(ViewMatchers.withId(R.id.class_list_view))
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
