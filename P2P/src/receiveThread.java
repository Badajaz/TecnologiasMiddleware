import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class receiveThread extends Thread{
	private ServerSocket serverSocket;
	private ArrayList<UUID> arrayNumber;

	public receiveThread(String sock) throws IOException {
		serverSocket = new ServerSocket(Integer.parseInt(sock));
		arrayNumber = new ArrayList<>();
	}

	public void run() {
		while(true) {
			try {
				Socket server = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(server.getInputStream());


				switch((int)in.readObject()) {
				case 1:
					UUID number = (UUID) in.readObject();
					if(arrayNumber.indexOf(number) == -1) {
						String ficheiro = (String)in.readObject();
						int tamanhoFicheiro = (int)in.readObject();
						FileOutputStream fos = new FileOutputStream(ficheiro);
						int count;
						int temp = tamanhoFicheiro;
						byte[] buffer = new byte[1024];
						while ((count = in.read(buffer, 0, temp < 1024 ? temp : 1024)) > 0){
							fos.write(buffer, 0, count);
							temp -= count;
							fos.flush();
						}
						
						arrayNumber.add(number);

						fos.close();
						in.close();
					}

					break;

				}




			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
