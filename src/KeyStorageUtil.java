import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class KeyStorageUtil {
    public static void savePrivateKey(String base64PrivateKey, String username) throws IOException {
        Files.write(Paths.get(username + "_private.pem"), base64PrivateKey.getBytes());
    }

    public static String loadPrivateKey(String username) throws IOException {
        return Files.readString(Paths.get(username + "_private.pem"));
    }
}
