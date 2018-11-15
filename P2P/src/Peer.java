import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Peer extends Thread{

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		ServerSocket listener = new ServerSocket(9090);
		try {
				Socket socket = listener.accept();
				try {
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					out.writeObject("hello Client");
					//out.flush();

				} finally {
					socket.close();
				}
			
		}
		finally {
			listener.close();
		}

	}




}


