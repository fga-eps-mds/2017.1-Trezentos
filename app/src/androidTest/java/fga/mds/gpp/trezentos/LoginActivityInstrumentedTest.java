package fga.mds.gpp.trezentos;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;
import android.widget.Toast;

import junit.framework.Assert;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Random;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Util.PasswordUtil;
import fga.mds.gpp.trezentos.View.AboutFragment;
import fga.mds.gpp.trezentos.View.AboutOnLogin;
import fga.mds.gpp.trezentos.View.LoginActivity;
import fga.mds.gpp.trezentos.View.MainActivity;
import fga.mds.gpp.trezentos.View.SignUpActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static fga.mds.gpp.trezentos.R.id.about_on_login;
import static fga.mds.gpp.trezentos.R.layout.fragment_about;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
    public class LoginActivityInstrumentedTest {

    int NUMBER_OF_ITENS_LISTVIEW = 7;

        @Rule
        public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class);

        @Test
        public void shouldValidateNullEmailLogin() throws UserException {
            onView(withId(R.id.edit_text_email))
                    .perform(typeText(""));
            closeSoftKeyboard();
            onView(withId(R.id.edit_text_password))
                    .perform(typeText("Aluno1"));
            closeSoftKeyboard();
            onView(withId(R.id.button_login))
                    .perform(click());
            onView(withId(R.id.edit_text_email)).check(matches(hasErrorText
                    ("O email não pode estar vazio")));
        }

        @Test
        public void shouldValidateNullPasswordLogin() throws UserException {
            onView(withId(R.id.edit_text_email))
                    .perform(typeText("Aluno1@aluno.com"));
            closeSoftKeyboard();
            onView(withId(R.id.edit_text_password))
                    .perform(typeText(""));
            closeSoftKeyboard();
            onView(withId(R.id.button_login))
                    .perform(click());
            onView(withId(R.id.edit_text_password)).check(matches(hasErrorText
                    ("A senha não pode estar vazia")));
        }

        @Test
        public void shouldValidateValidLogin() throws UserException, InterruptedException {
            onView(withId(R.id.edit_text_email))
                    .perform(typeText(("teste@email.com")));
            closeSoftKeyboard();
            onView(withId(R.id.edit_text_password))
                    .perform(typeText("testeFinal12"));
            closeSoftKeyboard();
            onView(withId(R.id.button_login))
                    .perform(click());
        }

    // AboutFragment test before Login
    @Test
    public void shouldValidateAboutFragmentInAboutOnLogin() throws UserException {
        onView(withId(R.id.button_about))
                .perform(click());
        assertNotNull(rule);
    }
}
