import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Emisor {
    private static final String FICHERO_CLAVE_PUBLICA = "public_key_emisor.key";
    private static final String FICHERO_CLAVE_PRIVADA = "private_key_emisor.key";

    public static void main(String[] args) {
        KeyPair claves = generarClaves();
        guardarClaves(claves);
    }

    /**
     * Generamos las claves con el algoritmo RSA.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @return
     */
    public static KeyPair generarClaves() {
        KeyPairGenerator generador;
        KeyPair claves = null;
        try {
            generador = KeyPairGenerator.getInstance("RSA");
            generador.initialize(2048);
            claves = generador.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        }
        return claves;
    }

    /**
     * Guardamos las claves que recibimos por parámetro en sus respectivos ficheros.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @param claves
     */
    public static void guardarClaves(KeyPair claves) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(FICHERO_CLAVE_PUBLICA);
            fos.write(claves.getPublic().getEncoded());
            fos.close();

            fos = new FileOutputStream(FICHERO_CLAVE_PRIVADA);
            fos.write(claves.getPrivate().getEncoded());
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("No se encuentra el fichero.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Se ha producido un error de entrada/salida");
            e.printStackTrace();
        }

    }

    /**
     * Obtenemos la clave pública del fichero correspondiente.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @return
     */
    public static PublicKey getClavePublica() {
        File ficheroClavePublica = new File(FICHERO_CLAVE_PUBLICA);
        PublicKey clavePublica = null;
        try {

            byte[] bytesClavePublica = Files.readAllBytes(ficheroClavePublica.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec;
            publicKeySpec = new X509EncodedKeySpec(bytesClavePublica);
            clavePublica = keyFactory.generatePublic(publicKeySpec);

        } catch (IOException e) {
            System.err.println("Se ha producido un error de entrada/salida");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.err.println("La clave indicada no es válida");
            e.printStackTrace();
        }
        return clavePublica;
    }

    /**
     * Obtenemos la clave privada del fichero correspondiente.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @return
     */
    public static PrivateKey getClavePrivada() {
        File ficheroClavePrivada = new File(FICHERO_CLAVE_PRIVADA);
        PrivateKey clavePrivada = null;
        try {

            byte[] bytesClavePrivada = Files.readAllBytes(ficheroClavePrivada.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytesClavePrivada);
            clavePrivada = keyFactory.generatePrivate(privateKeySpec);

        } catch (IOException e) {
            System.err.println("Se ha producido un error de entrada/salida");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.err.println("La clave indicada no es válida");
            e.printStackTrace();
        }
        return clavePrivada;
    }


}
