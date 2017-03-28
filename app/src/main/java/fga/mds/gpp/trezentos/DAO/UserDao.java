package fga.mds.gpp.trezentos.DAO;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fga.mds.gpp.trezentos.Model.UserAccount;

/**
 * Created by arthur on 28/03/17.
 */

public class UserDao {

    Context context;
    UserAccount userAccount;

    public UserDao(){


    }


    public UserDao(Context context){
            this.context = context;

    }


    public void update(UserAccount userAccount){
        //This method will update the register in DB


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

    public List<UserAccount> findAll() throws Exception {
        List<UserAccount> retorno = new ArrayList<UserAccount>();

        return retorno;
    }

    private UserAccount montaUsuario() {

        Integer idUserAccount = null;
        String user = null;
        String password = null;

        return new UserAccount(idUserAccount, user, password);

    }

}
