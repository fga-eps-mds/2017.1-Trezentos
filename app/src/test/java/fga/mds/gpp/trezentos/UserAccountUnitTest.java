package fga.mds.gpp.trezentos;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;


public class UserAccountUnitTest {

    @Test
    public void userAccountNameTest () throws UserException {
        UserAccount userAccount = new UserAccount();
        userAccount.setName("nome");
        assertEquals("nome",userAccount.getName());
    }

    @Test
    public void userAccountEmailTest() throws UserException {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail("email@email.com");
        assertEquals("email@email.com", userAccount.getEmail());
    }

    @Test
    public void userAccountPasswordTest() throws UserException{
        UserAccount userAccount = new UserAccount();
        userAccount.authenticatePassword("Password");
        assertEquals("Password", userAccount.getPassword());
    }

    @Test
    public void userAccountPasswordConfirmationTest() throws UserException{
        UserAccount userAccount = new UserAccount();
        userAccount.setPasswordConfirmation("PasswordConfirmation");
        assertEquals("PasswordConfirmation", userAccount.getPasswordConfirmation());
    }

    @Test
    public void userAccountSaltTest() throws UserException{
        UserAccount userAccount = new UserAccount();
        userAccount.setSalt("Salt");
        assertEquals("Salt", userAccount.getSalt());
    }
}
