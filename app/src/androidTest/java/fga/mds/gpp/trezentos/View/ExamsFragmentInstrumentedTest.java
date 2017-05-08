package fga.mds.gpp.trezentos.View;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class ExamsFragmentInstrumentedTest {

    @Rule
    public ActivityTestRule<ClassActivity> classRule =
            new ActivityTestRule<>(ClassActivity.class);

    @Test
    public void shouldValidateExamsFragmentInitialization(){

        onView(ViewMatchers.withText("EXAMS"))
                .perform(click());

        assertNotNull(classRule);

    }

//    @Test
//    public void shouldClickOnExamRegistered(){
//
//        ListView listView;
//
//        ExamsFragment examsFragment;
//
//        examsFragment = (ExamsFragment) classRule.getActivity()
//                .getSupportFragmentManager()
//                .findFragmentById(R.id.view_pager);
//
//        onView(ViewMatchers.withText("EXAMS"))
//                .perform(click());
//
//        listView = (ListView) examsFragment.getActivity()
//                .findViewById(R.id.list);
//
//        Log.d("Quantidade", String.valueOf(listView.getAdapter().getCount()));
//
//        if(listView.getAdapter().getCount() > 0) {
//
//            onView(withId(R.id.list))
//                    .perform(click());
//        }
//
//        assertNotNull(examsFragment);
//    }

    @Test
    public void shouldClickOnExamButton(){

        onView(ViewMatchers.withText("EXAMS"))
                .perform(click());

        onView(withId(R.id.floating_btn_add_exams)).perform(click());

        assertNotNull(classRule);

    }

}
