package client_java_v1;
import java.io.*; 
import java.net.*; 


public class JeuDeTest {
	
	public static boolean test;
	
	
	public static void main(String[] args) throws Exception {
		test=true;
		Socket clientSocket = new Socket("i5-68.univ-savoie.fr", 1933);
		Thread t = new Thread (new LireThread(clientSocket));
		t.start();
		Thread e = new Thread(new EcrireThread(clientSocket));
		e.start();
		
		
		
	}
}
