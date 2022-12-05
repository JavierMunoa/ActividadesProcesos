import java.util.concurrent.Semaphore;

public class Carniceria extends Thread{
    private static final Semaphore dependientesCarniceria = new Semaphore(4);
    private static final Semaphore dependientesCharcuteria = new Semaphore(2);
    private boolean atentidoCarniceria = false;
    private boolean atentidoCharcuteria = false;
    private boolean atendido = false;


    @Override
    public void run(){
        do {
            if (!atentidoCarniceria && dependientesCarniceria.availablePermits() > 0){
                entrarEnCarniceria();
            }
            if (!atentidoCharcuteria  && dependientesCharcuteria.availablePermits() > 0){
                entrarEnCharcuteria();
            }
            if (atentidoCharcuteria & atentidoCarniceria ){
                atendido = true;
            }

        }while (!atendido);
        System.out.println("El proceso " + getName() + " ha terminado.");
    }

    private void entrarEnCarniceria() {
        try {
            dependientesCarniceria.acquire(1);
            System.out.println("El proceso: " + this.getName()+ " está siendo atendido en la carnicería.");
            Thread.sleep(10000);
            System.out.println("El proceso: " + this.getName()+ " ha terminado en la carnicería.");
            dependientesCarniceria.release(1);
            atentidoCarniceria = true;
        }catch (InterruptedException e){
            System.out.println("Ha ocurrido un error:");
            e.printStackTrace();
        }
    }


    private void entrarEnCharcuteria() {
        try {
            dependientesCharcuteria.acquire(1);
            System.out.println("El proceso: " + this.getName()+ " está siendo atendido en la charcutería.");
            Thread.sleep(10000);
            System.out.println("El proceso: " + this.getName()+ " ha terminado en la charcutería.");
            dependientesCharcuteria.release(1);
            atentidoCharcuteria = true;
        }catch (InterruptedException e){
            System.out.println("Ha ocurrido un error:");
            e.printStackTrace();
        }
    }
}
