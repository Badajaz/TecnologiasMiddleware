import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class PeerDois {



	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner in = new Scanner(System.in);

		Socket s = new Socket("localhost", 23232);

		ObjectOutputStream outStream = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream inStream = new ObjectInputStream(s.getInputStream());
		System.out.println(inStream.readObject());




	}
}
