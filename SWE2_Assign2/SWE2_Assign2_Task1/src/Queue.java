import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Queue {
	private File chat ;
	private PrintWriter out ;
	private Scanner in ;
	
	public Queue()
	{
		chat = null ;
		out = null ;
		in = null ;
	}
	
	public void writeToFile(String data) throws IOException
	{
		chat = new File("data.txt");
		out = new PrintWriter(chat);
		out.println(data);
		out.close();
	}
	
	public void getFromFile() throws IOException
	{
		in = new Scanner(chat);
		in.close();
	}

}
