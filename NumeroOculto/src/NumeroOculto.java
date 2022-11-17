
public class NumeroOculto extends Thread{
    public int numero = 0;

    @Override
    public void run() {
        /*Hacemos que todos los hilos se paren durante dos segundos para evitar que casi siempre numerOculto sea
         encontrado por el hilo1.*/
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Ha ocurrido una excepción:");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //El bucle se repetirá hasta que uno de los hilos encuentre numeroOculto y el booleano del main cambie.
        while(!Main.encontrado){
            try {
                numero = (int)(Math.random()*101);
                if (propuestaNumero(numero) == -1){
                    currentThread().interrupt();
                    System.out.println("Numero encontrado por otro proceso");
                }
            }catch (Exception e){
                System.out.println("Proceso terminado");
            }
        }
    }

    /**
     * Recibimos un número aleatorio entre 0 y 100 y según tenga el mismo valor que el número oculto devolverá uno de
     * tres valores.
     * -1: el número ha sido encontrado por otro hilo.
     * 0: ningún hilo ha encontrado el numeroOculto.
     * 1: ha encontrado el númerOculto.
     * Precondición: ninguna
     * Poscondición: ninguna
     * @param num
     * @return
     */
    synchronized public static int propuestaNumero(int num){
        int resultadoBusqueda = 0;
        if(num == Main.numeroOculto && !Main.encontrado){
            resultadoBusqueda = 1;
            /*Seteamos el booleano del main a true para que el resto de hilos notifiquen que el número ha sido
            encontrado por otro hilo.*/
            Main.encontrado = true;
            System.out.println(currentThread().getName() + " ha encontrado el número oculto.");
        }else if(Main.encontrado){
            resultadoBusqueda = -1;
        }
        return resultadoBusqueda;
    }
}
