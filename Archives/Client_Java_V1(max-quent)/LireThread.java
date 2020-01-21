package client_java_v1;
import java.io.*; 
import java.net.*; 


public class LireThread extends Thread {
	Socket clientSocket;
	String modifiedSentence;
	
	public LireThread (Socket socket) {
		this.clientSocket = socket;
	}
	
	@Override
	
	public void run() {
	
		try {
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			while(JeuDeTest.test) {


				modifiedSentence = inFromServer.readLine(); 
				System.out.println( modifiedSentence); 

				if (modifiedSentence.equals("fin")) {
					System.out.println("deco reussi");
					JeuDeTest.test=false;
					clientSocket.close();
				}
		
			}
		
		} 
		
		catch (IOException e) {

			//e.printStackTrace();
		}
	
	}

}
