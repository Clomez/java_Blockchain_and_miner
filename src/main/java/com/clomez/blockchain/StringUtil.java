package com.clomez.blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

    //Hash the strings
    public static String applyHash(String input) {
        try {

            //SHA-256 for fuck of it
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            //Hash as a hexadecimal
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append("0");
                hexString.append(hex);
            }
            return hexString.toString();



        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
