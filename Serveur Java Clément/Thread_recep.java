
public class Thread_recep extends Thread {
	public ClientSocket serveur;
	public String prout;
	
	public Thread_recep(ClientSocket serveur){
		this.serveur=serveur;
		System.out.println("thrd recpet ouvert");
	}
	
	public void run(){
		
		while (true){

			prout=serveur.reciev();
			System.out.println(prout);
			Thread.yield();
		}
	}
}
