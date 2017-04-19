package fga.mds.gpp.trezentos.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import fga.mds.gpp.trezentos.Exception.UserException;

public class UserClassTest {

    @Test
    public void UserClass_ClassName() {
        UserClass userClass = new UserClass();
        try {
            userClass.setClassName("nome");
        } catch (UserException e) {
            e.printStackTrace();
        }
        assertEquals("nome", userClass.getClassName());

    }

    @Test
    public void UserClass_Institution() {
        UserClass userClass = new UserClass();
        try {
            userClass.setInstitution("nome");
        } catch (UserException e) {
            e.printStackTrace();
        }
        assertEquals("nome", userClass.getInstitution());
    }

    @Test
    public void UserClass_CutOff() {
        UserClass userClass = new UserClass();
        Float floatCutOff = 10.0f;
        try {
            userClass.setCutOff(floatCutOff);
        } catch (UserException e) {
            e.printStackTrace();
        }
        assertTrue(floatCutOff.equals(userClass.getCutOff()));
    }

    @Test
    public void UserClass_Password(){
        boolean isValid = false;

        UserClass userClass = new UserClass();

        try {
            isValid = true;
            userClass.setPassword("nome");
        } catch (UserException e) {
            isValid = false;
            e.printStackTrace();
        }

        assertFalse(isValid);
    }

    @Test
    public void UserClass_passwordTest (){
        UserClass userClass = new UserClass();
        try {
            userClass.setPassword("password");
        } catch (UserException e) {
            e.printStackTrace();
        }
        assertEquals("password",userClass.getPassword());
    }

    @Test
    public void UserClass_Addition() {
        UserClass userClass = new UserClass();
        Float floatAdd = 10.0f;
        try {
            userClass.setAddition(floatAdd);
        } catch (UserException e) {
            e.printStackTrace();
        }
        assertTrue(floatAdd.equals(userClass.getAddition()));
    }

    @Test
    public void UserClass_SizeGroups() {
        UserClass userClass = new UserClass();
        try {
            userClass.setSizeGroups(10);
        } catch (UserException e) {
            e.printStackTrace();
        }
        assertEquals(10, userClass.getSizeGroups());
    }

}


