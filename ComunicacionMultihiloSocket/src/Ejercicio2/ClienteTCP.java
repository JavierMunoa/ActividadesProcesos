package Ejercicio2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCP{

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            //1 - Creación del socket tipo cliente
            System.out.println("(Cliente): Creación del socket...");
            Socket socketCliente = new Socket(InetAddress.getLocalHost(),1500);

            //2 - Abrir flujos de lectura y escritura
            OutputStream os = socketCliente.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);



            //3 - Intercambio de datos con el servidor
            System.out.print("(Cliente): Introduzca la ruta de un archivo: ");
            bw.write(sc.next());
            bw.newLine();
            bw.flush();

            InputStream is = socketCliente.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            System.out.println("(Cliente): Lectura del fichero:");

            String lecturaFicheroServidor = br.readLine();
            System.out.println(lecturaFicheroServidor);


            //4 - Cerrar los flujos de lectura y escritura
            System.out.println("(Cliente): Ha terminado la lectura del fichero.");


            is.close();
            isr.close();
            br.close();

            os.close();
            osw.close();
            bw.close();

        }catch (UnknownHostException e){
            System.err.println("Error de host");
            e.printStackTrace();
        }catch (IOException e){
            System.err.println("Error de entrada/salida");
            e.printStackTrace();
        }
    }
}
