/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.version.swe2.ass2;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author A_Hawwas
 */
public class Sender extends Thread{
    
    private ServerSocket serverSocket;
    private Set<Connection>allConnectedPeers=new HashSet<Connection>();
    
    public Sender(String portNum ) throws IOException{
        
       serverSocket = new  ServerSocket(Integer.valueOf(portNum)) ;
    }
    public void run(){
        try{
            while(true){
                Connection conn = new Connection (serverSocket.accept(),this);
                allConnectedPeers.add(conn);
                conn.start();
            }
    }   catch (Exception ex) {ex.printStackTrace();        }
    }
    public void sendMessageForAllPears(String mess){
        try{allConnectedPeers.forEach(t->t.getPrintWriter().println(mess));}///display the message
    catch(Exception e){e.printStackTrace();}}

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    public Set<Connection>getConnectedPeers()
    {
        return allConnectedPeers;
    }
    
}
