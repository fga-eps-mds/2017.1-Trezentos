package fga.mds.gpp.trezentos;

import fga.mds.gpp.trezentos.Model.UserAccount;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;


public class UserAccountUnitTest {

    @Test
    public void UserAccount_NameTest (){
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setName("name");
            assertEquals("name",userAccount.getName());
        }
        catch (Exception c){

        }
    }

    @Test
    public void UserAccount_EmailTest() {
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setEmail("email");
            assertEquals("email", userAccount.getEmail());
        }
        catch (Exception c) {

        }
    }

    @Test
    public void UserAccount_PasswordTest(){
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setPassword("Password");
            assertEquals("Password", userAccount.getPassword());
        }
        catch (Exception c){

        }
    }

    @Test
    public void UserAccount_PasswordConfirmationTest(){
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setPasswordConfirmation("PasswordConfirmation");
            assertEquals("PasswordConfirmation", userAccount.getPasswordConfirmation());
        }
        catch (Exception c){

        }
    }

    @Test
    public void UserAccount_SaltTest(){
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setSalt("Salt");
            assertEquals("Salt", userAccount.getSalt());
        }
        catch (Exception c){

        }
    }
}
