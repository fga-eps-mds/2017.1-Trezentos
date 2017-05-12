package fga.mds.gpp.trezentos.View;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Controller.UserClassControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class CreateExamActivityInstrumentedTest {

    UserClassControl userClassControl;

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {

        UserAccountControl.getInstance(rule.getActivity())
                .authenticateLogin("teste@gmail.com", "123456");
        UserAccountControl.getInstance(rule.getActivity())
                .validateSignInResponse();
        onView(withId(R.id.floating_btn))
                .perform(click());
        onView(withId(R.id.edit_text_class_name))
                .perform(typeText("MDS"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_institution))
                .perform(typeText("UnB"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_class_password))
                .perform(typeText("Senha1"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_cut_grade))
                .perform(typeText(String.valueOf(4.5)));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_size_groups))
                .perform(typeText(String.valueOf(5)));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_addition))
                .perform(typeText(String.valueOf(0.5)));
        closeSoftKeyboard();
        onView(withId(R.id.button_save))
                .perform(click());
    }

    @Test
    public void shouldAssertNotNullActivity(){
        assertNotNull(rule);
    }

    @Test
    public void shouldValidateNullExamName() throws UserException {
        onView(withId(R.id.exam_name))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.ok_create_button))
                .perform(click());
        onView(withId(R.id.exam_name)).
                check(matches(hasErrorText("Preencha todos os campos!")));
    }

    @Test
    public void shouldValidadeExamNameMinLength() throws UserException{
        onView(withId(R.id.exam_name))
                .perform(typeText
                        ("A"));
        closeSoftKeyboard();
        onView(withId(R.id.ok_create_button))
                .perform(click());
        onView(withId(R.id.exam_name))
                .check(matches(hasErrorText
                        ("O nome da prova deve ter de 2 a 15 caracteres.")));
    }

    @Test
    public void shouldValidadeExamNameMaxLength() throws UserException{
        onView(withId(R.id.exam_name))
                .perform(typeText
                        ("P1 - Método de Desenvolvimento de Software"));
        closeSoftKeyboard();
        onView(withId(R.id.ok_create_button))
                .perform(click());
        onView(withId(R.id.exam_name))
                .check(matches(hasErrorText
                        ("O nome da prova deve ter de 2 a 15 caracteres.")));
    }
}