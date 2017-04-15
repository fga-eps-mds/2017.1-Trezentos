package fga.mds.gpp.trezentos.Model.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {

    public static String nextSalt() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static String stringToMD5(String string) {
        String md5 = null;

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(string.getBytes(), 0, string.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return md5;
    }

    public static boolean decryptPass(String hashedPassword, String salt, String password){
        boolean isValid = false;

        String compare = stringToMD5(salt+password);

        if(compare.equals(hashedPassword)){
            isValid = true;
        }
        return isValid;
    }
}