package fga.mds.gpp.trezentos.View.View;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.SearchActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class SearchActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<SearchActivity> mainRule =
            new ActivityTestRule<>(SearchActivity.class);

    @Before
    public void setUp() {
        UserAccountControl.getInstance(mainRule.getActivity()).authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(mainRule.getActivity()).validateSignInResponse();
    }

/*
    @Test
    public void shouldSearchAnClass(){

        onView(withId(R.id.action_search))
                .perform(click());

        onView(isAssignableFrom(EditText.class))
                .perform(typeText("teste"), pressImeActionButton());

        closeSoftKeyboard();

        assertNotNull(mainRule);

    }
*/
    @Test
    public void shouldEnterOnClassFiltrered(){

        SearchActivity searchActivity;

        searchActivity = mainRule.getActivity();

        RecyclerView recyclerView =
                (RecyclerView) searchActivity.findViewById(R.id.recycler);

        if(recyclerView.getAdapter().getItemCount() > 0){
            onView(ViewMatchers.withId(R.id.recycler))
                    .perform(click());
        }

        assertNotNull(searchActivity);

    }

    @Test
    public void shouldClickOnPreviousActivity(){

        onView(withId(R.id.toolbar))
                .perform(click());

        assertNotNull(mainRule);

    }

}
