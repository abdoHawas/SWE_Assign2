/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg2;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 *
 * @author Khloud
 */
public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    public Wallet() 
    {
        GenerateKeys();
    }
    public void GenerateKeys()
    {
        try
        {
            KeyPairGenerator GenKey = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            KeyPair keyPair = GenKey.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
        
    }

    
    
    
    
    
}
