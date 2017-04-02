package fga.mds.gpp.trezentos.Controller;

import android.content.Context;

import org.json.JSONObject;

import java.util.List;

import fga.mds.gpp.trezentos.DAO.UserDao;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Model.UserException;

public class UserAccountControl {

    private static UserAccountControl instance;

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

        }

        return instance;
    }

    public void insert(UserAccount userAccount){


    }

    public void authenticateLogin(){
        UserAccount.authenticateLogin(userAccount);

    }


    public boolean loginValidate(String user, String password){
        UserAccount userAccount = new UserAccount();
        if(userAccount == null || userAccount.getEmail() == null || userAccount.getPassword() == null){
            return false;
        }

        return true;
    }

    public void insertModelUserFacebook(JSONObject object){
        
    }

    public void insertModelUser(Integer idUser, String email, String password) throws UserException {
        userAccount = new UserAccount();

        //Id
        userAccount.setIdUserAccount(idUser);

        //Verify email
        if (email != null && !email.isEmpty()) {
            Integer MAX_NAME_LENGTH = 30;

            if (email.length() <= MAX_NAME_LENGTH) {
                userAccount.setEmail(email);
            } else  {
                throw new UserException("Digite um email de até 30 caracteres");
            }
        } else {
            throw new UserException("O email não pode estar vazio");
        }

        //Verify the password
        if (password != null && !password.isEmpty()) {
            Integer MAX_PASS_LENGTH = 20;

            if (password.length() <= MAX_PASS_LENGTH) {
                userAccount.setPassword(password);
            } else  {
                throw new UserException("Digite uma senha de até 20 caracteres");
            }
        } else {
            throw new UserException("A senha não pode estar vazia");
        }

        UserAccount.authenticateLogin(userAccount);


    }

    public void updateModelUser(Integer idUser,String name, String email, String password) throws UserException {

        userAccount.setIdUserAccount(idUser);
        userAccount.setName(name);
        userAccount.setEmail(email);
        userAccount.setPassword(password);
    }

    public Integer getUserAccountId(){
        return userAccount.getIdUserAccount();

    }
    public String getUserAccountEmail(){
        return userAccount.getEmail();

    }
    public String getUserAccountPassword(){
        return userAccount.getPassword();

    }



}