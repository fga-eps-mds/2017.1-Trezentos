package fga.mds.gpp.trezentos.Controller;

import org.json.JSONObject;
import fga.mds.gpp.trezentos.Model.UserAccount;
import fga.mds.gpp.trezentos.Exception.UserException;

public class UserAccountControl {

    private static UserAccountControl instance;

    public UserAccount userAccount;

    public UserAccountControl() {

    }

    public void validateSignUp(String name, String email, String password, String passwordConfirmation) throws UserException {
            userAccount = new UserAccount(name, email, password, passwordConfirmation);
    }

    public void insertModelUserFacebook(JSONObject object) {

    }

    public void insertModelUser(String email, String password) throws UserException {
        userAccount = new UserAccount();

        //Verify email
        if (email != null && !email.isEmpty()) {
            Integer MAX_NAME_LENGTH = 30;

            if (email.length() <= MAX_NAME_LENGTH) {
                userAccount.setEmail(email);
            } else {
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
            } else {
                throw new UserException("Digite uma senha de até 20 caracteres");
            }
        } else {
            throw new UserException("A senha não pode estar vazia");
        }

        UserAccount.authenticateLogin(userAccount);
    }


    public void updateModelUser(String name, String email, String password) throws UserException {

        userAccount.setName(name);
        userAccount.setEmail(email);
        userAccount.setPassword(password);
    }

    public String getUserAccountEmail() {
        return userAccount.getEmail();

    }

    public String getUserAccountPassword() {
        return userAccount.getPassword();

    }
        public void insert(UserAccount userAccount) {

    }

    public void authenticateLogin() {
        UserAccount.authenticateLogin(userAccount);

    }

    public boolean loginValidate(String user, String password) {
        UserAccount userAccount = new UserAccount();
        if (userAccount == null || userAccount.getEmail() == null || userAccount.getPassword() == null) {
            return false;
        }

        return true;
    }


}