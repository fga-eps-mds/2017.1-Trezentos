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



}
