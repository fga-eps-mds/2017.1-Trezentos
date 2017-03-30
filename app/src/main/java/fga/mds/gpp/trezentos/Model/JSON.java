package fga.mds.gpp.trezentos.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Integer.parseInt;

/**
 * Created by arthur on 30/03/17.
 */

public class JSON {

    UserAccount userAccount;
    String in;
    JSONObject reader = new JSONObject(in);

    public JSON() throws JSONException {


    }

    public JSONObject parsingJSON(UserAccount userAccount) throws JSONException {


        JSONObject json = new JSONObject();
        json.put("idUser", userAccount.getIdUserAccount());
        json.put("email", userAccount.getUser());
        json.put("password", userAccount.getPassword());

        return json;
    }

    public UserAccount readingJSON() throws JSONException {



        JSONObject sys  = reader.getJSONObject("sys");
        userAccount.setIdUserAccount(parseInt(sys.getString("idUser")));

        JSONObject email  = reader.getJSONObject("email");
        userAccount.setUser(email.getString("email"));

        JSONObject pass  = reader.getJSONObject("pass");
        userAccount.setUser(pass.getString("pass"));


        return userAccount;
    }




}
