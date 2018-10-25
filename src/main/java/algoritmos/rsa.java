package algoritmos;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class rsa {
    public KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("utf-8"));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), "utf-8");
    }

//    //First generate a public/private key pair
////    KeyPair pair = generateKeyPair();
////
////    //Our secret message
////    String message = "the answer to life the universe and everything";
////
////    //Encrypt the message
////    String cipherText = encrypt(message, pair.getPublic());
////
////    //Now decrypt it
////    String decipheredMessage = decrypt(cipherText, pair.getPrivate());
////
////System.out.println(decipheredMessage);
}
