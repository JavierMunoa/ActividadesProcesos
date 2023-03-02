import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Encriptar {
    public static void main(String[] args) {
        try {
            // Obtenemos la clave privada del emisor y la publica del destinatario
            PrivateKey clavePrivadaEmisor = Emisor.getClavePrivada();
            PublicKey clavePublicaDestinatario = Receptor.getClavePublica();

            // Obtenemos el fichero a cifrar y lo pasamos a un array de bytes
            File inputFile = new File("textoCifrar.txt");
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            inputStream.close();

            // cifraremos los bytes primero con una clave y posteriormente con otra
            byte[] archivoInicial = cifrarContenido(inputBytes, clavePrivadaEmisor);
            byte[] archivoFinalCifrado = cifrarContenido(archivoInicial, clavePublicaDestinatario);

            // Una vez finalizado el cifrado lo almacenaremos en un nuevo fichero
            File outputFile = new File("textoCifrado.txt");
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(archivoFinalCifrado);
            outputStream.close();
            System.out.println("Cifrado completado");

        } catch (NoSuchAlgorithmException e) {
            System.out.println("El algoritmo seleccionado no existe");
        } catch (NoSuchPaddingException e) {
            System.out.println("No existe el padding seleccionado");
        } catch (InvalidKeyException e) {
            System.out.println("La clave introducida no es válida");
        } catch (IllegalBlockSizeException e) {
            System.out.println("El tamaño del bloque utilizado no es correcto");
        } catch (BadPaddingException e) {
            System.out.println("El padding utilizado es erróneo");
        } catch (Exception e) {
            System.out.println("Error al cifrar el archivo");
        }
    }

    /**
     * Ciframos el contenido. Para ello necesitamos obtener el tamaño del contenido, puesto que lo cifraremos en
     * bloques para no perder bytes en el proceso.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @param contenido
     * @param clave
     * @return
     * @throws Exception
     */
    public static byte[] cifrarContenido(byte[] contenido, Key clave) throws Exception {
        Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        int tamanoBloque = 0;
        try{
            tamanoBloque = (((RSAPublicKey)clave).getModulus().bitLength() + 7) / 8 - 11;
        } catch (ClassCastException e){
            tamanoBloque = (((RSAPrivateKey)clave).getModulus().bitLength() + 7) / 8 - 11;
        }
        ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();
        int offset = 0;
        while (offset < contenido.length) {
            int tamanoBloqueActual = Math.min(tamanoBloque, contenido.length - offset);
            byte[] bloqueCifrado = cifrador.doFinal(contenido, offset, tamanoBloqueActual);
            bufferSalida.write(bloqueCifrado);
            offset += tamanoBloqueActual;
        }
        return bufferSalida.toByteArray();
    }
}
