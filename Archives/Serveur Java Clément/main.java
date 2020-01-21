
public class main {

	public static void main(String[] args) {
		try {
			ClientSocket test=new ClientSocket("193.48.125.71",80,"appliTest");
			test.send("raspberry","salut");
			test.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
