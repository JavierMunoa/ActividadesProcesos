public class Estudiantes extends Thread{

private static Libros[] libroTomado = new Libros[9];

    public static void main(String[] args) {
        //Contruimos todos los objetos del array para evitar un NullPointerException
        for (int i = 0; i < libroTomado.length; i++) {
            libroTomado[i] = new Libros();
        }
        for (int i = 0; i < 4; i++) {
            Estudiantes estudiante = new Estudiantes();
            estudiante.setName("estudiante" + (i+1));
            estudiante.start();
        }
    }


    @Override
    public void run() {
        super.run();
        try {
            int libro1 = (int)(Math.random()*9);
            int libro2 = (int)(Math.random()*9);
            //En caso de que haya tomado dos veces el mismo libro hacemos que tome otro
            while (libro2 == libro1){
                libro2 = (int)Math.random()*10;
            }
            synchronized (libroTomado[libro1]){
                synchronized (libroTomado[libro2]){
                    //Hacemos que espere en bucle hasta que el libro que quiera y que esté tomado deje de estarlo
                    while (libroTomado[libro1].isTomado() || libroTomado[libro2].isTomado()){
                        libroTomado[libro1].wait();
                    }
                    libroTomado[libro1].setTomado(true);
                    libroTomado[libro2].setTomado(true);
                    System.out.println("El " + this.getName() + " ha tomado los libros " + (libro1+1) +" y " + (libro2+1) + ".");
                    Thread.sleep((long) (1000*(Math.random()*3+3)));
                    libroTomado[libro1].setTomado(false);
                    libroTomado[libro2].setTomado(false);
                    //Notificamos al resto de hilos que los dos libros han sido liberados
                    libroTomado[libro1].notifyAll();
                    libroTomado[libro2].notifyAll();
                    System.out.println("El estudiante " + this.getName() + " ha devuelto los libros " + (libro1+1) +" y " + (libro2+1) + ".");
                }
            }
        }catch (InterruptedException e){
            System.out.println("Ha ocurrido un error de interrupción");
            e.printStackTrace();
        }
    }
}
