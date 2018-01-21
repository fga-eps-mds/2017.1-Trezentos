//
//package fga.mds.gpp.trezentos.Controller;
//
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.util.Log;
//
//import org.json.JSONException;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//
//import java.util.ArrayList;
//import java.util.concurrent.ExecutionException;
//
//import fga.mds.gpp.trezentos.BuildConfig;
//import fga.mds.gpp.trezentos.Exception.UserClassException;
//import fga.mds.gpp.trezentos.Exception.UserException;
//import fga.mds.gpp.trezentos.Model.UserClass;
//import fga.mds.gpp.trezentos.View.ClassActivity;
//import fga.mds.gpp.trezentos.View.LoginActivity;
//import fga.mds.gpp.trezentos.View.MainActivity;
//
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertNotSame;
//import static junit.framework.Assert.assertTrue;
//
//
//@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)
//public class UserClassControlUnitTest {
//
//    public UserClassControl testUser;
//    private MainActivity activity;
//
//    @Before
//    public void setUp() {
//        activity = Robolectric.buildActivity(MainActivity.class).get();
//    }
//
//
//    @Test
//    public void ShouldValidateNullName() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation(null, "UnB", "4.5", "Senha1", "0.5", "1");
//
//        assertTrue(isValid.equals("Preencha todos os campos!"));
//    }
//
//    @Test
//    public void ShouldValidateNullInstitution() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("Calculo 1", null, "4.5", "Senha1", "0.5", "1");
//
//
//        assertTrue(isValid.equals("Sucesso"));
//
//    }
//
//
//    @Test
//    public void ShouldValidateZeroCutOff() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("Calculo 1", "UnB", "0", "Senha1", "0.5", "1");
//
//
//        assertTrue(isValid.equals("A nota de corte nao pode ser zero."));
//
//
//    }
//
//
//    @Test
//    public void ShouldValidateNullClassPassword() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("Calculo 1", "UnB", "4.5", null, "0.5", "1");
//
//
//        assertTrue(isValid.equals("Preencha todos os campos!"));
//    }
//
//    @Test
//    public void ShouldValidateZeroAddition() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("Calculo 1", "UnB", "4.5", "Senha1", "0", "5");
//
//
//        assertTrue(isValid.equals("O acrescimo nao pode ser zero."));
//
//
//    }
//
//    @Test
//    public void ShouldValidateZeroGroupSize() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("Calculo 1", "UnB", "4.5", "Senha1", "0.5", "0");
//
//
//        assertTrue(isValid.equals("O tamanho do grupo nao pode ser zero."));
//
//    }
//
//
//    @Test
//    public void ShouldValidateClassNameMaxLength() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("Metodo De Desenvolvimento de Software"
//                , "UnB", "4.5", "Senha1", "0.5", "5");
//
//        assertTrue(isValid.equals("O nome da sala deve ter de 3 a 20 caracteres."));
//    }
//
//    @Test
//    public void ShouldValidateClassNameMinLength() throws UserException {
//
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("DS", "UnB", "4.5", "Senha1", "0.5", "5");
//
//        assertTrue(isValid.equals("O nome da sala deve ter de 3 a 20 caracteres."));
//
//    }
//
//    @Test
//    public void ShouldValidatePasswordMinLength() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("MDS", "UnB", "4.5", "Senha", "0.5", "5");
//
//        assertTrue(isValid.equals("A senha deve ter entre 6 e 16 caracteres"));
//
//    }
//
//    @Test
//    public void ShouldValidatePasswordMaxLength() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("MDS", "UnB", "4.5", "Esqueciminhasenha", "0.5", "5");
//
//        assertTrue(isValid.equals("A senha deve ter entre 6 e 16 caracteres"));
//
//    }
//
//    @Test
//    public void ShouldValidateInstituitionMinLength() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("MDS", "IF", "4.5", "Senha1", "0.5", "5");
//
//        assertTrue(isValid.equals("O nome da instituicao deve ter de 3 a 20 caracteres."));
//
//    }
//
//    @Test
//    public void ShouldValidateInstituitionMaxLength() throws UserException {
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        String isValid;
//
//        isValid = testUser.validateInformation("MDS", "Universidade de Brasilia",
//                                                    "4.5", "Senha1", "0.5", "5");
//
//        assertTrue(isValid.equals("O nome da instituicao deve ter de 3 a 20 caracteres."));
//
//    }
//
//
//    @Test
//    public void shouldValidateJoinClass() throws UserException, InterruptedException, ExecutionException, UserClassException {
//        UserClass userClass = new UserClass();
//        String password = "123456";
//        String studentEmail = "testJoinClass@gmail.com";
//        ArrayList <String> arrayStudents = new ArrayList<String>();
//
//        arrayStudents.add("ana");
//        arrayStudents.add("pedro");
//
//        userClass.setStudents(arrayStudents);
//        userClass.setSizeGroups(4);
//        userClass.setPassword("123456");
//        userClass.setAddition(5.5f);
//        userClass.setClassName("Test Join Class");
//        userClass.setCutOff(3.5f);
//        userClass.setInstitution("UNB");
//        //userClass.setOwnerEmail("testOwner@gmail.com");
//
//        String expectedResponse = null;
//        String serverResponse;
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        serverResponse= testUser.validateJoinClass(userClass, password, studentEmail);
//
//        assertNotSame(serverResponse, expectedResponse  );
//    }
//
//
//    @Rule
//    public ExpectedException expected = ExpectedException.none();
//
//    @Test (expected = UserClassException.class)
//    public void shouldValidateJoinClassExceptionWrongPassword() throws UserException, InterruptedException, ExecutionException, UserClassException {
//        UserClass userClass = new UserClass();
//        String password = "1353";
//        String studentEmail = "testJoinClass@gmail.com";
//        ArrayList <String> arrayStudents = new ArrayList<String>();
//
//        arrayStudents.add("ana");
//        arrayStudents.add("pedro");
//
//        userClass.setStudents(arrayStudents);
//        userClass.setSizeGroups(4);
//        userClass.setPassword("123456");
//        userClass.setAddition(5.5f);
//        userClass.setClassName("Test Join Class");
//        userClass.setCutOff(3.5f);
//        userClass.setInstitution("UNB");
//        //userClass.setOwnerEmail("testOwner@gmail.com");
//
//        String expectedResponse = null;
//        String serverResponse;
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        serverResponse = testUser.validateJoinClass(userClass, password, studentEmail);
//
//        assertNotSame(expectedResponse, serverResponse);
//    }
//
//    @Test (expected = UserClassException.class)
//    public void shouldValidateJoinClassExceptionEmptyPassword() throws UserException, InterruptedException, ExecutionException, UserClassException {
//        UserClass userClass = new UserClass();
//        String password = "";
//        String studentEmail = "testJoinClass@gmail.com";
//        ArrayList <String> arrayStudents = new ArrayList<String>();
//
//        arrayStudents.add("ana");
//        arrayStudents.add("pedro");
//
//        userClass.setStudents(arrayStudents);
//        userClass.setSizeGroups(4);
//        userClass.setPassword("123456");
//        userClass.setAddition(5.5f);
//        userClass.setClassName("Test Join Class");
//        userClass.setCutOff(3.5f);
//        userClass.setInstitution("UNB");
//        //userClass.setOwnerEmail("testOwner@gmail.com");
//
//        String expectedResponse = null;
//        String serverResponse;
//
//        testUser = UserClassControl.getInstance(activity.getApplicationContext());
//        serverResponse = testUser.validateJoinClass(userClass, password, studentEmail);
//
//        assertNotSame(expectedResponse, serverResponse);
//    }
//
//
//}
