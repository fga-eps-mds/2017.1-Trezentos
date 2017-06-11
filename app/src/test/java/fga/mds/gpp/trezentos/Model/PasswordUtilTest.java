package fga.mds.gpp.trezentos.Model;

import android.util.Log;

import org.junit.Test;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.Util.PasswordUtil;

import static org.junit.Assert.*;

public class PasswordUtilTest {

    @Test
    public void shouldValidateNextSalt(){
        String nextSalt = PasswordUtil.nextSalt();
        assertTrue(!nextSalt.equals("") && nextSalt.length() == 18);
    }

    @Test
    public void shouldValidateDecryptPass() throws UserException {
        String salt = PasswordUtil.nextSalt();
        String toBeHashed = (salt+"123456");
        String hashedPass = PasswordUtil.stringToMD5(toBeHashed);
        assertTrue(PasswordUtil.decryptPass(hashedPass, salt, "123456"));
    }

    @Test
    public void shouldValidateStringToMD5(){

        String password = "123456";
        String newPassword;

        newPassword = PasswordUtil.stringToMD5(password);
        assertNotEquals(password, newPassword);
    }
}
