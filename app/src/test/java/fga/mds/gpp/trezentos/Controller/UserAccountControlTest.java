//package fga.mds.gpp.trezentos.Controller;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//
//import fga.mds.gpp.trezentos.BuildConfig;
//import fga.mds.gpp.trezentos.Controller.UserAccountControl;
//import fga.mds.gpp.trezentos.Exception.UserException;
//import fga.mds.gpp.trezentos.View.SignInActivity;
//
//import static junit.framework.Assert.assertNotSame;
//import static junit.framework.TestCase.assertEquals;
//import static junit.framework.TestCase.assertFalse;
//
//@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)
//public class    UserAccountControlTest {
//
//    UserAccountControl testUser;
//    private SignInActivity activity;
//
//    @Before
//    public void setUp() {
//        activity = Robolectric.buildActivity(SignInActivity.class).get();
//    }
///*
//    @Test
//    public void shouldValitadeNullName() throws UserException {
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        String errorMessage = testUser.validateSignUp(null, "aluno@email.com", "Senha1", "Senha1");
//        assertEquals(errorMessage, "O nome não pode estar vazio");
//    }
//
//    @Test
//    public void shouldValitadeNullEmail() throws UserException {
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        String errorMessage = testUser.validateSignUp("Carla", null, "Senha1", "Senha1");
//        assertEquals(errorMessage, "O email não pode estar vazio");
//    }
//
//    @Test
//    public void shouldValitadeNullPassword() throws UserException {
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        String errorMessage = testUser.validateSignUp("Carla", "aluno@email.com", null, "Senha1");
//        assertEquals(errorMessage, "A senha não pode estar vazia");
//    }
//
//    @Test
//    public void shouldValitadeNullPasswordConfirmation() throws UserException {
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        String errorMessage = testUser.validateSignUp("Carla", "aluno@email.com", "Senha1", null);
//        assertEquals(errorMessage, "Senhas não coincidem, tente novamente");
//    }
//
//    @Test
//    public void shouldValitadeNameMinLength() throws UserException {
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        String errorMessage = testUser.validateSignUp("Ei", "aluno@email.com", "Senha1", "Senha1");
//        assertEquals(errorMessage, "O nome deve ter de 3 a 50 caracteres");
//    }
//
//    @Test
//    public void shouldValitadeNameMaxLength() throws UserException {
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        String errorMessage = testUser.validateSignUp(
//                "O trezentos é metodo de aprendizagem ativa e colaborativa"
//                , "aluno@email.com", "Senha1", "Senha1");
//        assertEquals(errorMessage, "O nome deve ter de 3 a 50 caracteres");
//    }
//
//    @Test
//    public void shouldValitadePasswordMinLength() throws UserException {
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        String errorMessage = testUser.validateSignUp("Carla", "aluno@email.com", "Senha", "Senha");
//        assertEquals(errorMessage, "A senha deve ter entre 6 e 16 caracteres");
//    }
//
//    @Test
//    public void shouldValitadePasswordMaxLength() throws UserException {
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        String errorMessage = testUser.validateSignUp("Carla"
//                , "aluno@email.com", "Vintecaracteresusados", "Vintecaracteresusados");
//        assertEquals(errorMessage, "A senha deve ter entre 6 e 16 caracteres");
//    }
//
//    @Test
//    public void shouldValitadePasswordMatchs() throws UserException {
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        String errorMessage = testUser.validateSignUp("Carla"
//                , "aluno@email.com", "Senha1", "Senha");
//        assertEquals(errorMessage, "Senhas não coincidem, tente novamente");
//    }
//
//    @Test
//    public void shoulValidateSignUp(){
//        String name = "test";
//        String email = "teste@testando.com";
//        String password = "123456";
//        String passwordConfirmation = "123456";
//        String errorMessageExpected = "";
//        String errorMessage;
//
//        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
//        errorMessage = testUser.validateSignUp(name, email, password, passwordConfirmation);
//
//        assertEquals(errorMessageExpected, errorMessage);
//    }
//
//    */
//
////    @Rule
////    public ExpectedException expected = ExpectedException.none();
////
////    @Test (expected = RuntimeException.class)
////    public void shouldValidateSignUpResponse(){
////        String serverResponseExpected = "404";
////        String serverResponse;
////
////        testUser = UserAccountControl.getInstance(activity.getApplicationContext());
////        serverResponse = testUser.validateSignUpResponse();
////
////       assertNotSame(serverResponseExpected, serverResponse);
////    }
//
//}
//
//
