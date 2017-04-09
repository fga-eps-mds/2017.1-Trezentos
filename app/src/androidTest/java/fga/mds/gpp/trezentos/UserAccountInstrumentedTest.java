package fga.mds.gpp.trezentos;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.View.LoginActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class UserAccountInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void shouldValidateNullEmailLogin() throws UserException{
        onView(withId(R.id.edit_text_email))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.button_login))
                .perform(click());

    }


    @Test
    public void shouldValidateNullPasswordLogin() throws UserException{
        onView(withId(R.id.edit_text_email))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.button_login))
                .perform(click());

    }



    @Test
    public void shouldValidateNullName() throws UserException{
            onView(withId(R.id.button_register))
                    .perform(click());
            onView(withId(R.id.edit_text_name_register))
                    .perform(typeText(""));
                closeSoftKeyboard();
            onView(withId(R.id.edit_text_email_register))
                    .perform(typeText("Aluno1@aluno.com"));
                closeSoftKeyboard();
            onView(withId(R.id.edit_text_password_register))
                    .perform(typeText("Aluno1"));
                closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_confirmation))
                    .perform(typeText("Aluno1"));
                closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                    .perform(click());

        }

    @Test
    public void shouldValidateInsuficientName() throws UserException{
        onView(withId(R.id.button_register))
                .perform(click());
        onView(withId(R.id.edit_text_name_register))
                .perform(typeText("Al"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_email_register))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_register))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_confirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateMaxCharName() throws UserException{
        onView(withId(R.id.button_register))
                .perform(click());
        onView(withId(R.id.edit_text_name_register))
                .perform(typeText("Pedro de Alcantara Joao Carlos Leopoldo Salvador Bibiano Francisco Xavier"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_email_register))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_register))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_confirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateNullEmail() throws UserException{
        onView(withId(R.id.button_register))
                .perform(click());
        onView(withId(R.id.edit_text_name_register))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_email_register))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_register))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_confirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateInvalidEmail() throws UserException{
        onView(withId(R.id.button_register))
                .perform(click());
        onView(withId(R.id.edit_text_name_register))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_email_register))
                .perform(typeText("aluno!!!@aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_register))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_confirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateNullPassword() throws UserException{
        onView(withId(R.id.button_register))
                .perform(click());
        onView(withId(R.id.edit_text_name_register))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_email_register))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_register))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_confirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }


    @Test
    public void shouldValidateMinPassword() throws UserException{
        onView(withId(R.id.button_register))
                .perform(click());
        onView(withId(R.id.edit_text_name_register))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_email_register))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_register))
                .perform(typeText("asdq"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_confirmation))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

    @Test
    public void shouldValidateNullPasswordConfirmation() throws UserException{
        onView(withId(R.id.button_register))
                .perform(click());
        onView(withId(R.id.edit_text_name_register))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_email_register))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_register))
                .perform(typeText("Aluno1"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_confirmation))
                .perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }


    @Test
    public void shouldValidateInvalidPassword() throws UserException{
        onView(withId(R.id.button_register))
                .perform(click());
        onView(withId(R.id.edit_text_name_register))
                .perform(typeText("Aluno"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_email_register))
                .perform(typeText("Aluno1@aluno.com"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_register))
                .perform(typeText("Aluno123"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_text_password_confirmation))
                .perform(typeText("Aluno12"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_button))
                .perform(click());

    }

}
