package fga.mds.gpp.trezentos.DAO;

import org.json.JSONException;
import org.json.JSONObject;


public class DAOHelperTest {
    public static final JSONObject getObjectFromServerResponse(String serverResponse) {
        JSONObject object = null;

        try {
            object = new JSONObject(serverResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

}
