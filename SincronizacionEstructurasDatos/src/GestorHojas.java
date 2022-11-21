import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GestorHojas extends Thread {

	private static List<String> lista = new ArrayList<String>();

	@Override
	public void run() {
		propuestaNumero();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new GestorHojas().start();
		}

	}

	/**
	 * Introducimos el código que había en el run en un método synchronized para que así los método estén sincronizados
	 *
	 */
	synchronized public static void propuestaNumero(){
		CopyOnWriteArrayList<String> copiaLista = new CopyOnWriteArrayList(lista);
		for (int i = 0; i < 10; i++) {
			copiaLista.add(new String("Texto" + i));
		}

		for (String string : copiaLista) {
			System.out.println(string);
		}
	}


	}
