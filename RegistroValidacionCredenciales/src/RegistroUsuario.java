import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import java.util.Stack;

public class RegistroUsuario {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String nombre;
        String constrasena;
        //Obtenemos el nombre y la constraseña que el usuario quiere registrar
        System.out.println("Registro");
        System.out.print("Introduzca un nombre: ");
        nombre = sc.next();
        System.out.printf("%nIntroduzca una contraseña: ");
        constrasena = sc.next();

        registrarUsuario(nombre,constrasena);
    }

    /**
     * Registramos en el fichero credenciales.cre los datos que nos ha introducido el usuario. En caso de la contraseña
     * la introduciremos codificada.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @param nombre
     * @param constrasena
     */
    public static void registrarUsuario(String nombre,String constrasena){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/credenciales.cre",true))) {
            byte[] resumenContrasena = CalculaHash.getDigest(constrasena);
            String resumenConvertido = Base64.getEncoder().encodeToString(resumenContrasena);
            bw.write(nombre + ":" + resumenConvertido);
            bw.newLine();
            System.out.println("Registro completado");
        }catch (IOException e){
            System.err.println("Ha ocurrido un error de entrada/salida");
            e.printStackTrace();
        }
    }
}
