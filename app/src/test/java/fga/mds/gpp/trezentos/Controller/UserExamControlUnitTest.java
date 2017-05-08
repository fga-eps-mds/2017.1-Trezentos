//package fga.mds.gpp.trezentos.Controller;
//
//
//import android.content.Context;
//import android.support.v4.media.MediaMetadataCompat;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//
//import fga.mds.gpp.trezentos.BuildConfig;
//import fga.mds.gpp.trezentos.Exception.UserException;
//import fga.mds.gpp.trezentos.View.ClassActivity;
//
//import static junit.framework.Assert.assertTrue;
//
//@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)
//public class UserExamControlUnitTest {
//
//    private UserExamControl testUser;
//    private ClassActivity activity;
//
//    @Before
//    public void setUp() {
//        activity = Robolectric.buildActivity(ClassActivity.class).get();
//    }
//
//    @Test
//    public void ShouldValidateNullExamName() throws UserException{
//
//        testUser = UserExamControl.getInstance(activity.getApplicationContext());
//
//        String isValid;
//
//        isValid = testUser.validateInformation(null, "Usuario", "exemplo@dominio.com");
//
//        assertTrue(isValid.equals("@string/msg_null_name_exam_error_message"));
//
//    }
//
//    @Test
//    public void ShouldValidateNullUserClassName() throws UserException{
//
//        testUser = UserExamControl.getInstance(activity.getApplicationContext());
//
//        String isValid;
//
//        isValid = testUser.validateInformation("P1", null, "exemplo@dominio.com");
//
//        assertTrue(isValid.equals("@string/msg_null_name_userClass_error_message"));
//
//    }
//
//    @Test
//    public void ShouldValidateNullClassOwnerEmail() throws UserException{
//
//        testUser = UserExamControl.getInstance(activity.getApplicationContext());
//
//        String isValid;
//
//        isValid = testUser.validateInformation("P1", "Usuario", null);
//
//        assertTrue(isValid.equals("@string/msg_null_classOwnerEmail_error_message"));
//
//    }
//
//    @Test
//    public void ShouldValidateExamNameMaxLength() throws UserException{
//
//        testUser = UserExamControl.getInstance(activity.getApplicationContext());
//
//        String isValid;
//
//        isValid = testUser.validateInformation("Prova 1 do professor Fragelli da UnB",
//                                                "Usuario", "exemplo@dominio.com");
//
//        assertTrue(isValid.equals("@string/msg_len_name_exam_error"));
//
//    }
//
//    @Test
//    public void ShouldValidateExamNameMinLength() throws UserException{
//
//        testUser = UserExamControl.getInstance(activity.getApplicationContext());
//
//        String isValid;
//
//        isValid = testUser.validateInformation("P", "Usuario", "exemplo@dominio.com");
//
//        assertTrue(isValid.equals("@string/msg_len_name_exam_error"));
//
//    }
//
//}
