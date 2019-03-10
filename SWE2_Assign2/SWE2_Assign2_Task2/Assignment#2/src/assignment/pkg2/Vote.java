/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg2;

import java.security.PublicKey;

/**
 *
 * @author Khloud
 */
public class Vote {
    public PublicKey VoterPublicKey;
    public String Vote;
    public byte[] signature;

    public Vote(PublicKey VoterPublicKey, String Vote, byte[] signature) {
        this.VoterPublicKey = VoterPublicKey;
        this.Vote = Vote;
        this.signature = signature;
    }

    public PublicKey getVoterPublicKey() {
        return VoterPublicKey;
    }

    public void setVoterPublicKey(PublicKey VoterPublicKey) {
        this.VoterPublicKey = VoterPublicKey;
    }

    public String getVote() {
        return Vote;
    }

    public void setVote(String Vote) {
        this.Vote = Vote;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    
}
