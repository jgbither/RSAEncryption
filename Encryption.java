import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.*;
import javax.crypto.Cipher;

public class Encryption {

    public static void main(String[] args) throws Exception{
        //Create key pairs
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        Scanner scan = new Scanner(System.in);

        KeyPair keyPair = keyGen.genKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("Please enter something you wish to be encrypted");
        String text = scan.nextLine();

        byte[] data = Encrypt(text, publicKey);

        System.out.println("Encrypted text: " + new String(data));

        text = Decrypt(data, privateKey);

        System.out.println("Decrypted text: " + text);
    }

    private static byte[] Encrypt(String message, PublicKey publicKey) throws Exception{

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(message.getBytes());
    }

    private static String Decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception{

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(cipher.doFinal(cipherText));
    }
}
