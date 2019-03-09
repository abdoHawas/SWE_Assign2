/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg2;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author Khloud
 */
public class Block
{
        private String index;
        private Date TimeStamp;
        private String hash;
        private String previousHash;
        private Transaction[] transaction;
        private int dummy = 0;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Date getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Date TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    public int getDummy() {
        return dummy;
    }

    public void setDummy(int dummy) {
        this.dummy = dummy;
    }


    public String getHash() {
        return hash;
    }

        public Block(String index, Date ts, Transaction[] t)
        {
            this.index = index;
            this.TimeStamp = ts;
            this.transaction = t;
            this.hash = this.CreatHash();
        }
        public String CreatHash()
        {
            String hashData = "" + this.index + this.TimeStamp + this.previousHash  + this.dummy;
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
            this.hash = encoded;
            return encoded;
        }
        public void proofOfWork(int diff)
        {
            System.out.println("Mining");
            char[] temp= new char[diff];
            Arrays.fill(temp, 'A');
            String s = new String (temp);
            while(this.hash.substring(0, diff).compareTo(s)!=0)
            {
                this.dummy++;
                this.hash = this.CreatHash();
                System.out.println(this.hash.substring(0, diff));
                System.out.println(s);
            }
            System.out.println("Mined");
        }
    
}
