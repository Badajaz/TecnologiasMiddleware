import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author goncalocardoso
 *
 */
public class SendData {

	private String ficheiro;
	private int tamanhoFicheiro;
	private File file;
	private UUID number;
	private String evento;
	private String tema;
	private List<String> subs;

	public SendData(String evento) {
		this.evento = evento;
	}



	public String getEvento() {
		return evento;
	}



	public void setEvento(String evento) {
		this.evento = evento;
	}

	public SendData(String[] subscricoes) {
		subs = new ArrayList<>();
		for(int i = 1;i< subscricoes.length;i++) {
			subs.add(subscricoes[i]);
		}
	}

	public SendData(String ficheiro, int tamanhoFicheiro, File file, UUID number,String tema) {

		this.ficheiro = ficheiro;
		this.tamanhoFicheiro = tamanhoFicheiro;
		this.file = file;
		this.number = number;
		this.tema = tema;
	}


	public List<String> getSubs() {
		return subs;
	}



	public void setSubs(List<String> subs) {
		this.subs = subs;
	}



	public String getTema() {
		return tema;
	}



	public void setTema(String tema) {
		this.tema = tema;
	}



	public String getFicheiro() {
		return ficheiro;
	}

	public void setFicheiro(String ficheiro) {
		this.ficheiro = ficheiro;
	}

	public int getTamanhoFicheiro() {
		return tamanhoFicheiro;
	}

	public void setTamanhoFicheiro(int tamanhoFicheiro) {
		this.tamanhoFicheiro = tamanhoFicheiro;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public UUID getNumber() {
		return number;
	}

	public void setNumber(UUID number) {
		this.number = number;
	}








}
