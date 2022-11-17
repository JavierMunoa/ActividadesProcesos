import java.util.ArrayList;

public class Main {
    //Booleano con el que los hilos podrán saber si numeroOculto ha sido encontrado  o no.
    public static boolean encontrado = false;
    public static int numeroOculto = (int)(Math.random()*101);

    public static void main(String[] args) {
        ArrayList<NumeroOculto> listaHilos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listaHilos.add(new NumeroOculto());
            listaHilos.get(i).setName("Hilo" + (i+1));
        }
        for (int i = 0; i < listaHilos.size(); i++) {
            listaHilos.get(i).start();
        }

    }
}