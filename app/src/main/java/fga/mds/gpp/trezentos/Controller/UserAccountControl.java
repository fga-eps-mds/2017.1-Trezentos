package fga.mds.gpp.trezentos.Controller;

import android.content.Context;

import java.util.List;

import fga.mds.gpp.trezentos.Model.UserAccount;

public class UserAccountControl {
    private static UserAccountControl instance;
    private Context context;

    public static UserAccountControl getInstance(Context context) {
        if (instance == null) {
            instance = new UserAccountControl();

        }

        return instance;
    }

    public void insert(UserAccount userAccount){

    }

    public void update(UserAccount userAccount){

    }

    public List<UserAccount> findAll(){
        return null;
    }

    public boolean loginValidate(String user, String password){
        UserAccount userAccount = new UserAccount();
        if(userAccount == null || userAccount.getUser() == null || userAccount.getPassword() == null){
            return false;
        }

        return true;
    }
}