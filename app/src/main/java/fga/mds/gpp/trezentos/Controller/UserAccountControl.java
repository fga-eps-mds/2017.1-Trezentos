package fga.mds.gpp.trezentos.Controller;

import android.content.Context;

import java.nio.charset.CharacterCodingException;
import java.util.List;

import fga.mds.gpp.trezentos.DAO.UserDao;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Exception.UserException;

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
        if(userAccount == null || userAccount.getUser() == null || userAccount.getPassword() == null){
            return false;
        }

        return true;
    }

    public void validateInformation(String name, String email, String password,
                                    String passwordConfirmation) throws UserException {

        Integer MIN_PASSWORD_LENGTH = 6;
        Integer MIN_NAME_LENGTH = 3;
        Integer MAX_PASSWORD_LENGTH = 16;
        Integer MAX_NAME_LENGTH = 50;

        if(name == null || email == null || password == null || passwordConfirmation == null){

            throw new UserException("Preencha todos os campos!");
        }

        if(name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH){

            throw new UserException("O nome deve ter de 3 a 50 caracteres.");
        }

        if(password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH){

            throw new UserException("A senha deve ter de 6 a 16 caracteres.");
        }

        if(password != passwordConfirmation){
            throw new UserException("Senhas não Coincidem. Tente novamente.");
        }

        // Confirm UpperCase in password

        boolean upperCaseConfirmation = false;
        char letter;

        for(int i = 0; i < password.length(); i++){

            letter = password.charAt(i);

            if (letter == Character.toUpperCase(letter)){
                upperCaseConfirmation = true;
            }

        }

        if(upperCaseConfirmation == false){

            throw new UserException("A senha deve ter ao menos um caracter maiusculo!");
        }

    }

    public void insertModelUser(Integer idUser, String user, String password){
        userAccount = new UserAccount(idUser, user, password);
        UserAccount.authenticateLogin(userAccount);
    }

    public void insertModelUserRegister(Integer idUser, String user, String password){
        userAccount = new UserAccount(idUser, user, password);
        UserAccount.authenticateLogin(userAccount);
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

    public String getUserAccountName(){
        return userAccount.getName();

    }



}