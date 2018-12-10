import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class sendThread extends Thread{

	private Scanner in; 
	private int port;
	private ArrayList<String> subscricoes;
	private Map<String,Integer> peers;

	public sendThread(String port) throws IOException {
		in = new Scanner(System.in);
		this.port = Integer.parseInt(port);
		subscricoes = new ArrayList<>();
		peers = new HashMap<String, Integer> ();
	}


	public void run() {
		System.out.println("Faça as suas subscrições");
		while(true) {
			String ms = "";
			if(!(ms = in.nextLine()).equals("")) {
				try {
					String[] splitsubs = ms.split(" ");
					switch(splitsubs[0]) {
					case "subscribe":
						//subscribe futebol cinema politica
						addsubs(splitsubs);
						break;

					case "publish":
						//publish ficheiro(path+nomeficheiro) tema

						Iterator it = peers.entrySet().iterator();
						while (it.hasNext()) {

							Map.Entry pair = (Map.Entry)it.next();
							System.out.println("a enviar ficheiro para vizinho "+(String)pair.getKey()+"...");

							Socket socket = new Socket((String)pair.getKey(),(int)pair.getValue());
							ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
							FileInputStream fis = new FileInputStream(splitsubs[1]);
							File file = new File(splitsubs[1]);

							int count;
							int temp = (int) file.length();
							//evento publish
							out.writeObject(1);
							out.writeObject(UUID.randomUUID());

							out.writeObject(splitsubs[2]);
							out.writeObject(temp);

							byte[] buffer = new byte[1024];
							while ((count = fis.read(buffer, 0, temp < 1024 ? temp : 1024)) > 0){
								out.write(buffer, 0, count);
								temp -= count;
								out.flush();
							}
							it.remove(); 

							fis.close();
							out.close();
							socket.close();








						}



						//publish ficheiro tema
						break;

					case "addPeer":
						//addPeer Ip porto
						peers.put(splitsubs[1], Integer.parseInt(splitsubs[2]));
						break;
					}
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

	public void addsubs(String[] splitsubs) {
		for(int i = 1;i< splitsubs.length;i++) {
			subscricoes.add(splitsubs[i]);
		}


	}


}