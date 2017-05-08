package fga.mds.gpp.trezentos.View;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static fga.mds.gpp.trezentos.R.id.frame;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class ForgotPasswordActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<ForgotPasswordActivity> rule
            = new ActivityTestRule<>(ForgotPasswordActivity.class);

    @Test
    public void shouldAssertNotNull(){

        assertNotNull(rule);

    }

}
