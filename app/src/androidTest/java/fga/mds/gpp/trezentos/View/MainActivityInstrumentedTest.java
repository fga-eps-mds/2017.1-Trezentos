package fga.mds.gpp.trezentos.View;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static fga.mds.gpp.trezentos.R.id.frame;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> rule
            = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() {
        UserAccountControl.getInstance(rule.getActivity()).authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(rule.getActivity()).validateSignInResponse();
    }

    @Test
    public void shouldCallClassFragment(){

        onView(withId(R.id.salas_item))
                .perform(click());

        assertThat(rule.getActivity().
                getSupportFragmentManager().findFragmentById(frame).getTag(),
                    is(equalTo("fragment_class")));

    }

    @Test
    public void shouldCallUserFragment(){

        onView(withId(R.id.usuario_item))
                .perform(click());

        assertThat(rule.getActivity()
                .getSupportFragmentManager().findFragmentById(frame)
                    .getTag(), is(equalTo("fragment_user")));

    }

    @Test
    public void shouldCallAboutFragment(){

        onView(withId(R.id.about_item))
                .perform(click());

        assertThat(rule.getActivity()
                .getSupportFragmentManager().findFragmentById(frame)
                    .getTag(), is(equalTo("fragment_about")));

    }

    @Test
    public void shouldCallEvaluationFragment(){

        onView(withId(R.id.avaliacao_item))
                .perform(click());

        assertThat(rule.getActivity().
                getSupportFragmentManager().findFragmentById(frame)
                    .getTag(), is(equalTo("fragment_evaluation")));

    }

}
