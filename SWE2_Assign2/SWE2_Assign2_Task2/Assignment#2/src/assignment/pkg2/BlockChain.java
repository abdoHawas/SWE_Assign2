/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Khloud
 */
public class BlockChain {
    private List<Block> chain;
    private List<Transaction> Ptrans;
    public static HashMap<String,TransactionData> UST = new HashMap<String,TransactionData>();
    public BlockChain ()
    {
        chain = new ArrayList<Block>();
        chain.add(generateGenesis());
    }
    private Block generateGenesis()
    {
        Block gensis =  new Block("0", new java.util.Date(),null);
        gensis.setPreviousHash(null);
        gensis.getHash();
        return gensis;
    }
    void addBlock(Block blockToAdd)
    {
        blockToAdd.proofOfWork(1);
        blockToAdd.setPreviousHash(chain.get(chain.size()-1).getHash());
        chain.add(blockToAdd);
    }
    void CreateTrans(Transaction t)
    {
        Ptrans.add(t);
    }
    boolean validateChain()
    {
        for(int i = 0; i <chain.size();i++)
        {
            Block CurrentBlock = chain.get(i);
            Block PrevBlock = chain.get(i-1);
            if (CurrentBlock.getHash() != CurrentBlock.CreatHash())
                return false;
            else if(CurrentBlock.getPreviousHash() != PrevBlock.getHash())
                return false;
        }
        return true;
    }
    void printChain()
    {
        for (int i =0;i<chain.size();i++)
        {
            System.out.println(chain.get(i).getHash());
            System.out.println(chain.get(i).getPreviousHash());
            System.out.println(chain.get(i).getTimeStamp());
            System.out.println(chain.get(i).getIndex());
        }
    }
    
}
