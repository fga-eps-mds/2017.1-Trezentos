package fga.mds.gpp.trezentos.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import fga.mds.gpp.trezentos.Exception.UserException;

public class UserClassTest {

    @Test
    public void shouldValidateClassName() throws UserException {
        UserClass userClass = new UserClass();
        userClass.setClassName("nome");
        assertEquals("nome", userClass.getClassName());

    }

    @Test
    public void shouldValidateInstitution() throws UserException {
        UserClass userClass = new UserClass();
        userClass.setInstitution("nome");
        assertEquals("nome", userClass.getInstitution());
    }

    @Test
    public void shouldValidateCutOff() throws UserException {
        UserClass userClass = new UserClass();
        Float floatCutOff = 10.0f;
        userClass.setCutOff(floatCutOff);
        assertTrue(floatCutOff.equals(userClass.getCutOff()));
    }

    @Test
    public void shouldValidatePassword () throws UserException {
        UserClass userClass = new UserClass();
        userClass.setPassword("password");
        assertEquals("password",userClass.getPassword());
    }

    @Test
    public void shouldValidateAddition() throws UserException {
        UserClass userClass = new UserClass();
        Float floatAdd = 10.0f;
        userClass.setAddition(floatAdd);
        assertTrue(floatAdd.equals(userClass.getAddition()));
    }

    @Test
    public void shouldValidateSizeGroups() throws UserException {
        UserClass userClass = new UserClass();
        userClass.setSizeGroups(10);
        assertEquals(10, userClass.getSizeGroups());
    }

}


