import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Peer extends Thread {

	public static void main(String[] args) throws java.net.SocketException, ClassNotFoundException{
		Scanner in = new Scanner(System.in);
		System.out.println("user?");
		String user = in.nextLine();
		System.out.println("password?");
		String password = in.nextLine();
		
		
		if (user.equals("goncalo") && password.equals("1234")) {
			
		
		System.out.println("SERVER OR USER? 1/2");
		int check = in.nextInt();
		if (check == 1) {	
		try {
			Socket s = new Socket("localhost", 23232);
			ObjectOutputStream outStream = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream inStream = new ObjectInputStream(s.getInputStream());
			System.out.println(inStream.readObject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		Peer peer = new Peer();
		peer.startServer();
	}
}else {
	System.out.println("Dados errados");
}
		


	}

	public void startServer (){
		ServerSocket sSoc = null;
		try {
			sSoc = new ServerSocket(23232);
			sSoc.getLocalPort();
			//System.out.println(sSoc.getLocalPort());

		} catch (IOException e) {
			System.out.println("O servidor PhotoShare so aceita ligacoes do porto : 23232");
			System.exit(-1);
		}
		while(true) {
			try {
				Socket inSoc = sSoc.accept();
				ServerThread newServerThread = new ServerThread(inSoc);
				newServerThread.start();
				//sSoc.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	//Threads utilizadas para comunicacao com os clientes
	class ServerThread extends Thread {

		private Socket socket = null;

		ServerThread(Socket inSoc) {
			socket = inSoc;
			System.out.println("Entrou novo cliente");
		}




		public void run() {
			try {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				try {


					out.writeObject("hello Client");
					//System.out.println(in.readObject());

				} catch(Exception e) {

				}
			}catch(IOException e) {

			}

		}

	}


}





