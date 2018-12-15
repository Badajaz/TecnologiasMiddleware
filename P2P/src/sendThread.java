import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class sendThread extends Thread{

	private ArrayList<String> subscricoes;
	private Map<String,Integer> peers;
	private BlockingQueue<SendData> queue;

	public sendThread(BlockingQueue<SendData> queue) throws IOException {
		subscricoes = new ArrayList<>();
		peers = new HashMap<String, Integer> ();
		this.queue = queue;
	}


	public void run() {
		
		System.out.println("Faça as suas subscrições | publish <ficheiro> <tema> "
				+ " | subscribe <tema> | addPeer <IP> <Porto>");
		
		while(true) {
			String ms = "";
			if ( (queue.peek() != null ) && (queue.peek().getEvento() != null)) {
				
				try {
					String[] splitsubs;

					SendData sd = queue.take();
					splitsubs = sd.getEvento().split(" ");

					switch(splitsubs[0]) {

					case "publish":
						//publish ficheiro(path+nomeficheiro) tema
						
						// ver se o tema da mensagem esta subscrito
						UUID uid;
						String tema;
						if(splitsubs.length == 5) {
							
							if(subscricoes.contains(splitsubs[4]))
								System.out.println("Recebi uma subscricao do tema " + 
									splitsubs[4] + " com o ficheiro " + splitsubs[1]);
							tema = splitsubs[4];
							uid = UUID.fromString(splitsubs[3]);
							
						} else {
							uid = UUID.randomUUID();
							tema = splitsubs[2];
						}

						Iterator<Entry<String, Integer>> it = peers.entrySet().iterator();
						while (it.hasNext()) {

							Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
							System.out.println("a enviar ficheiro para vizinho "+(String)pair.getKey()+"...");

							Socket socket = new Socket((String)pair.getKey(),(int)pair.getValue());
							ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
							FileInputStream fis = new FileInputStream(splitsubs[1]);
							File file = new File(splitsubs[1]);

							int count;
							int temp = (int) file.length();
							//evento publish
							out.writeObject(1);
							out.writeObject(uid);
							out.writeObject(splitsubs[1]);
							out.writeObject(tema);
							out.writeObject(temp);

							byte[] buffer = new byte[1024];
							while ((count = fis.read(buffer, 0, temp < 1024 ? temp : 1024)) > 0){
								out.write(buffer, 0, count);
								temp -= count;
								out.flush();
							}
							
							fis.close();
							out.close();
							socket.close();

						}

						//publish ficheiro tema
						break;
						
					case "subscribe":
						subscricoes.add(splitsubs[1]);
						System.out.println("Adicionei uma nova subscricao, subscricoes atuais sao " +
								subscricoes);

						break;

					case "addPeer":
						//addPeer Ip porto
						if(splitsubs.length != 3) {
							System.out.println("Faltam argumentos!");
							break;
						}
						
						peers.put(splitsubs[1], Integer.parseInt(splitsubs[2]));
						System.out.println("Adicionei um novo vizinho, peers atuais sao " + peers);
						break;
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}



	}



}