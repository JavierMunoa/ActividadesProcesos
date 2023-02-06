import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            //1 - Creación del socket tipo cliente
            System.out.println("(Cliente): Creación del socket...");
            InetAddress direccion = InetAddress.getLocalHost();
            Socket socketCliente = new Socket(direccion,50000);

            //2 - Abrir flujos de lectura y escritura
            OutputStream os = socketCliente.getOutputStream();
            InputStream is = socketCliente.getInputStream();

            //3 - Intercambio de datos con el servidor


            //Enviar mensaje al cliente
            System.out.println("Cliente envía un mensaje al servidor");
            OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("Pascual petardo");
            bw.newLine();
            bw.flush();

            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Mensaje recibido por el servidor: " + br.readLine());

            //4 - Cerrar los flujos de lectura y escritura
            System.out.println("(Cliente): Cerramos flujo de lectura y escritura...");
            is.close();
            os.close();
        }catch (UnknownHostException e){
            System.err.println("Error de host");
            e.printStackTrace();
        }catch (IOException e){
            System.err.println("Error al iniciar el puerto 50000");
            e.printStackTrace();
        }

    }
}
