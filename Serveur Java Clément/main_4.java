
public class main {

	public static void main(String[] args) {
		try {
			String sentence="";
			ClientSocket serveur = new ClientSocket("193.48.125.71", 1933, "AppliTest");
			Thread_recep thrd=new Thread_recep(serveur);
			thrd.start();
			Frame fenetre= new Frame(serveur);
			//thrd.notify();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
