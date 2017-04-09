package fga.mds.gpp.trezentos;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.widget.Toast;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.View.LoginActivity;
import fga.mds.gpp.trezentos.View.SignUpActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.setFailureHandler;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class UserAccountInstrumentedTest {

    @Rule
    public ActivityTestRule<SignUpActivity> rule = new ActivityTestRule<>(SignUpActivity.class);

    @Test
    public void shouldValidateNullName() throws UserException{

            onView(withId(R.id.editTextNameRegister))
                    .perform(typeText(""));
                closeSoftKeyboard();
            onView(withId(R.id.editTextEmailRegister))
                    .perform(typeText("Aluno1@aluno.com"));
                closeSoftKeyboard();
            onView(withId(R.id.editTextPasswordRegister))
                    .perform(typeText("Aluno1"));
                closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordConfirmation))
                    .perform(typeText("Aluno1"));
                closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                    .perform(click());

        }

    @Test
    public void shouldValidateInsuficientName() throws UserException{

        onView(withId(R.id.editTextNameRegister))
                .perform(typeText("Al"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextEmailRegister))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordRegister))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordConfirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateMaxCharName() throws UserException{

        onView(withId(R.id.editTextNameRegister))
                .perform(typeText("Pedro de Alcantara Joao Carlos Leopoldo Salvador Bibiano Francisco Xavier"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextEmailRegister))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordRegister))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordConfirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateNullEmail() throws UserException{

        onView(withId(R.id.editTextNameRegister))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextEmailRegister))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordRegister))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordConfirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateInvalidEmail() throws UserException{

        onView(withId(R.id.editTextNameRegister))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextEmailRegister))
                .perform(typeText("aluno!!!@aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordRegister))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordConfirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateNullPassword() throws UserException{

        onView(withId(R.id.editTextNameRegister))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextEmailRegister))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordRegister))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordConfirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }


    @Test
    public void shouldValidateMinPassword() throws UserException{

        onView(withId(R.id.editTextNameRegister))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextEmailRegister))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordRegister))
                .perform(typeText("asdq"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordConfirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateNullPasswordConfirmation() throws UserException{

        onView(withId(R.id.editTextNameRegister))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextEmailRegister))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordRegister))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordConfirmation))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }


    @Test
    public void shouldValidateInvalidPassword() throws UserException{

        onView(withId(R.id.editTextNameRegister))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextEmailRegister))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordRegister))
                .perform(typeText("Aluno123"));
        closeSoftKeyboard();
        onView(withId(R.id.editTextPasswordConfirmation))
                .perform(typeText("Aluno12"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

}
