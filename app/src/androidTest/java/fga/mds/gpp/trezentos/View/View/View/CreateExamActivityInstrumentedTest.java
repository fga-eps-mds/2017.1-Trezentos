package fga.mds.gpp.trezentos.View.View.View;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Controller.UserExamControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.MainActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static fga.mds.gpp.trezentos.R.id.floating_btn;
import static fga.mds.gpp.trezentos.R.id.frame;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class CreateExamActivityInstrumentedTest extends
        ActivityInstrumentationTestCase2<MainActivity>{

    UserClassControl userClassControl;

    @Rule
    public ActivityTestRule<MainActivity> rule =
           new ActivityTestRule<>(MainActivity.class);

    public CreateExamActivityInstrumentedTest(){
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        UserAccountControl.getInstance(rule.getActivity())
                .authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(rule.getActivity())
                .validateSignInResponse();
    }

    @Test
    public void shouldAssertNotNullActivity(){
        onView(withId(R.id.salas_item))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.recycler))
                .atPosition(0).perform(click());
        onView(withId(floating_btn))
                .perform(click());
        assertNotNull(rule);
    }

    @Test
    public void shouldValidateNullExamName() throws UserException {
        onView(withId(R.id.salas_item))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.recycler))
                .atPosition(0).perform(click());
        onView(withId(floating_btn))
                .perform(click());
        onView(withId(R.id.exam_name))
                .perform(typeText
                        (""));
        closeSoftKeyboard();
        onView(withId(R.id.ok_create_button))
                .perform(click());
        onView(withId(R.id.exam_name))
                .check(matches(hasErrorText
                        ("Preencha todos os campos!")));

    }

    @Test
    public void shouldValidadeExamNameMinLength() throws UserException{
        onView(withId(R.id.salas_item))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.recycler))
                .atPosition(0).perform(click());
        onView(withId(floating_btn))
                .perform(click());
        onView(withId(R.id.exam_name))
                .perform(typeText
                        ("A"));
        closeSoftKeyboard();
        onView(withId(R.id.ok_create_button))
                .perform(click());
        onView(withId(R.id.exam_name))
                .check(matches(hasErrorText
                        ("O nome da prova " +
                                "deve ter entre 2 e 15 caracteres.")));
    }

    @Test
    public void shouldValidadeExamNameMaxLength() throws UserException{
        onView(withId(R.id.salas_item))
                .perform(click());
        onData(anything()).inAdapterView(withId(R.id.recycler))
                .atPosition(0).perform(click());
        onView(withId(floating_btn))
                .perform(click());
        onView(withId(R.id.exam_name))
                .perform(typeText
                        ("P1 - Método de Desenvolvimento de Software"));
        closeSoftKeyboard();
        onView(withId(R.id.ok_create_button))
                .perform(click());
        onView(withId(R.id.exam_name))
                .check(matches(hasErrorText
                        ("O nome da prova " +
                                "deve ter entre 2 e 15 caracteres.")));
    }
}