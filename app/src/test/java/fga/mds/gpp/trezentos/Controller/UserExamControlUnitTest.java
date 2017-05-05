package fga.mds.gpp.trezentos.Controller;


import android.content.Context;
import android.support.v4.media.MediaMetadataCompat;

import org.junit.Test;

import fga.mds.gpp.trezentos.Exception.UserException;

import static junit.framework.Assert.assertTrue;

public class UserExamControlUnitTest {

    private UserExamControl testUser;
    private Context context;

    @Test
    public void ShouldValidateNullExamName() throws UserException{

        testUser = new UserExamControl(context);

        String isValid;

        isValid = testUser.validateInformation(null, "Usuario", "exemplo@dominio.com");

        assertTrue(isValid.equals("@string/msg_null_name_exam_error_message"));

    }

    @Test
    public void ShouldValidateNullUserClassName() throws UserException{

        testUser = new UserExamControl(context);

        String isValid;

        isValid = testUser.validateInformation("P1", null, "exemplo@dominio.com");

        assertTrue(isValid.equals("@string/msg_null_name_userClass_error_message"));

    }

    @Test
    public void ShouldValidateNullClassOwnerEmail() throws UserException{

        testUser = new UserExamControl(context);

        String isValid;

        isValid = testUser.validateInformation("P1", "Usuario", null);

        assertTrue(isValid.equals("@string/msg_null_classOwnerEmail_error_message"));

    }

    @Test
    public void ShouldValidateExamNameMaxLength() throws UserException{

        testUser = new UserExamControl(context);

        String isValid;

        isValid = testUser.validateInformation("Prova 1 do professor Fragelli da UnB",
                                                "Usuario", "exemplo@dominio.com");

        assertTrue(isValid.equals("@string/msg_len_name_exam_error"));

    }

    @Test
    public void ShouldValidateExamNameMinLength() throws UserException{

        testUser = new UserExamControl(context);

        String isValid;

        isValid = testUser.validateInformation("P", "Usuario", "exemplo@dominio.com");

        assertTrue(isValid.equals("@string/msg_len_name_exam_error"));

    }

}
