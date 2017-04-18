package fga.mds.gpp.trezentos;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.View.LoginActivity;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UserAccountControlTest {

    UserAccountControl testUser;
    private LoginActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(LoginActivity.class).get();
    }

    @Test
<<<<<<< HEAD
    public void ShouldValitadeNullName() throws UserException {
=======
    public void shouldValitadeNullName() throws UserException {
>>>>>>> 85ee230cd7c299bf2c9e03a3fd9d89c2fdc7d134

        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
        String errorMessage = testUser.validateSignUp(null, "aluno@email.com", "Senha1", "Senha1");

        assertEquals(errorMessage, "O nome não pode estar vazio");
    }


    @Test
<<<<<<< HEAD
    public void ShouldValitadeNullEmail() throws UserException {
=======
    public void shouldValitadeNullEmail() throws UserException {
>>>>>>> 85ee230cd7c299bf2c9e03a3fd9d89c2fdc7d134

        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
        String errorMessage = testUser.validateSignUp("Carla", null, "Senha1", "Senha1");

        assertEquals(errorMessage, "O email não pode estar vazio");
    }


    @Test
<<<<<<< HEAD
    public void ShouldValitadeNullPassword() throws UserException {
=======
    public void shouldValitadeNullPassword() throws UserException {
>>>>>>> 85ee230cd7c299bf2c9e03a3fd9d89c2fdc7d134

        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
        String errorMessage = testUser.validateSignUp("Carla", "aluno@email.com", null, "Senha1");

        assertEquals(errorMessage, "A senha não pode estar vazia");
    }

    @Test
<<<<<<< HEAD
    public void ShouldValitadeNullPasswordConfirmation() throws UserException {
=======
    public void shouldValitadeNullPasswordConfirmation() throws UserException {
>>>>>>> 85ee230cd7c299bf2c9e03a3fd9d89c2fdc7d134

        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
        String errorMessage = testUser.validateSignUp("Carla", "aluno@email.com", "Senha1", null);

        assertEquals(errorMessage, "Senhas não coincidem, tente novamente");
    }


    @Test
<<<<<<< HEAD
    public void ShouldValitadeNameMinLength() throws UserException {
=======
    public void shouldValitadeNameMinLength() throws UserException {
>>>>>>> 85ee230cd7c299bf2c9e03a3fd9d89c2fdc7d134

        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
        String errorMessage = testUser.validateSignUp("Ei", "aluno@email.com", "Senha1", "Senha1");

        assertEquals(errorMessage, "O nome deve ter de 3 a 50 caracteres");
    }


    @Test
<<<<<<< HEAD
    public void ShouldValitadeNameMaxLength() throws UserException {
=======
    public void shouldValitadeNameMaxLength() throws UserException {
>>>>>>> 85ee230cd7c299bf2c9e03a3fd9d89c2fdc7d134

        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
        String errorMessage = testUser.validateSignUp(
                "O trezentos é metodo de aprendizagem ativa e colaborativa"
                , "aluno@email.com", "Senha1", "Senha1");

        assertEquals(errorMessage, "O nome deve ter de 3 a 50 caracteres");
    }


    @Test
<<<<<<< HEAD
    public void ShouldValitadePasswordMinLength() throws UserException {
=======
    public void shouldValitadePasswordMinLength() throws UserException {
>>>>>>> 85ee230cd7c299bf2c9e03a3fd9d89c2fdc7d134

        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
        String errorMessage = testUser.validateSignUp("Carla", "aluno@email.com", "Senha", "Senha");

        assertEquals(errorMessage, "A senha deve ter entre 6 e 16 caracteres");
    }

    @Test
<<<<<<< HEAD
    public void ShouldValitadePasswordMaxLength() throws UserException {
=======
    public void shouldValitadePasswordMaxLength() throws UserException {
>>>>>>> 85ee230cd7c299bf2c9e03a3fd9d89c2fdc7d134

        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
        String errorMessage = testUser.validateSignUp("Carla"
                , "aluno@email.com", "Vintecaracteresusados", "Vintecaracteresusados");

        assertEquals(errorMessage, "A senha deve ter entre 6 e 16 caracteres");
    }

    @Test
<<<<<<< HEAD
    public void ShouldValitadePasswordMatchs() throws UserException {
=======
    public void shouldValitadePasswordMatchs() throws UserException {
>>>>>>> 85ee230cd7c299bf2c9e03a3fd9d89c2fdc7d134

        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
        String errorMessage = testUser.validateSignUp("Carla"
                , "aluno@email.com", "Senha1", "Senha");

        assertEquals(errorMessage, "Senhas não coincidem, tente novamente");
    }
}


