import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Desencriptar {
    public static void main(String[] args) {
        try {
            // Obtenemos la clave privada del receptor y la pública del emisor
            PrivateKey clavePrivadaReceptor = Receptor.getClavePrivada();
            PublicKey clavePublicaEmisor = Emisor.getClavePublica();

            // Obtenemos el fichero a descifrar y lo pasamos a un array de bytes
            File inputFile = new File("textoCifrado.txt");
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            inputStream.close();

            //Descifraremos los bytes del fichero primero con una clave y posteriormente con la otra en orden
            byte[] archivoDescifrado = DescifrarContenido(inputBytes, clavePrivadaReceptor);
            byte[] archivoDescifradoFinal = DescifrarContenido(archivoDescifrado, clavePublicaEmisor);

            // Almacenamos los bytes descifrado en otro archivo
            File outputFile = new File("textoDescifrado.txt");
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(archivoDescifradoFinal);
            outputStream.close();
            System.out.println("Archivo descifrado y almacenado correctamente");

        } catch (FileNotFoundException e) {
            System.out.println("El algoritmo seleccionado no existe");
        } catch (IOException e) {
            System.out.println("El algoritmo seleccionado no existe");
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
            System.out.println("Error al descifrar");
        }
    }

    /**
     * Desciframos el contenido. Para ello necesitamos obtener el tamaño del contenido, puesto que tendremos que
     * descifrarlo en bloques para no perder bytes en el proceso.
     * Precondición: ninguna
     * Postcondición: ninguna
     * @param contenido
     * @param clave
     * @return
     * @throws Exception
     */
    public static byte[] DescifrarContenido(byte[] contenido, Key clave) throws Exception {
        Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        int tamanoBloque = 0;
        try{
            tamanoBloque = (((RSAPublicKey)clave).getModulus().bitLength() + 7) / 8;
        } catch (ClassCastException e){
            tamanoBloque = (((RSAPrivateKey)clave).getModulus().bitLength() + 7) / 8;
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
