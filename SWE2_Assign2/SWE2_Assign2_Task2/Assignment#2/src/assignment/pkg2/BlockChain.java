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
    private static  ArrayList<Block> chain;
    private static  int difficulty = 1;
    public BlockChain ()
    {
        chain = new ArrayList<Block>();
        chain.add(generateGenesis());
    }
    private Block generateGenesis()
    {
        Block gensis =  new Block( new java.util.Date(),null);
        gensis.setPreviousHash(null);
        gensis.getHash();
        return gensis;
    }
    public static void addBlock(Block blockToAdd)
    {
        blockToAdd.proofOfWork(difficulty);
        blockToAdd.setPreviousHash(chain.get(chain.size()-1).getHash());
        chain.add(blockToAdd);
    }
    public static boolean  validateChain()
    {
        for(int i = 1; i <chain.size();i++)
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
        }
    }
    
}
