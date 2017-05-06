package fga.mds.gpp.trezentos.Model;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;


public class UserAccountTest {

    @Test
    public void shouldValidateName() throws UserException {
        UserAccount userAccount = new UserAccount();
        userAccount.setName("nome");
        assertEquals("nome", userAccount.getName());
    }

    @Test
    public void shouldValidateEmail() throws UserException {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail("Joao123@email.com");
        assertEquals("Joao123@email.com", userAccount.getEmail());
    }

    @Test
    public void shouldValidatePasswordConfirmation() {
        UserAccount userAccount = new UserAccount();
        userAccount.setPasswordConfirmation("PasswordConfirmation");
        assertEquals("PasswordConfirmation", userAccount.getPasswordConfirmation());
    }

    @Test
    public void shouldValidateSalt() {
        UserAccount userAccount = new UserAccount();
        userAccount.setSalt("Salt");
        assertEquals("Salt", userAccount.getSalt());
    }

    @Test
    public void shouldValidateAuthenticatePassword() throws UserException {
        UserAccount userAccount = new UserAccount();
        userAccount.authenticatePassword("Password");
        assertEquals("Password", userAccount.getPassword());
    }
}