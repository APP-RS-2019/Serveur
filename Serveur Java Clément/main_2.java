import java.io.DataOutputStream;
import java.util.*;


public class main {

	public static void main(String[] args) {
		String bo="";
		boolean te=true;
		try {
			Scanner sc = new Scanner(System.in);
			ClientSocket test=new ClientSocket("193.48.125.66",80,"PC2");
			test.send("PC1","salut");
			test.reciev();
			while (te){
				//bo = sc.nextLine();
				System.out.println(test.reciev());
			}
			test.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
