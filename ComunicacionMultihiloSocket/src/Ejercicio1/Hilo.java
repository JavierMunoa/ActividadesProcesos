package Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Hilo extends Thread{
    private DatagramSocket socket;
    private int numeroAleatorio;
    public Hilo(DatagramSocket socket, int numCliente) {
        this.socket = socket;
        this.numeroAleatorio = numCliente;
    }


    @Override
    public void run() {
        super.run();
        gestionarCliente();

    }

    public void gestionarCliente(){
        try{
            DatagramPacket packet = leerMensaje(socket);
            String cadena = new String(packet.getData());
            String mensajeServidor = comprobarNumeroCliente(Integer.parseInt(cadena.trim()));
            enviarMensaje(this.socket,packet,mensajeServidor);
        }catch (IOException e){

        }
    }

    public static void enviarMensaje(DatagramSocket socket, DatagramPacket packetCliente, String mensaje) throws IOException {
        InetAddress direccion = packetCliente.getAddress();
        byte[] buffer = mensaje.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length,direccion,packetCliente.getPort());
        socket.send(packet);
    }

    public static DatagramPacket leerMensaje(DatagramSocket socket) throws  IOException{
        byte[] buffer = new byte[64];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return packet;
    }
    public String comprobarNumeroCliente(int numeroUsuario){
        String estadoBusqueda;
        if (numeroUsuario < numeroAleatorio){
            estadoBusqueda = "El número es mayor";
        } else if (numeroUsuario > this.numeroAleatorio) {
            estadoBusqueda = "El número es menor";
        }else{
            estadoBusqueda = "Número encontrado";
        }
        return estadoBusqueda;
    }
}
