import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
	Socket soc;
	String sentence;
	private BufferedOutputStream bos;
	private BufferedInputStream bis;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	public boolean open;

	public ClientSocket(String ip, int port, String name) throws Exception {
		this.soc=new Socket(ip,port);
		this.bis = new BufferedInputStream(soc.getInputStream());
		this.bos = new BufferedOutputStream(soc.getOutputStream());
		this.inFromServer = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		this.outToServer = new DataOutputStream(soc.getOutputStream());
		this.connect(name);
	}


	public void connect(String name){
		try {
			this.sentence = inFromServer.readLine();
			byte[] b = name.getBytes();
			outToServer.write(b,0,name.length()); 
			outToServer.flush();
			this.sentence = inFromServer.readLine();
			System.err.println(sentence);
			this.open=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	
	public void close() throws Exception{
		String fin ="";
		byte[] b = fin.getBytes();
		outToServer.write(b,0,fin.length()); 
		outToServer.flush();
		
		this.sentence = inFromServer.readLine();
		fin="fin";
		b = fin.getBytes();
		outToServer.write(b,0,fin.length()); 
		outToServer.flush();
		
		this.sentence = inFromServer.readLine();
		b = fin.getBytes();
		outToServer.write(b,0,fin.length()); 
		outToServer.flush();
		
		this.sentence = inFromServer.readLine();
		System.err.println("vous avez bien été déconnecté");
		this.open=false;
	}
	
	
	public void send (String sentence){
		DataOutputStream outToServer;
		try {
			outToServer = new DataOutputStream(soc.getOutputStream());
			outToServer.writeBytes(sentence + '\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
