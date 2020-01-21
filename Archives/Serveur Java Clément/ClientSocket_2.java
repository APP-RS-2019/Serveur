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
	private byte[] b;
	public boolean open;

	public ClientSocket(String ip, int port, String name) throws Exception {
		this.soc=new Socket(ip,port);
		this.bis = new BufferedInputStream(soc.getInputStream());
		this.bos = new BufferedOutputStream(soc.getOutputStream());
		this.inFromServer = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		this.outToServer = new DataOutputStream(soc.getOutputStream());
		this.b=b;
		this.connect(name);
	}


	public void connect(String name){
		try {
			this.sentence = inFromServer.readLine();
			byte[] b = name.getBytes();
			outToServer.write(b,0,name.length()); 		//utiliser writeBytes
			outToServer.flush();
			this.sentence = inFromServer.readLine();	
			System.err.println(sentence);
			outToServer.flush();
			this.sentence = inFromServer.readLine();
			//System.out.println(sentence);
			this.open=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	
	public void close() throws Exception{
		String fin ="";
		b = fin.getBytes();
		outToServer.write(b,0,fin.length()); 	//utiliser writeBytes
		outToServer.flush();
		
		this.sentence = inFromServer.readLine();
		fin="fin";
		b = fin.getBytes();
		outToServer.write(b,0,fin.length()); 	//utiliser writeBytes
		outToServer.flush();
		
		this.sentence = inFromServer.readLine();
		b = fin.getBytes();
		outToServer.write(b,0,fin.length()); 	//utiliser writeBytes
		outToServer.flush();
		
		this.sentence = inFromServer.readLine();
		System.err.println("vous avez bien été déconnecté");
		this.open=false;
	}
	
	
	public void send (String destRobot, String order){
		try {
			b =destRobot.getBytes();
			this.sentence = inFromServer.readLine();
			outToServer.write(b,0,destRobot.length());
			outToServer.flush();
			
			b=order.getBytes();
			this.sentence = inFromServer.readLine();
			outToServer.write(b,0,order.length());
			outToServer.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String reciev(){
		try{
			this.sentence = inFromServer.readLine();
			return this.sentence;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return sentence;
	}
}
