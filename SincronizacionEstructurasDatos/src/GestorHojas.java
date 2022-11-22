import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GestorHojas extends Thread {

	private static List<String> lista = new ArrayList<String>();
	CopyOnWriteArrayList<String> copiaLista = new CopyOnWriteArrayList(lista);

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			copiaLista.add(new String("Texto" + i));
		}

		synchronized (copiaLista){
			for (String string : copiaLista) {
				System.out.println(string);
			}
		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new GestorHojas().start();
		}

	}
}
