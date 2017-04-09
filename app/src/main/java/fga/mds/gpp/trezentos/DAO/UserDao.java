package fga.mds.gpp.trezentos.DAO;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fga.mds.gpp.trezentos.Model.UserAccount;

/**
 * Created by arthur on 28/03/17.
 */

public class UserDao {

    Context context;
    UserAccount userAccount;

    //Contructor
    public UserDao(){


    }

    public UserDao(Context context){
            this.context = context;

    }

    //Methods
    public void update(UserAccount userAccount){
        //This method will update the register in DB


    }

    public void authenticate(UserAccount userAccount){
        //Http requisition
        //parse to JSON the userAccount

    }

    public void insert(UserAccount userAccount){
        //This method  will insert the register in BD


    }

    public UserAccount findById(Integer idUserAccount){
        //Get the cursor in database
        //sends this cursor to
        UserAccount userAccount = new UserAccount();

        return userAccount;
    }

    public UserAccount findById(String usuario , String senha) {

        UserAccount userAccount = new UserAccount();

        return userAccount;
    }




}
