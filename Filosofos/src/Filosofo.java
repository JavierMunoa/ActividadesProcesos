import java.util.ArrayList;

public class Filosofo extends Thread{
    private static ArrayList<clsPalillo> palillos = new ArrayList<>();
    public int idFilosofo;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            palillos.add(new clsPalillo());
        }

        Filosofo filosofo = null;
        for (int i = 0; i < 5; i++) {
            filosofo = new Filosofo();
            filosofo.idFilosofo = i;
            filosofo.setName("Filosofo " + (i+1));
            filosofo.start();
        }

    }

    @Override
    public void run() {
        super.run();
        int palillo1= idFilosofo;
        int palillo2 = idFilosofo+1;
        if (idFilosofo == 4){
            palillo2 = 0;
        }
        comer(palillo1,palillo2);
    }


    public void comer(int palillo1 ,int palillo2) {
        synchronized (palillos){
            try {
                if (palillos.get(palillo1).isEnUso()){
                    palillos.wait();
                    System.out.println("El filósofo " + this.getName() + " está esperando el palillo " + palillo1);
                }else if (palillos.get(palillo2).isEnUso()){
                    palillos.wait();
                    System.out.println("El filósofo " + this.getName() + " está esperando el palillo " + palillo2);
                }
            }catch (InterruptedException e){
                System.out.println("Ha ocurrido un error de interrupción.");
            }
        }
        palillos.get(palillo1).setEnUso(true);
        palillos.get(palillo2).setEnUso(true);
        System.out.println("El palillo " + palillo1 + " ha sido tomado.");
        System.out.println("El palillo " + (palillo2) + "ha sido tomado.");
        System.out.println("El filósofo " + idFilosofo + " está comiendo.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El filósofo " + idFilosofo + " ha terminado de comer.");
        System.out.println("El palillo " + palillo1 + "ha sido liberado.");
        System.out.println("El palillo " + (palillo2) + "ha sido liberado.");
        synchronized (palillos){
            palillos.get(palillo1).setEnUso(false);
            palillos.get(palillo2).setEnUso(false);
            palillos.notifyAll();

        }

    }
}
