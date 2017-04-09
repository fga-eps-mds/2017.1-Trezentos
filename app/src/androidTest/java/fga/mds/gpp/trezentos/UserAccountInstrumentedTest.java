package fga.mds.gpp.trezentos;

import android.support.test.rule.ActivityTestRule;

import junit.framework.Assert;

import org.hamcrest.Matcher;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
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

        onView(withId(R.id.edit_text_email)).check(matches(hasErrorText("O email não pode estar vazio")));
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

        onView(withId(R.id.edit_text_password)).check(matches(hasErrorText("A senha não pode estar vazia")));
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

        onView(withId(R.id.edit_text_name_register)).check(matches(hasErrorText("O nome não pode estar vazio")));
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

        onView(withId(R.id.edit_text_name_register)).check(matches(hasErrorText("O nome deve ter de 3 a 50 caracteres")));
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

        onView(withId(R.id.edit_text_name_register)).check(matches(hasErrorText("O nome deve ter de 3 a 50 caracteres")));
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

        onView(withId(R.id.edit_text_email_register)).check(matches(hasErrorText("O email não pode estar vazio")));
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

        onView(withId(R.id.edit_text_email_register)).check(matches(hasErrorText("Email com caracteres inválidos. Tente novamente")));
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

        onView(withId(R.id.edit_text_password_register)).check(matches(hasErrorText("A senha não pode estar vazia")));
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

        onView(withId(R.id.edit_text_password_register)).check(matches(hasErrorText("A senha deve ter entre 6 e 16 caracteres")));
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

        onView(withId(R.id.edit_text_password_confirmation)).check(matches(hasErrorText("Senhas não coincidem, tente novamente")));
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

        onView(withId(R.id.edit_text_password_confirmation)).check(matches(hasErrorText("Senhas não coincidem, tente novamente")));
    }

}
