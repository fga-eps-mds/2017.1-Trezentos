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
        userAccount.setEmail(email);

        //Verify the password
        userAccount.setPasswordConfirmation(password);
        userAccount.setPassword(password);

        UserAccount.authenticateLogin(userAccount);
    }


    public void updateModelUser(String name, String email, String password) throws UserException {

        userAccount.setName(name);
        userAccount.setEmail(email);
        userAccount.setPasswordConfirmation(password);
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