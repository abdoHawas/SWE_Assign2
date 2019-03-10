package pkgfinal.version.swe2.ass2;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import pkgfinal.version.swe2.ass2.Peer;
import pkgfinal.version.swe2.ass2.Sender;



public class FinalVersionSWE2Ass2 {

    /**
     * @param args the command line arguments
     **/
    public static void main(String[] args) throws IOException{
        BufferedReader buf=new BufferedReader(new InputStreamReader(System.in) );////take info from user
        System.out.println("Please Enter The User Name and The Port") ;
        String [] setup_values =buf.readLine().split(" ");
        Sender s =new Sender(setup_values[1]);///bya5od port
        s.start();///thread 
        new Peer().createConnection(buf ,setup_values[0],s );
    }

    
    
}
