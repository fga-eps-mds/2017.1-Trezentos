package fga.mds.gpp.trezentos.Controller;


import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

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

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation(null, "Usuario", "exemplo@dominio.com");

        assertEquals(isValid, "O nome não pode ser vazio");

    }

    @Test
    public void ShouldValidateNullUserClassName() throws UserException{

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation("P1", null, "exemplo@dominio.com");

        assertEquals(isValid, "O nome não pode ser vazio");
    }

    @Test
    public void ShouldValidateNullClassOwnerEmail() throws UserException{

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation("P1", "Usuario", null);

        assertEquals(isValid, "O email não pode estar vazio");

    }

    @Test
    public void ShouldValidateExamNameMaxLength() throws UserException{

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation("Prova 1 do professor Fragelli da UnB",
                                                "Usuario", "exemplo@dominio.com");

        assertEquals(isValid, "O nome da prova deve ter entre 2 e 15 caracteres.");

    }

    @Test
    public void ShouldValidateExamNameMinLength() throws UserException{

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation("P", "Usuario", "exemplo@dominio.com");

        assertEquals(isValid, "O nome da prova deve ter entre 2 e 15 caracteres.");

    }

//    @Test
//    public void ShouldValidateAddsFirstGradeNullClassName() throws UserException, InterruptedException, ExecutionException, UserClassException {
//        Exam exam = new Exam();
//        UserClass userClass = new UserClass();
//
//        exam.setNameExam("Prova 1");
//        exam.setFirstGrade("icaro@icaro.com=0.00, carol@carol.com=1.00");
//        exam.setClassOwnerEmail("prova1owner@gmail.com");
//        userClass.setClassName(null);
//
//        testUser = UserExamControl.getInstance(activity.getApplicationContext());
//
//        String isValid;
//
//        isValid = testUser.validateAddsFirstGrade(userClass, exam);
//
//    }

}
