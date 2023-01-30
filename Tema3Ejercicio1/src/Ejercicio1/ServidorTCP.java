package Ejercicio1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try{
                System.out.println("Abriendo conexion");
                ServerSocket socket = new ServerSocket(1500);

                System.out.println("Aceptando conexion");
                Socket socketCliente = socket.accept();

                System.out.println("Abriendo flujos entrada y salida");
                InputStream is = socketCliente.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                OutputStream os = socketCliente.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);

                //Leemos el fichero cuya ruta nos ha enviado el cliente
                System.out.println("Leo el fichero del cliente");
                BufferedReader brf = new BufferedReader(new FileReader(br.readLine()));
                String lectura= brf.readLine();
                do {
                    System.out.println(lectura);
                    bw.write(lectura);
                    bw.newLine();
                    bw.flush();

                    lectura = brf.readLine();
                }while (lectura != null);



                is.close();
                isr.close();
                br.close();

                os.close();
                osw.close();
                bw.close();


                socketCliente.close();
                socket.close();

        } catch (Exception e){

        }
    }
}