import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class EncryptionService {
    public static String[] generateKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        //żeby używać algorytmu asymetrycznego RSA
        keyGen.initialize(2048);
        //inicjalizacja generatora z zadaną długością

        KeyPair pair = keyGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        //publicKey.getEncoded() zwraca klucz jako surowy bajtowy ciąg binarny
        //Base64.getEncoder().encodeToString(...) koduje binarny ciąg na czytelny tekst
        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        return new String[] { publicKeyBase64, privateKeyBase64 };
    }

    public static void savePrivateKey(String privateKeyBase64, String username) throws IOException {
        Files.write(Paths.get(username + "_private.key"), privateKeyBase64.getBytes());
    }

    public static String loadPrivateKey(String username) throws IOException {
        return Files.readString(Paths.get(username + "_private.key"));
    }
}
