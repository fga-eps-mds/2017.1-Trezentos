package fga.mds.gpp.trezentos.ModelTest;

import fga.mds.gpp.trezentos.Model.UserAccount;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;


public class UserAccountTest {

    @Test
    public void UserAccount_idUserAccountTest (){
        UserAccount userAccount = new UserAccount();
        Integer integer = new Integer(10);
        userAccount.setIdUserAccount(integer);
        assertEquals(integer,userAccount.getIdUserAccount());
    }

    @Test
    public void UserAccount_nameTest (){
        UserAccount userAccount = new UserAccount();
        userAccount.setName("nome");
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

    @Test
    public void UserAccount_passwordTest (){
        UserAccount userAccount = new UserAccount();
        userAccount.setPassword("password");
        assertEquals("password",userAccount.getPassword());
    }

}
