package Ejercicio2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try{
            System.out.println("Abriendo conexion");
            ServerSocket socket = new ServerSocket(1500);

            System.out.println("Aceptando conexion");
            Socket socketCliente ;
            while (true){
                socketCliente = socket.accept();
                Hilo hilo = new Hilo(socketCliente);
                hilo.start();
            }
        }catch (IOException e){
            System.err.println("Ha ocurrido un error de entrada/salida");
        }
    }
}
