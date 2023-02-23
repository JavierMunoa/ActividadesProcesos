import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class clsAccion {
    private static final int LONGITUD_BLOQUE = 16;
    private static final String ALGORITMO = "AES/ECB/PKCS5Padding";

    /**
     *
     * @param password
     * @return
     */
    public static Key obtenerClave(String password){
        return new SecretKeySpec(password.getBytes(), 0, LONGITUD_BLOQUE, "AES");
    }

    /**
     * Ciframos el texto asociado a la clave que recibimos
     * PrecondiciónL:ninguna
     * Postcondición: ninguna
     * @param mensaje
     * @param clave
     * @return
     */
    public static String cifrar(String mensaje, Key clave){
        String mensajeCifrado = "";
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            byte[] cipherText = cipher.doFinal(mensaje.getBytes());
            mensajeCifrado = Base64.getEncoder().encodeToString(cipherText);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.err.println("El padding seleccionado no existe");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("La clave utilizada no es válida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("El tamaño del bloque elegido no es correcto");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El padding seleccionado no es correcto");
            e.printStackTrace();
        }
        return mensajeCifrado;
    }

    /**
     * Desciframos el mensaje asocidado a la clave que recibimos.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @param clave
     * @param mensajeCifrado
     * @return
     */
    public static String descifrar(Key clave,String mensajeCifrado){
        String mensajeDescifrado = "";
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, clave);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));
            System.out.println(new String(plainText));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.err.println("El padding seleccionado no existe");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("La clave utilizada no es válida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("El tamaño del bloque elegido no es correcto");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El padding seleccionado no es correcto");
            e.printStackTrace();
        }
        return mensajeDescifrado;
    }
}
