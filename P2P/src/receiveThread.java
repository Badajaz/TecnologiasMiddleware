import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class receiveThread extends Thread{
	private ServerSocket serverSocket;
	private ArrayList<UUID> arrayNumber;
	private BlockingQueue<SendData> queue;

	public receiveThread(String sock, BlockingQueue<SendData> queue) throws NumberFormatException, IOException {
		serverSocket = new ServerSocket(Integer.parseInt(sock));
		arrayNumber = new ArrayList<>();
		this.queue=queue;
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
						String tema = (String)in.readObject();
						int tamanhoFicheiro = (int)in.readObject();
						FileOutputStream fos = new FileOutputStream(ficheiro);
						int count;
						int temp = tamanhoFicheiro;
						//download file
						byte[] buffer = new byte[1024];
						while ((count = in.read(buffer, 0, temp < 1024 ? temp : 1024)) > 0){
							fos.write(buffer, 0, count);
							temp -= count;
							fos.flush();
						}
						
						queue.add(new SendData("publish " + ficheiro + " " + tamanhoFicheiro + " " + number + " " + tema));
						arrayNumber.add(number);

						fos.close();
						in.close();
						server.close();
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
