import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {
        try(BufferedWriter br = new BufferedWriter(new FileWriter("cosa.txt"))){
            DatagramSocket socket = new DatagramSocket(41500);
            DatagramPacket packet = leerMensaje(socket);
            String cadena = new String(packet.getData()).trim();
            do{
                System.out.println("Mensaje " + cadena);
                br.write(cadena+ "\n");
                packet = leerMensaje(socket);
                cadena = new String(packet.getData() ).trim();
            }
            while (!cadena.equals("FIN"));

        }catch (SocketException e){
            System.err.println("Error al crear el Socket");
        }catch (IOException e){
            System.err.println("Error de entrada/salida");
        }
    }
    public static DatagramPacket leerMensaje(DatagramSocket socket) throws  IOException{
        byte[] buffer = new byte[64];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return packet;
    }



}