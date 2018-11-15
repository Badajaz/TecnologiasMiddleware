import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class PeerDois {

	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		while(true) {
			Socket s = new Socket("localhost", 9090);
			//BufferedReader input =new BufferedReader(new InputStreamReader(s.getInputStream()));
			//String answer = input.readLine();
			ObjectOutputStream outStream = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream inStream = new ObjectInputStream(s.getInputStream());
			System.out.println(inStream.readObject());
			s.setKeepAlive(true);
		}
			
		

	}
}
