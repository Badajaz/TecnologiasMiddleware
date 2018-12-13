import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class receiveThread extends Thread{
	private ServerSocket serverSocket;
	private ArrayList<UUID> arrayNumber;
	private BlockingQueue<SendData> queue;
	private BlockingQueue<String> filaSubscricoes;
	


	public receiveThread(String sock, BlockingQueue<SendData> queue,BlockingQueue<String> subscricoes) throws NumberFormatException, IOException {
		serverSocket = new ServerSocket(Integer.parseInt(sock));
		arrayNumber = new ArrayList<>();
		this.queue=queue;
		this.filaSubscricoes = subscricoes;

	}

	public void run() {
		while(true) {
				
			try {
			
				
				Socket server = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(server.getInputStream());

				System.out.println("Linha 38");
				switch((int)in.readObject()) {
				case 1:
					UUID number = (UUID) in.readObject();
					if(arrayNumber.indexOf(number) == -1) {
						String ficheiro = (String)in.readObject();
						String tema = (String)in.readObject();
						int tamanhoFicheiro = (int)in.readObject();
						System.out.println("Linha 54");
						FileOutputStream fos = new FileOutputStream(ficheiro);
						int count;
						int temp = tamanhoFicheiro;
						byte[] buffer = new byte[1024];
						while ((count = in.read(buffer, 0, temp < 1024 ? temp : 1024)) > 0){
							fos.write(buffer, 0, count);
							temp -= count;
							fos.flush();
						}
						
						SendData sd = new SendData(ficheiro, tamanhoFicheiro, new File(ficheiro), number, tema);
						System.out.println("Linha 66");
						System.out.println(filaSubscricoes+" "+tema);
						if (filaSubscricoes.contains(tema)) {
							System.out.println("Recebi uma susbscrição de "+tema+" nome do ficheiro "+ficheiro);
						}
						queue.add(sd);
						arrayNumber.add(number);
						System.out.println("Linha 72");
						

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
