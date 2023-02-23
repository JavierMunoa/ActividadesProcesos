import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.Scanner;

public class clsCifrar {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String contrasena;
        do{
            System.out.print('\n'+ "Introduzca una contraseña de cifrado de 16 bytes:");
            contrasena = sc.nextLine();
        }while (contrasena.length() != 16);
        Key clave = clsAccion.obtenerClave(contrasena);

        String mensaje;
        System.out.print("Introduzca un mensaje a cifrar:");
        mensaje = sc.nextLine();

        String mensajeCifrado = clsAccion.cifrar(mensaje,clave);
        try(BufferedWriter bf = new BufferedWriter(new FileWriter("src/mensajesCifrados.txt",true))){
            bf.write(mensajeCifrado);
            bf.newLine();
        }catch (IOException e){
            System.err.println("Ha ocurrido un error de entrada/salida");
        }
    }
}
