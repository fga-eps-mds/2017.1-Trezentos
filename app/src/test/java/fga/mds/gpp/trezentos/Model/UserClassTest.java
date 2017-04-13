package fga.mds.gpp.trezentos.Model;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserClassTest {

    @Test
    public void UserClass_ClassName() {
        UserClass userClass = new UserClass();
        userClass.setClassName("nome");
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
        userClass.setCutOff(floatCutOff);
        floatCutOff.equals(userClass.getCutOff());
    }

    @Test
    public void UserClass_Password(){
        UserClass userClass = new UserClass();
        userClass.setPassword("nome");
        assertEquals("nome", userClass.getPassword());
    }

    @Test
    public void UserClass_idUserAccountTest (){
        UserClass userClass = new UserClass();
        Integer integer = new Integer(10);
        userClass.setIdUserAccount(integer);
        assertEquals(integer,userClass.getIdUserAccount());
    }

    @Test
    public void UserClass_passwordTest (){
        UserClass userClass = new UserClass();
        userClass.setPassword("password");
        assertEquals("password",userClass.getPassword());
    }

    @Test
    public void UserClass_Addition() {
        UserClass userClass = new UserClass();
        Float floatAdd = new Float(10.0);
        userClass.setAddition(floatAdd);
        floatAdd.equals(userClass.getAddition());
    }

    @Test
    public void UserClass_SizeGroups() {
        UserClass userClass = new UserClass();
        userClass.setSizeGroups(10);
        assertEquals(10, userClass.getSizeGroups());
    }

}


