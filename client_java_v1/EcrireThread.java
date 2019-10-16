package client_java_v1;
import java.io.*; 
import java.net.*; 


public class EcrireThread extends Thread {

		Socket clientSocket;
		String modifiedSentence;
		String sentence;
		
		public EcrireThread (Socket socket) {
			this.clientSocket = socket;
		}
		
		@Override
		
		public void run() {
			try {
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				while(JeuDeTest.test) {
					
				
					sentence = inFromUser.readLine();
					byte[] b = sentence.getBytes();
					outToServer.write(b,0,sentence.length()); 
					outToServer.flush();
					
					if (sentence.equals("fin")) {
						System.out.println("coucou");
						JeuDeTest.test=false;
						clientSocket.close();
					}
					
					
				}
			}
			
			catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			
			
			
			
		}
	
}


