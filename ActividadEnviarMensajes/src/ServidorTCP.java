import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try{
            //1 - Creación del servidor
            ServerSocket socketServidor = new ServerSocket(32300);

            //2 - Espera y acepta las conexiones
            Socket socketCliente = socketServidor.accept();

            //3 - Flujos de entrada y salida
            InputStream is = socketCliente.getInputStream();
            OutputStream os = socketCliente.getOutputStream();

            //4 - Intercambiar datos con el cliente
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Mensaje enviado por el cliente: " + br.readLine());

            //Enviar mensaje al cliente
            System.out.println("Servidor envía al cliente un mensaje");
            OutputStreamWriter osw = new OutputStreamWriter(os,"UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("Soy el servidor. Este mensaje es para el cliente");
            bw.newLine();
            bw.flush();

            //5 - Cerrar flujos de lectura y escritura
            br.close();
            isr.close();
            is.close();
            os.close();
            osw.close();
            bw.close();

            //6 - Cerrar la conexión
            socketCliente.close();
            socketServidor.close();

        }catch (IOException e){
            System.err.println("Error al iniciar en el puerto 50000");
            e.printStackTrace();
        }
    }
}
