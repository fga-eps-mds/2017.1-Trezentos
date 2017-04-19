package fga.mds.gpp.trezentos.Model;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.UserAccount;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;


public class UserAccountTest {

    @Test
    public void UserAccount_nameTest (){
        UserAccount userAccount = new UserAccount();
        try {
            userAccount.setName("nome");
        } catch (UserException e) {
            e.printStackTrace();
        }
        assertEquals("nome",userAccount.getName());
    }

    @Test
    public void TestForException (){
        try {
            UserAccount userAccount = new UserAccount();
            userAccount.setEmail("email");
            assertEquals("email", userAccount.getEmail());
        } catch (Exception c){

        }
    }

//    @Test
//    public void UserAccount_passwordTest () {
//        UserAccount userAccount = new UserAccount();
//        try {
//            userAccount.setPasswordConfirmation("password");
//            userAccount.setPassword("password");
//        } catch (UserException e) {
//            e.printStackTrace();
//        }
//        assertEquals("password", userAccount.getPassword());
//        assertEquals("password", userAccount.getPasswordConfirmation());
//    }

    @Test
    public void UserAccount_NameTest (){
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setName("name");
            assertEquals("name",userAccount.getName());
        } catch (Exception c){
        }
    }

    @Test
    public void UserAccount_EmailTest() {
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setEmail("email");
            assertEquals("email", userAccount.getEmail());
        } catch (Exception c) {
        }
    }

    @Test
    public void UserAccount_PasswordTest(){
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setPassword("Password");
            assertEquals("Password", userAccount.getPassword());
        } catch (Exception c){
        }
    }

    @Test
    public void UserAccount_PasswordConfirmationTest(){
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setPasswordConfirmation("PasswordConfirmation");
            assertEquals("PasswordConfirmation", userAccount.getPasswordConfirmation());
        } catch (Exception c){
        }
    }

    @Test
    public void UserAccount_SaltTest(){
        try{
            UserAccount userAccount = new UserAccount();
            userAccount.setSalt("Salt");
            assertEquals("Salt", userAccount.getSalt());
        } catch (Exception c){
        }
    }
}
