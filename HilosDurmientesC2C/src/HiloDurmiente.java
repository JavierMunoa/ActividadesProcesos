public class HiloDurmiente extends Thread{

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("Soy el bucle :" + getName() +" y estoy trabajando");
                Thread.sleep(1000,100000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
