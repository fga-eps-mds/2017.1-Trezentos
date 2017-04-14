package fga.mds.gpp.trezentos.Controller;


import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fga.mds.gpp.trezentos.BuildConfig;
import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.View.MainActivity;

import static com.facebook.FacebookSdk.getApplicationContext;
import static junit.framework.Assert.assertFalse;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class UserClassControlUnitTest {

    public UserClassControl testUser;

    private MainActivity mainActivity;

    @Before
    public void setUp(){

        mainActivity = Robolectric.buildActivity(MainActivity.class).get();

    }

    @Test
    public void ShouldValidateNullName()throws UserException {

        boolean isValid = false;
        UserClass userClass = new UserClass(null, "UnB", 4.5f, "Senha1", 0.5f, 1);


        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateNullInstitutionName()throws UserException{

        boolean isValid = false;
        UserClass userClass = new UserClass("Calculo 1", null, 4.5f, "Senha1", 0.5f, 1);


        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateNullCutOff()throws UserException{

        boolean isValid = false;
        UserClass userClass = new UserClass("Calculo 1", "UnB", 0, "Senha1", 0.5f, 1);


        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidateNullClassPassword()throws UserException{

        boolean isValid = false;
        UserClass userClass = new UserClass("Calculo 1", "UnB", 4.5f, null, 0.5f, 1);


        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateAddition()throws UserException{

        boolean isValid = false;
        UserClass userClass = new UserClass("Calculo 1", "UnB", 4.5f, "Senha1", 0, 1);


        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }



    @Test
    public void ShouldValidateClassNameMaxLength()throws UserException{

        boolean isValid = false;
        UserClass userClass = new UserClass("Metodo De Desenvolvimento de Software", "UnB",
                                            4.5f, "Senha1", 0.5f, 1);


        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("O nome deve ter de 3 a 20 caracteres.")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidateClassNameMinLength()throws UserException{

        boolean isValid = false;
        UserClass userClass = new UserClass("DS", "UnB", 4.5f, "Senha1", 0.5f, 1);


        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("O nome deve ter de 3 a 20 caracteres.")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidatePasswordMinLength()throws UserException{

        boolean isValid = false;
        UserClass userClass = new UserClass("MDS", "UnB", 4.5f, "Senha", 0.5f, 1);

        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("A senha deve ter de 6 a 16 caracteres.")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidatePasswordMaxLength()throws UserException{

        boolean isValid = false;
        UserClass userClass = new UserClass("MDS", "UnB", 4.5f, "Senha", 0.5f, 1);

        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("A senha deve ter de 6 a 16 caracteres.")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidateInstituitionMinLength()throws UserException{

        boolean isValid = false;
        UserClass userClass = new UserClass("MDS","U",4.5f, "Senha", 0.5f, 1);

        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException){

            if(userException.getMessage().equals("A instituição deve ter de 2 a 30 caracteres.")){

                isValid = false;
            }

        }

        assertFalse(isValid);

    }

    @Test
    public void ShouldValidateInstituitionMaxLength()throws UserException{

        boolean isValid = false;

        String instituition = "Metodos de desenvolvimento de Software - Universidade de Brasilia";

        UserClass userClass = new UserClass("MDS",instituition,4.5f, "Senha", 0.5f, 1);

        try{

            testUser = UserClassControl.getInstance(mainActivity.getApplicationContext());
            testUser.validateInformation(userClass);
            isValid = true;

        } catch (UserException userException){

            if(userException.getMessage().equals("A instituição deve ter de 2 a 30 caracteres.")){

                isValid = false;
            }

        }

        assertFalse(isValid);

    }

}
