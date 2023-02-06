import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            InetAddress direccion = InetAddress.getByName("ciclo.iesnervion.es");
            System.out.println("Dirección IP: " + direccion.getHostAddress());

            byte[] direccionIP = direccion.getAddress();
            System.out.println(Arrays.toString(direccionIP));

            InetAddress local = InetAddress.getLocalHost();
            System.out.println("Dirección IP: " + local.getHostAddress());
        }catch (UnknownHostException e){
            System.err.println("Error: No se encuentra la dirección host.");
            e.printStackTrace();
        }
    }
}