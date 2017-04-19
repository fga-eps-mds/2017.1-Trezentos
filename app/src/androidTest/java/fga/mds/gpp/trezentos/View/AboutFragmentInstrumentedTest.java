package fga.mds.gpp.trezentos.View;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.core.deps.guava.base.Predicate;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Set;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.AboutFragment;
import fga.mds.gpp.trezentos.View.AboutOnLogin;
import fga.mds.gpp.trezentos.View.LoginActivity;
import fga.mds.gpp.trezentos.View.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static fga.mds.gpp.trezentos.R.id.about_on_login;
import static fga.mds.gpp.trezentos.R.id.frame;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class AboutFragmentInstrumentedTest {

    int NUMBER_OF_ITENS_LISTVIEW = 7;
    Intent startedIntent;

    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<>(MainActivity.class);
    public ActivityTestRule<AboutOnLogin> aboutRule = new ActivityTestRule<>(AboutOnLogin.class);

    // AboutFragment test in Main Activity
    @Test
    public void shouldValidateFragmentInicialization() throws UserException {
        onView(ViewMatchers.withId(R.id.about_item))
                .perform(click());
        assertNotNull(mainRule);
    }

    @Test
    public void shouldCountItemsInListView() {
        onView(withId(R.id.about_item))
                .perform(click());

        AboutFragment frag = (AboutFragment) mainRule.getActivity()
                .getSupportFragmentManager().findFragmentById(frame);
        ListView listview = (ListView) frag.getActivity().findViewById(R.id.about_list_view);
        assertThat(listview.getAdapter().getCount(), is(NUMBER_OF_ITENS_LISTVIEW));
    }
}