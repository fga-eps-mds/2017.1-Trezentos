package fga.mds.gpp.trezentos.Controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fga.mds.gpp.trezentos.BuildConfig;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;
import fga.mds.gpp.trezentos.View.ClassActivity;
import fga.mds.gpp.trezentos.View.CreateExamActivity;

import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UserExamControlUnitTest {

    private UserExamControl testUser;
    private CreateExamActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(CreateExamActivity.class).get();
    }

    @Test
    public void ShouldValidateNullExamName() throws UserException{

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation(null, "Usuario", "exemplo@dominio.com");

        assertTrue(isValid.equals("O nome não pode ser vazio"));

    }

    @Test
    public void ShouldValidateNullUserClassName() throws UserException{

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation("P1", null, "exemplo@dominio.com");

        assertTrue(isValid.equals("O nome não pode ser vazio"));

    }

    @Test
    public void ShouldValidateNullClassOwnerEmail() throws UserException{

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation("P1", "Usuario", null);

        assertTrue(isValid.equals("O email não pode estar vazio"));

    }

    @Test
    public void ShouldValidateExamNameMaxLength() throws UserException{

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation(
                "Prova 1 do professor Fragelli da UnB",
                "Usuario",
                "exemplo@dominio.com");

        assertTrue(isValid.equals("O nome da prova deve ter entre 2 e 15 caracteres."));
    }

    @Test
    public void ShouldValidateExamNameMinLength() throws UserException{

        testUser = UserExamControl.getInstance(activity.getApplicationContext());

        String isValid;

        isValid = testUser.validateInformation("P", "Usuario", "exemplo@dominio.com");

        assertTrue(isValid.equals("O nome da prova deve ter entre 2 e 15 caracteres."));

    }

}