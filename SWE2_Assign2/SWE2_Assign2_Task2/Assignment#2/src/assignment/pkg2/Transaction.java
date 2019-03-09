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
public class Transaction {
    public PublicKey from;
    public PublicKey to;
    private int amount;
    public byte[] signature;

    public PublicKey getFrom() {
        return from;
    }

    public void setFrom(PublicKey from) {
        this.from = from;
    }

    public PublicKey getTo() {
        return to;
    }

    public void setTo(PublicKey to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Transaction(PublicKey from, PublicKey to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public  byte[] CreateSign(PrivateKey privateKey, String input) {

		Signature Sign;

		byte[] output = new byte[0];

		try 
                {

			Sign = Signature.getInstance("ECDSA", "BC");

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
    public static boolean VerifySign(PublicKey publicKey, String data, byte[] signature) {

		try 
                {

			Signature verSign = Signature.getInstance("ECDSA", "BC");

			verSign.initVerify(publicKey);

			verSign.update(data.getBytes());

			return verSign.verify(signature);

		}
                catch(Exception e) 
                {

			throw new RuntimeException(e);

		}

	}
    public static String getStringFromKey(Key key) 
    {

		return Base64.getEncoder().encodeToString(key.getEncoded());

    }
    public void generateSignature(PrivateKey privateKey) {
        String data = getStringFromKey(from) + getStringFromKey(to) + Float.toString(amount)    ;
        signature = CreateSign(privateKey,data);        
    }
    
    public boolean verifySignature() {
        String data = getStringFromKey(from) + getStringFromKey(to) + Float.toString(amount)    ;
        return VerifySign(from, data, signature);
    }
    
}
