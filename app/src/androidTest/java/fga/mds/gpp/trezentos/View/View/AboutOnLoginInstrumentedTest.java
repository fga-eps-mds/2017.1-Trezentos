package fga.mds.gpp.trezentos.View.View;


import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.AboutFragment;
import fga.mds.gpp.trezentos.View.AboutOnLogin;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static fga.mds.gpp.trezentos.R.id.about_on_login;
import static fga.mds.gpp.trezentos.R.id.frame;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class AboutOnLoginInstrumentedTest {

    int NUMBER_OF_ITENS_LISTVIEW = 7;

    @Rule
    public ActivityTestRule<AboutOnLogin> aboutRule =
            new ActivityTestRule<>(AboutOnLogin.class);


    @Before
    public void setUp() {
        UserAccountControl.getInstance(aboutRule.getActivity()).authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(aboutRule.getActivity()).validateSignInResponse();
    }

    @Test
    public void shouldCountItemsInListView() {

        AboutFragment frag = (AboutFragment) aboutRule.getActivity()
                .getSupportFragmentManager()
                    .findFragmentById(about_on_login);

        ListView listview = (ListView)
                frag.getActivity().findViewById(R.id.about_list_view);

        assertThat(listview.getAdapter().getCount(),
                is(NUMBER_OF_ITENS_LISTVIEW));
    }


}
