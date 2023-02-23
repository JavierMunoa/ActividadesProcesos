import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.Scanner;

public class clsDescifrar {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String contrasena;
        do{
            System.out.print('\n'+ "Introduzca una contraseña de cifrado de 16 bytes:");
            contrasena = sc.nextLine();
        }while (contrasena.length() != 16);
        Key clave = clsAccion.obtenerClave(contrasena);
        try(BufferedReader br = new BufferedReader(new FileReader("src/mensajesCifrados.txt"))){
            String lectura = br.readLine();
            do {
                String mensajeDescifrado = clsAccion.descifrar(clave,lectura);
                System.out.println(mensajeDescifrado);
                lectura = br.readLine();
            }while (lectura != null);

        }catch (IOException e){
            System.err.println("Ha ocurrido un error de entrada/salida");
        }
    }
}
