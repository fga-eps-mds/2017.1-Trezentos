package fga.mds.gpp.trezentos.View;

import android.support.test.espresso.action.TypeTextAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class SearchActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void shouldSearchAnClass(){

        onView(withId(R.id.search_classes))
                .perform(click());
        onView(withId(R.id.action_search))
                .perform(click());

//        onView(withId(R.id.action_search))
//                .perform(typeText("teste"));
//
//        closeSoftKeyboard();

        assertNotNull(mainRule);

    }

    @Test
    public void shouldEnterOnClassFiltrered(){

        onView(withId(R.id.action_search))
                .perform(click());

        onView(withId(R.id.search_classes))
                .perform(click());

        SearchActivity searchActivity;

        searchActivity = (SearchActivity) mainRule.getActivity()
                .getApplicationContext();

        RecyclerView recyclerView;

        recyclerView = (RecyclerView) searchActivity.findViewById(R.id.recycler);

        onView(withId(R.id.action_search))
                .perform(click());

        onView(withId(R.id.action_search))
                .perform(typeText("teste"));

        closeSoftKeyboard();

        if(recyclerView.getAdapter().getItemCount() > 0){
            onView(ViewMatchers.withId(R.id.recycler))
                    .perform(click());
        }

        assertNotNull(searchActivity);

    }

    @Test
    public void shouldClickOnPreviousActivity(){


    }

    @Test
    public void shouldHideToolBarWithUpScrollList(){


    }


}
