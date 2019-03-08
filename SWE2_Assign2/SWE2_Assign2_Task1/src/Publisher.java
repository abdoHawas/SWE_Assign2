import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class Publisher extends Thread {
	
	private DatagramSocket dgSocket ;
	private InetAddress chat;
	private byte[] buffer = new byte[1024];
	private String message ;
	
	
	public Publisher(){}
	
	public void sendTOAll(String message) throws IOException
	{
		this.setMessage(message) ;
		dgSocket = new DatagramSocket();
		
		chat = InetAddress.getByName("230.0.0.0");
		buffer = message.getBytes();
		
		DatagramPacket dgPacket = new DatagramPacket(buffer, buffer.length , chat , 5555);
		dgSocket.send(dgPacket);
		dgSocket.close();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				System.out.println("Write Your Message Or ( E ) To Exit ");
				String msg = new Scanner(System.in).nextLine();
				if(msg.equals("e")||msg.equals("E"))
					break;
				this.sendTOAll(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
