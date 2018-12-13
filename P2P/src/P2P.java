import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class P2P {

	public static void main (String [] args) {
		Scanner in = new Scanner(System.in);

		try {
			BlockingQueue<SendData> queue = new LinkedBlockingQueue<SendData>();
			BlockingQueue<String> subscricoes = new LinkedBlockingQueue<String>();
			new receiveThread(args[0],queue,subscricoes).start();
			new sendThread(queue).start();
			while(true) {
				String ms;
				if(!(ms = in.nextLine()).equals("")) {
					if (ms.equals("quit")) {
						break;
						
					}else if(ms.split(" ")[0].equals("subscribe")){
						subscricoes.add(ms.split(" ")[1]);
					}else {
						queue.add(new SendData(ms));
					}

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();

	}



}
