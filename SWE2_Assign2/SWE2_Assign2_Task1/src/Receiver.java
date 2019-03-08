import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class Receiver  extends Thread {
	
	private MulticastSocket mulSocket = null ;
	private byte [] buffer = new byte[1024];
	private String message ;
	private Queue queuing ;
	
	public Receiver() throws IOException
	{
		mulSocket = new MulticastSocket(5555);
		message = "";
		queuing = new Queue();
		
	}
	
	public void joinToChat() throws IOException
	{
		InetAddress chat = null ;
		chat = InetAddress.getByName("230.0.0.0");
		this.mulSocket.joinGroup(chat);
	}
	
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.joinToChat();
			DatagramPacket dgPacket ;
			while(true)
			{
				dgPacket = new DatagramPacket(buffer, buffer.length);
				this.mulSocket.receive(dgPacket);
				this.message = new String (dgPacket.getData() , 0 , dgPacket.getLength());
				System.out.println(" Received " + this.message);
				this.queuing.writeToFile(this.message);
				this.message = "";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
