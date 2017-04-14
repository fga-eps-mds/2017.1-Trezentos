package fga.mds.gpp.trezentos.Controller;


import org.junit.Test;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserClass;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class UserClassControlUnitTest {

    public UserClassControl testUser;

    @Test
    public void ShouldValidateNullName(){

        boolean isValid = false;

        try{

            testUser = new UserClassControl();
            testUser.validateInformation(null, "UnB", "4.5", "Senha1", 1, "0.5", "5");
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

        try{

            testUser = new UserClassControl();
            testUser.validateInformation("Calculo 1", "UnB", null, "Senha1", 1, "0.5","5");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateZeroCutOff()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserClassControl();
            testUser.validateInformation("Calculo 1", "UnB", "0", "Senha1", 1, "0.5","5");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("A nota de corte nao pode ser zero.")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidateNullClassPassword()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserClassControl();
            testUser.validateInformation("Calculo 1", "UnB", "4.5", null, 1, "0.5", "5");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidateNullAddition()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserClassControl();
            testUser.validateInformation("Calculo 1", "UnB", "4.5", "Senha1", 1, null, "5");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateZeroAddition()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserClassControl();
            testUser.validateInformation("Calculo 1", "UnB", "4.5", "Senha1", 1, "0", "5");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("O acrescimo nao pode ser zero.")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }

    @Test
    public void ShouldValidateNullGroupSize()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserClassControl();
            testUser.validateInformation("Calculo 1", "UnB", "4.5", "Senha1", 1, "0.5", null);
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("Preencha todos os campos!")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateZeroGroupSize()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserClassControl();
            testUser.validateInformation("Calculo 1", "UnB", "4.5", "Senha1", 1, "0.5", "0");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("O tamanho do grupo nao pode ser zero.")) {
                isValid = false;
            }
        }

        assertFalse(isValid);
    }


    @Test
    public void ShouldValidateClassNameMaxLength()throws UserException{

        boolean isValid = false;

        try{

            testUser = new UserClassControl();
            testUser.validateInformation("Metodo De Desenvolvimento de Software",
                    "UnB", "4.5", "Senha1", 1, "0.5", "5");
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


        try{

            testUser = new UserClassControl();
            testUser.validateInformation("DS", "UnB", "4.5", "Senha1", 1, "0.5", "5");
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

        try{

            testUser = new UserClassControl();
            testUser.validateInformation("MDS", "UnB", "4.5", "Senha", 1, "0.5", "5");
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

            testUser = new UserClassControl();
            testUser.validateInformation("MDS", "UnB", "4.5", "Senha", 1, "0.5", "5");
            isValid = true;

        } catch (UserException userException) {

            if(userException.getMessage().equals("A senha deve ter de 6 a 16 caracteres.")) {

                isValid = false;
            }
        }

        assertFalse(isValid);
    }



}
