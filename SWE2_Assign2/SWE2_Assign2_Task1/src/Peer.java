
package pkgfinal.version.swe2.ass2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.Socket;
import javax.json.Json;

/**
 *
 * @author A_Hawwas
 */
public class Peer {
 
    public Peer() {
    }
    
    public void createConnection(BufferedReader buf ,String user ,Sender S ) throws IOException
    {
        System.out.println("Please Enter The Ports That You Want To Recieve From (hostname:port)" + "\t" + "(S To Skip)");
        String input = buf.readLine();
        String [] values = input.split(" ");///name & port
        if(!input.equals("s"))
            for(int i=0;i<values.length;i++){ ///array for all port numbers
                String []name_port=values[i].split(":");
                Socket socket=null;
                try{
                    socket = new Socket(name_port [0] ,Integer.valueOf(name_port[1]));
                    new Reciever(socket).start();
                            }
                catch(Exception e)
                {
                    if(socket == null)
                        System.out.println("invalid!...skip");
                    else 
                        socket.close();
                }
            }
        connect( buf , user , S);
    }
    public void connect(BufferedReader buf ,String user_name ,Sender s)throws IOException {
    try{
        System.out.println(" You Are Connected Enter A Message or (Enter 0 to Exit , C To Change The Port).");
        while(true){
                String message= buf.readLine();
                if (message.equals("0"))
                {break;}
                
                else if (message.equals("c")||message.equals("C")){
                    createConnection(buf,user_name ,s);
                }
                else {
                    StringWriter writer = new StringWriter();
                    Json.createWriter(writer).writeObject(Json.createObjectBuilder().add("user_name",user_name)
                                                                                       .add("message",message)
                                                                                       .build());
                    System.out.println(writer);
                    s.sendMessageForAllPears(writer.toString());
                }
            }
        }
        catch (IOException e) {}
    }
}
