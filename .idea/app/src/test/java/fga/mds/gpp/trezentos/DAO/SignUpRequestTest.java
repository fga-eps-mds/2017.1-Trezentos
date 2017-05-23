package fga.mds.gpp.trezentos.DAO;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.Util.PasswordUtil;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by carolina on 04/05/17.
 */


public class SignUpRequestTest {

    @Test
    public void shouldValidatedData() throws JSONException, UserException {
        UserAccount user = new UserAccount();

        // usar um e-mail random
        user.setEmail(PasswordUtil.nextSalt() + "@email.com");
        user.setName("Maria");
        user.authenticatePassword("12345678");
        user.setSalt("1578");

        SignUpRequest signUpRequest = new SignUpRequest(user, false);

        String serverResponse = null;

        try {
            serverResponse = signUpRequest.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        DAOHelperTest daoHelper = new DAOHelperTest();
        JSONObject resposta = daoHelper.getObjectFromServerResponse(serverResponse);
        JSONObject userDAO = daoHelper.getObjectFromServerResponse(resposta.getJSONObject("user").toString());


        assertEquals(userDAO.getString("name"), resposta.getJSONObject("user").getString("name"));
        assertEquals(userDAO.getString("email"), resposta.getJSONObject("user").getString("email"));
        assertEquals(userDAO.getString("password"), resposta.getJSONObject("user").getString("password"));
        assertEquals(userDAO.getString("salt"), resposta.getJSONObject("user").getString("salt"));
    }
}
