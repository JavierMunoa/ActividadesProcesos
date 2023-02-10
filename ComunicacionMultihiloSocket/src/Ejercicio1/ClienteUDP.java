package Ejercicio1;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try{

            InetAddress direccion = InetAddress.getLocalHost();

            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket();
            String respuestaServidor;
            do {
                enviarMensaje(socket,direccion);
                respuestaServidor = leerMensaje(socket);
                System.out.println(respuestaServidor);
            }while (!respuestaServidor.equals("Número encontrado"));




        }catch (SocketException e){
            System.err.println("Error al crear el Socket");
        }catch (UnknownHostException w){
            System.err.println("Error con el host");
        }catch (IOException e){
            System.err.println("Error de entrada/salida");
        }
    }

    public static void enviarMensaje(DatagramSocket socket,InetAddress direccion) throws  IOException{
        System.out.print("Introduzca un número: ");
        int mensaje = sc.nextInt();
        byte[] buffer = String.valueOf(mensaje).getBytes();
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length,direccion,41500);
        socket.send(packet);
    }

    public static String leerMensaje(DatagramSocket socket) throws  IOException{
        byte[] buffer = new byte[64];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new String( packet.getData()).trim();
    }
}
