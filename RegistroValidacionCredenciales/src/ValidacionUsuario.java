import jdk.jshell.execution.LocalExecutionControl;

import java.io.*;
import java.util.Base64;
import java.util.Scanner;

public class ValidacionUsuario {
    private final static Scanner sc = new Scanner(System.in);
    private static boolean usuarioCorrecto;
    private static boolean contraseñaCorrecta;
    public static void main(String[] args) {
        String nombre;
        String constrasena;

        //Recibimos los datos de logeo del usuario
        System.out.println("Registro");
        System.out.print("Introduzca un nombre: ");
        nombre = sc.next();

        System.out.printf("%nIntroduzca una contraseña: ");
        constrasena = sc.next();
        comprobarDatosUsuario(nombre,constrasena);
        comprobarLoginUsuario();
    }

    /**
     * Comprobamos que los datos que ha introducido el usuario son correctos y existen dentro del fichero, modificando
     * de esta manera sus respectivos booleanos según el resultado que den.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @param nombre
     * @param constrasena
     */
    public static void comprobarDatosUsuario(String nombre,String constrasena){
        byte[] resumenContrasena = CalculaHash.getDigest(constrasena);
        try(BufferedReader br = new BufferedReader(new FileReader("src/credenciales.cre"))) {
            String lectura =  br.readLine();
            do {
                String[] datosUsuario  = lectura.split(":");
                if (nombre.equals(datosUsuario[0])){
                    usuarioCorrecto = true;
                    byte[] contrasenaUsuario = Base64.getDecoder().decode(datosUsuario[1]);
                    if (CalculaHash.compararResumenes(resumenContrasena,contrasenaUsuario)){
                        contraseñaCorrecta = true;
                    }
                }
                lectura = br.readLine();
            }while (lectura != null && !usuarioCorrecto);
        }catch (IOException e){
            System.err.println("Ha ocurrido un error de entrada/salida");
            e.printStackTrace();
        }
    }

    /**
     * Comprobamos si el usuario podrá logearse o no y le informamos. Se podrán dar tres casos:
     * 1.El usuario introducido es incorrecto
     * 2.La constraseña introducida es incorrecta
     * 3.Login completado con éxito.
     * Precondión: ninguna
     *Postcondición: ninguna
     */
    public static void comprobarLoginUsuario(){
        if (!usuarioCorrecto){
            System.out.println("El usuario introducido no existe");
        }else {
            if (!contraseñaCorrecta){
                System.out.println("Contraseña incorrecta");
            }else {
                System.out.println("Login completado");
            }
        }
    }
}
