package Ejercicio1;

import java.io.IOException;
import java.net.*;

public class ServidorUDP {
    private static int NUMERO_ALEATORIO_SERVIDOR;
    public static void main(String[] args) {
        try{
            NUMERO_ALEATORIO_SERVIDOR = (int)(Math.random()*100);
            System.out.println("Número creado:" + NUMERO_ALEATORIO_SERVIDOR);


            DatagramSocket socket = new DatagramSocket(41500);

            System.out.println("Creación del array de bytes");




            while (true){
                Hilo hilo = new Hilo(socket,NUMERO_ALEATORIO_SERVIDOR);
                hilo.start();
            }


        }catch (SocketException e){
            System.err.println("Error al crear el Socket");
        }catch (IOException e){
            System.err.println("Error de entrada/salida");
        }
    }





}
