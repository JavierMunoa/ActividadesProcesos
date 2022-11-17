import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<HiloDurmiente> listaHilosDurmientes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listaHilosDurmientes.add(new HiloDurmiente());
            listaHilosDurmientes.get(i).setName("Hilo "+i);
        }
        for (int i = 0; i < 5; i++) {
            listaHilosDurmientes.get(i).start();
        }
    }
}