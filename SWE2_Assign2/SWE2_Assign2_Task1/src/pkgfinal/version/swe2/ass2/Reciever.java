/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.version.swe2.ass2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author A_Hawwas
 */
public class Reciever extends Thread{
    
    private BufferedReader buffer;
    public Reciever (Socket s )throws IOException
    {
      buffer =new BufferedReader(new InputStreamReader(s.getInputStream()) );
    }
    public void run(){ ///TO receive from another node
        while(true){
        try{
             JsonObject jSon=Json.createReader (buffer).readObject();
             if(jSon.containsKey("user_name")){
                 System.out.println("<"+jSon.getString("user_name")+" send:>"+jSon.getString("message"));}
        }catch(Exception e){interrupt();}
        }
    }

    public BufferedReader getBuffer() {
        return buffer;
    }
    
    
}
