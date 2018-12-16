import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class P2P {

	public static void main (String [] args) {
		Scanner in = new Scanner(System.in);
		
		try {
			if(args[2] == null || args[3] == null){
				System.out.prtintln("por favor inicie o program fornecendo user e password");
				break;
			}
			string user = args[2];
			String password = args[3];
			if(verificaLogin(user, password)){
				BlockingQueue<SendData> queue = new LinkedBlockingQueue<SendData>();
			} else {
				system.out.prtintln("Novo ultilizador, as suas credenciais serão adicionadas. Para confirmar carregue 1");
			}
			new receiveThread(args[0],queue).start();
			new sendThread(queue).start();
			while(true) {
				String ms;
				if(!(ms = in.nextLine()).equals("")) {
					if (ms.equals("quit"))
						break;
					else
						queue.add(new SendData(ms));
				}
			}

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();

	}

	private boolean verificaLogin(string user, string password){
		//TODO
		return false;
	}

}
