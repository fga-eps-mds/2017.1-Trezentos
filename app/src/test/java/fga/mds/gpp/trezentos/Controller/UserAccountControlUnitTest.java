package fga.mds.gpp.trezentos.Controller;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import fga.mds.gpp.trezentos.Controller.UserAccountControl;
import fga.mds.gpp.trezentos.Exception.UserException;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;


public class UserAccountControlUnitTest {

    UserAccountControl testUser;

    @Test
    public void ShouldValidateNullName()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserAccountControl();
            testUser.validateSignUp(null,"aluno@email.com","Senha1", "Senha1");
            isValid = true;

        } catch (UserException userException) {

            //if(userException.getMessage().equals("Preencha todos os campos!")) {

                isValid = false;
           // }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateNullEmail()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserAccountControl();
            testUser.validateSignUp("Carla",null,"Senha1", "Senha1");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateNullPassword()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserAccountControl();
            testUser.validateSignUp("Carla","aluno@email.com",null, "Senha1");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidateNullPasswordConfirmation()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserAccountControl();
            testUser.validateSignUp("Carla", "aluno@email.com", "Senha1", null);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }



    @Test
    public void ShouldValidateNameMinLength()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserAccountControl();
            testUser.validateSignUp("Ei","aluno@email.com","Senha1", "Senha1");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("O nome deve ter de 3 a 50 caracteres.")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }



    @Test
    public void ShouldValidateNameMaxLength()throws UserException{

        boolean isValid = false;
        testUser = new UserAccountControl();

        try{

            testUser.validateSignUp("O trezentos é metodo de aprendizagem ativa e colaborativa"
                    ,"aluno@email.com","Senha1", "Senha1");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("O nome deve ter de 3 a 50 caracteres.")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidatePasswordMinLength()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserAccountControl();
            testUser.validateSignUp("Carla","aluno@email.com","Senha", "Senha");
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

        try{

            testUser = new UserAccountControl();
            testUser.validateSignUp("Carla"
                    ,"aluno@email.com","Vintecaracteresusados", "Vintecaracteresusados");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("A senha deve ter de 6 a 16 caracteres.")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidatePasswordMatches()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserAccountControl();
            testUser.validateSignUp("Carla"
                    ,"aluno@email.com","Senha1", "Senha");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Senhas não Coincidem. Tente novamente.")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateUpperCasePassword()throws UserException{

        boolean isValid = false;

        try{
            testUser = new UserAccountControl();
            testUser.validateSignUp("Carla"
                    ,"aluno@email.com","senha1", "senha");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("A senha deve ter ao menos um caracter maiusculo!")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }


}


