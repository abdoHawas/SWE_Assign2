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
public class TransactionData {
    public String id;
    public PublicKey to;
    public int amount;
    public String parentTransId;

    public TransactionData(PublicKey to, int amount, String ParentTransId) {
        this.to = to;
        this.amount = amount;
        this.parentTransId = ParentTransId;
        this.id = generateHash.CreatHash(generateHash.getStringFromKey(to) + Integer.toString(amount) + parentTransId);
    }
    public boolean belongsto(PublicKey publickey)
    {
        return (publickey == to);
    }
    
}
