package fga.mds.gpp.trezentos.DAO;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.Util.PasswordUtil;

import static junit.framework.Assert.assertEquals;

/**
 * Created by carolina on 05/05/17.
 */

public class SignInRequestTest {
    @Test
    public void shouldValidatedData() throws JSONException, UserException {
        UserAccount user = new UserAccount();

        user.setName("carol22k");
        // usar um e-mail random
        user.setEmail("carol@carol.com");
        user.authenticatePassword("123456");

        SignInRequest signInRequest = new SignInRequest(user);

        String serverResponse = "404";

        try {
            serverResponse = signInRequest.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        DAOHelperTest daoHelper = new DAOHelperTest();
        JSONObject resposta = daoHelper.getObjectFromServerResponse(serverResponse);

        assertEquals(user.getName(), resposta.getString("name"));
        assertEquals(user.getPassword(), resposta.getString("password"));
    }
}
