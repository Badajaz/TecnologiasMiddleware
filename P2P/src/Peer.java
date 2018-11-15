import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Peer {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner input = new Scanner(System.in);
		//System.out.println("Pesquisa?");
		//String pesquisa = input.nextLine();
		
		/*
		
		while(true) {
			Socket s = new Socket("localhost", 9090);
			//BufferedReader input =new BufferedReader(new InputStreamReader(s.getInputStream()));
			//String answer = input.readLine();
			
			ObjectOutputStream outStream = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream inStream = new ObjectInputStream(s.getInputStream());
			System.out.println(inStream.readObject());
			System.out.println("INTRODUZA O NOME DO FICHEIRO");
			//String pesquisa = in.nextLine();
			
			outStream.writeObject(pesquisa);
			
			
			//s.setKeepAlive(true);
		}
		
		*
		*
		*/
		
		

		ServerSocket listener = new ServerSocket(9090);
		try {
				Socket socket = listener.accept();
				try {
					
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					out.writeObject("hello Client");
					System.out.println(in.readObject());

				} finally {
					socket.close();
				}
			
		}
		finally {
			listener.close();
		}

	}




}


