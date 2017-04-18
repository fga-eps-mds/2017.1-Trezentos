package fga.mds.gpp.trezentos;

import fga.mds.gpp.trezentos.Model.UserClass;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;


public class UserClassUnitTest {

    @Test
    public void UserClass_AdditionTest() {
        UserClass userClass = new UserClass();
        Float floatAddition = new Float(10.0);
        userClass.setAddition(floatAddition);
        floatAddition.equals(userClass.getCutOff());
    }

    @Test
    public void UserClass_ClassNameTest() {
        UserClass userClass = new UserClass();
        userClass.setClassName("name");
        assertEquals("name", userClass.getClassName());

    }

    @Test
    public void UserClass_IdUserAccountTest(){
        UserClass userClass = new UserClass();
        Integer integer = new Integer(10);
        userClass.setIdUserAccount(integer);
        assertEquals(integer,userClass.getIdUserAccount());
    }

    @Test
    public void UserClass_PasswordTest(){
        UserClass userClass = new UserClass();
        userClass.setPassword("password");
        assertEquals("password",userClass.getPassword());
    }

    @Test
    public void UserClass_InstitutionTest(){
        UserClass userClass = new UserClass();
        userClass.setInstitution("Institution");
        assertEquals("Institution",userClass.getInstitution());
    }

    @Test
    public void UserClass_CutOffTest(){
        UserClass userClass = new UserClass();
        Float floatCutOff = new Float(10.0);
        userClass.setCutOff(floatCutOff);
        floatCutOff.equals(userClass.getCutOff());
    }



}
