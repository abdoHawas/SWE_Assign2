/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg2;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 *
 * @author Khloud
 */
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        BlockChain MyBlockChain = new BlockChain();
        ArrayList<Vote>votesToMine = new ArrayList<Vote>();
        ArrayList<PublicKey> didVote = new ArrayList<PublicKey>();
        Voter voter = new Voter();;
        String Vote;
        Scanner input = new Scanner(System.in);
    
        while (true)
        {
            String choice;
            System.out.println("1-Add Voter\n2-Vote\n3-Minechain\n4-Checkchain\nvalidation 5-Exit");
            choice = input.next();
            if(choice.compareTo("1") == 0)
            {
                voter = new Voter();
                System.out.print("Please enter your Name :");
                input.nextLine();
                voter.setName(input.nextLine());
                voter.GenerateKeys();
                
            }
            else if(choice.compareTo("2") == 0)
            {
                if(didVote.contains(voter.getPublicKey()))
                {
                    System.out.println("You already voted!");
                }
                else
                {
                    System.out.println("1-Choice A 2-Choice B 3-Choice B 4-Choice C 5-Choice D");
                    choice = input.next();
                    byte[] signature = voter.CreateSign(voter.getPrivateKey(), choice);
                    Vote thisVote = new Vote(voter.getPublicKey(), choice, signature);
                    votesToMine.add(thisVote);
                    didVote.add(voter.getPublicKey());
                }
                
            }
            else if(choice.compareTo("3") ==0 )
            {
                if(votesToMine.size()==0)
                {
                    System.out.println("Nothing to mine \n");
                }
                else
                {
                    Vote temp = votesToMine.get(0);
                    if(voter.VerifySign(temp.getVoterPublicKey(), temp.getVote(), temp.getSignature()))
                    {
                         Block BlockToMine = new Block( new java.util.Date(), temp.getVote());
                          BlockChain.addBlock(BlockToMine);
                        votesToMine.remove(0);
                    }
                    else
                    {
                        System.out.println("Data has been tampered with");
                        System.out.println();
                    }
                }
            }
            else if(choice.compareTo("4") == 0)
            {
                if(BlockChain.validateChain())
                {
                    System.out.println("BlockChain is valid \n");
                }
                else
                {
                    System.out.println("BlockChain is invalid \n");
                }
            }
            else if(choice.compareTo("5") ==0)
            {
                break;
            }
            else
            {
                System.out.println("Wrong choice! \n");
            }
        
            
            
        
        }
    
   }
}
