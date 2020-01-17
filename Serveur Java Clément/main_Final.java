import java.io.DataOutputStream;
import java.util.*;


public class main {

	public static void main(String[] args) {
		String bo="";
		boolean te=true;
		try {
			//Scanner sc = new Scanner(System.in);
			ClientSocket test=new ClientSocket("193.48.125.71",80,"PC2");
			//ok
			test.send("Serveur","salut");
			//ok
			test.reciev();
			while (te){
				bo = test.reciev();
				System.out.println(bo);

				if (bo.equals("fin")){
					te = false;
				}
			}
			System.out.println("cc exit");
			test.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
