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
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author Khloud
 */
public class Transaction {
    String id;
    public PublicKey from;
    public PublicKey to;
    private int amount;
    public byte[] signature;
    public ArrayList<UnSpentTransaction> UST = new ArrayList<UnSpentTransaction>();
    public ArrayList<TransactionData> TD = new ArrayList<TransactionData>();

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
    public void generateSignature(PrivateKey privateKey) {
        String data = generateHash.getStringFromKey(from) + generateHash.getStringFromKey(to) + Float.toString(amount)    ;
        signature = CreateSign(privateKey,data);        
    }
    
    public boolean verifySignature() {
        String data = generateHash.getStringFromKey(from) + generateHash.getStringFromKey(to) + Float.toString(amount)    ;
        return VerifySign(from, data, signature);
    }
    public boolean processTransaction() {

        if(verifySignature() == false) {

                System.out.println("#Transaction Signature failed to verify");

                return false;

        }



        //Gathers transaction inputs (Making sure they are unspent):

        for(UnSpentTransaction i : UST) {

                i.UST = BlockChain.UST.get(i.id);

        }


        //Generate transaction outputs:

        int leftOver = getInputsValue() - amount; //get value of inputs then the left over change:

        id = generateHash.CreatHash(generateHash.getStringFromKey(from)+generateHash.getStringFromKey(to)+ Integer.toString(amount));

        TD.add(new TransactionData( this.to, amount,id));

        TD.add(new TransactionData( this.from, leftOver,id)); //send the left over 'change' back to sender		



        //Add outputs to Unspent list

        for(TransactionData o : TD) {

                BlockChain.UST.put(o.id , o);

        }



        //Remove transaction inputs from UTXO lists as spent:

        for(UnSpentTransaction i : UST) {

                if(i.UST == null) continue; //if Transaction can't be found skip it 

                BlockChain.UST.remove(i.UST.id);

        }



        return true;

}
    public int getInputsValue() {

		int total = 0;

		for(UnSpentTransaction i : UST) {

			if(i.UST == null) continue; //if Transaction can't be found skip it, This behavior may not be optimal.

			total += i.UST.amount;

		}

		return total;

	}
}
