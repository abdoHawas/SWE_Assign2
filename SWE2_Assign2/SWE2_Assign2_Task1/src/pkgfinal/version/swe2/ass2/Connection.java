/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.version.swe2.ass2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author A_Hawwas
 */
public class Connection extends Thread{
    
    private Sender sender;
    private Socket Socket;
    private PrintWriter writer;
    public Connection(Socket So , Sender s){
        this.sender=s;
        this.Socket=So;
    }
    public void run(){
        try{
                BufferedReader buf=new BufferedReader(new InputStreamReader(this.Socket.getInputStream()) );
                this.writer=new PrintWriter(Socket.getOutputStream(),true);
                while(true){sender.sendMessageForAllPears(buf.readLine());}
        }catch (Exception ex) {sender.getConnectedPeers().remove(this);        }
    }       
    

    public Sender getSender() {
        return sender;
    }

    public Socket getSocket() {
        return Socket;
    }
    
    public PrintWriter getPrintWriter()
    {
        return writer;
    }
    
}