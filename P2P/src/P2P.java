import java.io.IOException;

public class P2P {

	public static void main (String [] args) {
		try {
			new receiveThread(args[0]).start();
			new sendThread(args[1]).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}



}
