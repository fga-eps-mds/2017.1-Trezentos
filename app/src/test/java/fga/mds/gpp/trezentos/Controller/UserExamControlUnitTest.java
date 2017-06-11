package fga.mds.gpp.trezentos.Controller;

import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fga.mds.gpp.trezentos.BuildConfig;
import fga.mds.gpp.trezentos.DAO.AddSecondGrades;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.ClassActivity;
import fga.mds.gpp.trezentos.View.CreateExamActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import dalvik.annotation.TestTargetClass;
import fga.mds.gpp.trezentos.BuildConfig;
import fga.mds.gpp.trezentos.Exception.UserClassException;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Exam;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.ClassActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UserExamControlUnitTest {

    private UserExamControl testUser;
    private ClassActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(ClassActivity.class).get();
    }

    @Test
    public void ShouldValidateNullExamName() throws UserException{

        String isValid;
        testUser = UserExamControl.getInstance(activity.getApplicationContext());
        isValid = testUser.validateInformation(null, "Usuario", "exemplo@dominio.com");
        assertEquals(isValid, "O nome não pode ser vazio");
    }

    @Test
    public void ShouldValidateExam()throws  UserException{
        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;
        isValid = testUser.validateInformation("Teste", "Usuario", "exemplo@dominio.com");
        assertEquals(isValid, "Sucesso");
    }

    @Test
    public void ShouldValidateNullUserClassName() throws UserException{

        String isValid;
        testUser = UserExamControl.getInstance(activity.getApplicationContext());
        isValid = testUser.validateInformation("P1", null, "exemplo@dominio.com");
        assertEquals(isValid, "O nome não pode ser vazio");
    }

    @Test
    public void ShouldValidateNullClassOwnerEmail() throws UserException{

        String isValid;
        testUser = UserExamControl.getInstance(activity.getApplicationContext());
        isValid = testUser.validateInformation("P1", "Usuario", null);
        assertEquals(isValid, "O email não pode estar vazio");
    }

    @Test
    public void ShouldValidateExamNameMaxLength() throws UserException{

        String isValid;
        testUser = UserExamControl.getInstance(activity.getApplicationContext());
        isValid = testUser.validateInformation(
                "Prova 1 do professor Fragelli da UnB",
                "Usuario",
                "exemplo@dominio.com");
        assertEquals(isValid, "O nome da prova deve ter entre 2 e 15 caracteres.");
    }

    @Test
    public void ShouldValidateExamNameMinLength() throws UserException{

        String isValid;
        testUser = UserExamControl.getInstance(activity.getApplicationContext());
        isValid = testUser.validateInformation("P", "Usuario", "exemplo@dominio.com");
        assertEquals(isValid, "O nome da prova deve ter entre 2 e 15 caracteres.");
    }

    @Test
    public void ShouldValidateAddSecondGrade() throws ExecutionException, InterruptedException, UserException {
        Exam exam = new Exam();
        UserClass userClass = new UserClass();
        ArrayList<String> arrayTeste = new ArrayList<String>();

        arrayTeste.add("Carol");
        arrayTeste.add("Ana");
        arrayTeste.add("Pedro");

        exam.setNameExam("teste");
        exam.setClassOwnerEmail("teste@teste1.com");
        exam.setFirstGrades("icaro@icaro.com=0.00, carol@carol.com=1.00");
        exam.setSecondGrades("um@carol.com=7.00, carol@carol.com=8.00");
        exam.setUserClassName("Pedro");

        userClass.setClassName("Ana Linda");
        userClass.setAddition(7.8f);
        userClass.setCutOff(5.3f);
        userClass.setInstitution("Unb");
        userClass.setOwnerEmail("teste@teste1.com");
        userClass.setPassword("123456");
        userClass.setSizeGroups(3);
        userClass.setStudents(arrayTeste);

        testUser = UserExamControl.getInstance(activity.getApplicationContext());
        String serverResponse = testUser.addSecondGrade(userClass,exam);
        assertTrue(serverResponse, true);

    }

    @Test
    public void ShouldValidateAddsFirstGrade() throws UserException, InterruptedException, ExecutionException, UserClassException {
        Exam exam = new Exam();
        UserClass userClass = new UserClass();
        ArrayList<String> arrayTeste = new ArrayList<String>();

        arrayTeste.add("Carol");
        arrayTeste.add("Ana");
        arrayTeste.add("Pedro");

        exam.setNameExam("teste");
        exam.setClassOwnerEmail("teste@teste1.com");
        exam.setFirstGrades("icaro@icaro.com=0.00, carol@carol.com=1.00");
        exam.setUserClassName("Pedro");

        userClass.setClassName("Ana Linda");
        userClass.setAddition(7.8f);
        userClass.setCutOff(5.3f);
        userClass.setInstitution("Unb");
        userClass.setOwnerEmail("teste@teste1.com");
        userClass.setPassword("123456");
        userClass.setSizeGroups(3);
        userClass.setStudents(arrayTeste);

        testUser = UserExamControl.getInstance(activity.getApplicationContext());
        String serverResponse = testUser.validateAddsFirstGrade(userClass,exam);
        assertTrue(serverResponse, true);
    }

    @Test
    public void shouldValidateGetExamsFromUser() throws UserException{
        ArrayList <Exam> arrayList = new ArrayList<Exam>();
        String email = "teste@gmail.com";
        String pasword = "123456";

        testUser = UserExamControl.getInstance(activity.getApplicationContext());
        arrayList = testUser.getExamsFromUser(email, pasword);

        assertNotNull(arrayList);
    }

}
