package fga.mds.gpp.trezentos.Controller;

import android.content.Context;

import java.util.List;

import fga.mds.gpp.trezentos.DAO.UserDao;
import fga.mds.gpp.trezentos.Model.UserAccount;

public class UserAccountControl {

    private static UserAccountControl instance;
    private static UserDao userDao;
    private static UserAccount userAccount;

    private Context context;

    public UserAccountControl(){

    }

    public UserAccountControl(Context context){
        this.context = context;
    }


    public static UserAccountControl getInstance(Context context) {
        if (instance == null) {
            instance = new UserAccountControl();
            userDao = new UserDao();
        }

        return instance;
    }

    public void insert(UserAccount userAccount){
        userDao.insert(userAccount);

    }

    public void update(UserAccount userAccount){
        userDao.update(userAccount);
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

    public void insertModelUser(Integer idUser, String user, String password){

        userAccount = new UserAccount(idUser, user, password);
    }

    public void updateModelUser(Integer idUser, String user, String password){

        userAccount.setIdUserAccount(idUser);
        userAccount.setUser(user);
        userAccount.setPassword(password);
    }

    public Integer getUserAccountId(){
        return userAccount.getIdUserAccount();

    }
    public String getUserAccountEmail(){
        return userAccount.getUser();

    }
    public String getUserAccountPassword(){
        return userAccount.getPassword();

    }



}