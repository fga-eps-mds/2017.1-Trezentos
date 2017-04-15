package fga.mds.gpp.trezentos.Model;

import static java.lang.Double.valueOf;
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
        userClass.setInstitution("nome");
        assertEquals("nome", userClass.getInstitution());
    }

    @Test
    public void UserClass_CutOff() {
        UserClass userClass = new UserClass();
        Float floatCutOff = new Float(10.0);
        try {
            userClass.setCutOff(floatCutOff);
        } catch (UserException e) {
            e.printStackTrace();
        }
        floatCutOff.equals(userClass.getCutOff());
    }

    @Test
    public void UserClass_Password(){
        UserClass userClass = new UserClass();
        try {
            userClass.setPassword("nome");
        } catch (UserException e) {
            e.printStackTrace();
        }
        assertEquals("nome", userClass.getPassword());
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
        Float floatAdd = new Float(10.0);
        try {
            userClass.setAddition(floatAdd);
        } catch (UserException e) {
            e.printStackTrace();
        }
        floatAdd.equals(userClass.getAddition());
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


