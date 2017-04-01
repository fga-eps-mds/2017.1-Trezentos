package fga.mds.gpp.trezentos.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Integer.parseInt;



public class JSON {

    UserAccount userAccount;
    String in;
    JSONObject reader = new JSONObject(in);

    public JSON() throws JSONException {}

    public JSONObject parsingJSON(UserAccount userAccount) throws JSONException {

        JSONObject json = new JSONObject();

        json.put("idUser", userAccount.getIdUserAccount());
        json.put("email", userAccount.getEmail());
        json.put("email", userAccount.getEmail());
        json.put("password", userAccount.getPassword());

        return json;
    }

    public UserAccount readingJSON() throws JSONException, UserException {

        JSONObject sys  = reader.getJSONObject("sys");

        userAccount.setIdUserAccount(parseInt(sys.getString("idUser")));
        userAccount.setName(sys.getString("name"));
        userAccount.setEmail(sys.getString("email"));
        userAccount.setPassword(sys.getString("password"));

        return userAccount;
    }
}