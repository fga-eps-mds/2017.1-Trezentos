package fga.mds.gpp.trezentos.Model;

import android.util.Log;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        userClass.setPassword("password", "password");
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

    @Test
    public void shouldValidateStudentsRegistreds(){
        UserClass userClass = new UserClass();
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("teststudent");
        tempList.add("anotherteststudent");

        userClass.setStudents(tempList);

        assertEquals(tempList, userClass.getStudents());
    }

//    public void shouldProperlySetOwnerEmail() {
//        UserClass userClass = new UserClass();
//        userClass.setOwnerEmail("teste@teste.com");
//        assertEquals("teste@teste.com", userClass.getOwnerEmail());
//    }

    @Test
    public void shouldValidateConstructor() throws UserException {
        UserClass userClass = new UserClass("Name", "Institution", 10.0f, "password", "password", 10.0f, 5);
        assertEquals("Name", userClass.getClassName());
        assertEquals("Institution", userClass.getInstitution());
        assertEquals(10.0f, userClass.getCutOff(), 0.1f);
        assertEquals("password", userClass.getPassword());
        assertEquals(10.0f, userClass.getAddition(), 0.1f);
        assertEquals(5, userClass.getSizeGroups());
    }

}


