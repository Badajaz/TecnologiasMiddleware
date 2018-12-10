import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {

	public static void main (String [] args) {
	 try {
		Socket socket = new Socket("localhost",23456);
		
		
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

			String user = "Rui";
			String passwd = "lala";
		
			outStream.writeObject(user);
			outStream.writeObject(passwd);
			

			outStream.close();
			inStream.close();
			
			socket.close();

		
		
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	}
}
