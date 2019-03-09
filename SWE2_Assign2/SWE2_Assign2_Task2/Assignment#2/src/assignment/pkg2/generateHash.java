/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg2;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Khloud
 */
public class generateHash {
    public static String CreatHash(String hashData)
        {
            MessageDigest digest;
            String encoded = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(hashData.getBytes(StandardCharsets.UTF_8));
                encoded = Base64.getEncoder().encodeToString(hash);
            }
            catch(NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return encoded;
        }
    public static String getStringFromKey(Key key) 
    {

		return Base64.getEncoder().encodeToString(key.getEncoded());

    }
    
}
