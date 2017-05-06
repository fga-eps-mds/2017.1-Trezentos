
package fga.mds.gpp.trezentos.Controller;


import android.content.Context;
import org.junit.Test;
import fga.mds.gpp.trezentos.Exception.UserException;
import static junit.framework.Assert.assertTrue;


public class UserClassControlUnitTest {

    public UserClassControl testUser;
    private Context context;


    @Test
    public void ShouldValidateNullName() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation(null, "UnB", "4.5", "Senha1", "0.5", "1");

        assertTrue(isValid.equals("Preencha todos os campos!"));
    }

    @Test
    public void ShouldValidateNullInstitution() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("Calculo 1", null, "4.5", "Senha1", "0.5", "1");


        assertTrue(isValid.equals("Sucesso"));

    }


    @Test
    public void ShouldValidateZeroCutOff() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("Calculo 1", "UnB", "0", "Senha1", "0.5", "1");


        assertTrue(isValid.equals("A nota de corte nao pode ser zero."));


    }


    @Test
    public void ShouldValidateNullClassPassword() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("Calculo 1", "UnB", "4.5", null, "0.5", "1");


        assertTrue(isValid.equals("Preencha todos os campos!"));
    }

    @Test
    public void ShouldValidateZeroAddition() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("Calculo 1", "UnB", "4.5", "Senha1", "0", "5");


        assertTrue(isValid.equals("O acrescimo nao pode ser zero."));


    }


    @Test
    public void ShouldValidateZeroGroupSize() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("Calculo 1", "UnB", "4.5", "Senha1", "0.5", "0");


        assertTrue(isValid.equals("O tamanho do grupo nao pode ser zero."));

    }


    @Test
    public void ShouldValidateClassNameMaxLength() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("Metodo De Desenvolvimento de Software"
                , "UnB", "4.5", "Senha1", "0.5", "5");

        assertTrue(isValid.equals("O nome da sala deve ter de 3 a 20 caracteres."));
    }

    @Test
    public void ShouldValidateClassNameMinLength() throws UserException {


        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("DS", "UnB", "4.5", "Senha1", "0.5", "5");

        assertTrue(isValid.equals("O nome da sala deve ter de 3 a 20 caracteres."));

    }

    @Test
    public void ShouldValidatePasswordMinLength() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("MDS", "UnB", "4.5", "Senha", "0.5", "5");

        assertTrue(isValid.equals("A senha deve ter entre 6 e 16 caracteres"));

    }

    @Test
    public void ShouldValidatePasswordMaxLength() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("MDS", "UnB", "4.5", "Esqueciminhasenha", "0.5", "5");

        assertTrue(isValid.equals("A senha deve ter entre 6 e 16 caracteres"));

    }

    @Test
    public void ShouldValidateInstituitionMinLength() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("MDS", "IF", "4.5", "Senha1", "0.5", "5");

        assertTrue(isValid.equals("O nome da instituicao deve ter de 3 a 20 caracteres."));

    }

    @Test
    public void ShouldValidateInstituitionMaxLength() throws UserException {

        testUser = new UserClassControl(context);
        String isValid;

        isValid = testUser.validateInformation("MDS", "Universidade de Brasilia",
                                                    "4.5", "Senha1", "0.5", "5");

        assertTrue(isValid.equals("O nome da instituicao deve ter de 3 a 20 caracteres."));

    }
}
