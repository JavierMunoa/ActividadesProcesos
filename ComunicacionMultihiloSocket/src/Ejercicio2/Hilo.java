package Ejercicio2;

import java.io.*;
import java.net.Socket;

public class Hilo extends Thread{
    public Hilo(Socket socket) {
        this.socket = socket;
    }

    private Socket socket;

    @Override
    public void run() {
        super.run();
        gestionarCliente();
    }

    public void gestionarCliente(){
        try {
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        //Leemos el fichero cuya ruta nos ha enviado el cliente
        String mensajeCliente = br.readLine().trim();
        System.out.println("Leo el fichero del cliente");
        BufferedReader brf = new BufferedReader(new FileReader("src/Ejercicio2/Direcciones.txt"));
        String ipDireccion = "No se ha podido encontrar la dirección ip";
        String lecturaFichero= brf.readLine();
            String direccionCompletaFichero[];
        do {
             direccionCompletaFichero = lecturaFichero.split(":");
            if (direccionCompletaFichero[0].equals(mensajeCliente)){
                ipDireccion = "La dirección ip es: " + direccionCompletaFichero[1];
            }
            lecturaFichero = brf.readLine();
        }while (lecturaFichero != null && !ipDireccion.equals(direccionCompletaFichero[1]));


        bw.write(ipDireccion);
        bw.newLine();
        bw.flush();

        is.close();
        isr.close();
        br.close();

        os.close();
        osw.close();
        bw.close();


        socket.close();
        socket.close();

    }catch (IOException e){
            System.err.println("Ha ocurrido un erro de entrada/salida");
    }
    }
}
