import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.json.*;

public class ClientSocket {
	Socket soc;

	private BufferedOutputStream bos;
	private BufferedInputStream bis;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	private byte[] b;
	public boolean open;
	private OutputStream outStramToServer;

	public ClientSocket(String ip, int port, String name) throws Exception {
		this.soc=new Socket(ip,port);
		this.bis = new BufferedInputStream(soc.getInputStream());
		this.bos = new BufferedOutputStream(soc.getOutputStream());
		this.inFromServer = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		this.outStramToServer = soc.getOutputStream();
		this.outToServer = new DataOutputStream(this.outStramToServer);
		this.b=b;
		this.connect(name);
	}


	private void connect(String name){
		try {
			this.send(name);
			System.err.println("vous etes connecte");
			this.open=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public void close() throws Exception{
		this.outToServer.close();
		this.inFromServer.close();
		this.soc.close();
		System.err.println("vous avez bien été déconnecté");
		this.open=false;
	}
	
	
//	public void dialog (String destRobot, String order){
//		try {
//			
//			System.out.println(this.reciev());
//			
//			/*b =destRobot.getBytes();
//			//this.sentence = inFromServer.readLine();
//			outToServer.write(b,0,destRobot.length());
//			this.outStramToServer.flush();
//			outToServer.flush();*/
//
//			System.out.println("order: "+destRobot);
//			this.send(destRobot);
//			
//			System.out.println(this.reciev());
//			
//			/*b=order.getBytes();
//			this.sentence = inFromServer.readLine();
//			outToServer.write(b,0,order.length());
//			this.outStramToServer.flush();
//			outToServer.flush();*/
//			
//			System.out.println("order: "+order);
//			this.send(order);
//			
//			System.out.println(this.reciev());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void sendOrder (String destRobot, String order){
		String jsonOrder = this.formatJson(destRobot, order);
		this.send(jsonOrder);
	}
	
	public String formatJson(String destRobot, String order) {
		JSONObject jsonobject=new JSONObject();
		JSONObject robots = new JSONObject();
		JSONArray tab = new JSONArray();
		
		jsonobject.put("name", destRobot);
		jsonobject.put("order", order);
		tab.put(jsonobject);
		robots.put("robot", tab);
		String jsonformate=robots.toString();//"{\"Robot\":[{\"name\": \""+destRobot+"\",\"order\":\""+order+"\"}]}";
		System.out.println(jsonformate);
	return jsonformate;
}


	public void send(String msg){
		b=msg.getBytes();
		try {
			outToServer.write(b,0,msg.length());
			this.outStramToServer.flush();
			outToServer.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String reciev(){

		try{
			String sentence = inFromServer.readLine();
			return sentence;
		}
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
}
