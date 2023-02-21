import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalculaHash {
    /**
     * Obtenemos y devolvemos el array de bytes de la cadena que recibimos por parámetro.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @param constrasena
     * @return
     */
    public static byte[] getDigest(String constrasena){
        byte[] resumen = new byte[0];
        try{
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            algoritmo.reset();
            algoritmo.update(constrasena.getBytes("UTF-8"));
            resumen = algoritmo.digest();
        }catch (NoSuchAlgorithmException e){
            System.err.println("Algoritmo no disponible");
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            System.err.println("Condificación inválida");
            e.printStackTrace();
        }
        return resumen;
    }

    /**
     * Comparamos los dos array de bytes que recibimos por parámetro.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @param array1
     * @param array2
     * @return
     */
    public static boolean compararResumenes( byte[] array1,byte[] array2){
        return MessageDigest.isEqual(array1,array2);
    }
}
