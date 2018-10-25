package algoritmos;
import herramientas.bytes2hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash {
    public String generateHash256(String input){
        bytes2hex tool = new bytes2hex();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(input.getBytes());
        return tool.bytesToHex(messageDigest.digest());
    }
}
