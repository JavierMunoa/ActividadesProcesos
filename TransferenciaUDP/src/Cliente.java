import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try{
            InetAddress direccion = InetAddress.getLocalHost();

            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket();
            int mensaje = 0;
            while (mensaje <= 10000){
                enviarMensaje(socket,direccion,String.valueOf(mensaje));
                mensaje++;
            }
            enviarMensaje(socket,direccion,"FIN");

        }catch (SocketException e){
            System.err.println("Error al crear el Socket");
        }catch (UnknownHostException w){
            System.err.println("Error con el host");
        }catch (IOException e){
            System.err.println("Error de entrada/salida");
        }
    }

    public static void enviarMensaje(DatagramSocket socket,InetAddress direccion,String mensaje) throws  IOException{
        byte[] buffer = String.valueOf(mensaje).getBytes();
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length,direccion,41500);
        socket.send(packet);
    }

}
