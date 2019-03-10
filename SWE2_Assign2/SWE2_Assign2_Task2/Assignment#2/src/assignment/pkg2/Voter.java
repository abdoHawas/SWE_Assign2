package assignment.pkg2;


import assignment.pkg2.generateHash;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import static java.util.Date.from;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khloud
 */
public class Voter {
    String Name;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private byte [] signature;
    public void GenerateKeys()
    {
        try
        {
            KeyPairGenerator GenKey = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            GenKey.initialize(1024, random);
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
    public  byte[] CreateSign(PrivateKey privateKey, String input) {
		Signature Sign;

		byte[] output = new byte[0];

		try 
                {

			Sign = Signature.getInstance("SHA256withDSA");

			Sign.initSign(privateKey);

			byte[] strByte = input.getBytes();

			Sign.update(strByte);

			byte[] realSig = Sign.sign();

			output = realSig;

		} 
                catch (Exception e) 
                {

			throw new RuntimeException(e);

		}

		return output;

	}

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
        public static boolean VerifySign(PublicKey publicKey, String data, byte[] signature) {

		try 
                {

			Signature verSign = Signature.getInstance("SHA256withDSA");

			verSign.initVerify(publicKey);

			verSign.update(data.getBytes());

			return verSign.verify(signature);

		}
                catch(Exception e) 
                {

			throw new RuntimeException(e);

		}

	}
        public void generateSignature(PrivateKey privateKey) {
        String data = Name   ;
        signature = CreateSign(privateKey,data);        
    }
    


    
}
