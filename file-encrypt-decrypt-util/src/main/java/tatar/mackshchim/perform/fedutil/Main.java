package tatar.mackshchim.perform.fedutil;

import tatar.mackshchim.perform.fedutil.app.ConsoleApp;
import tatar.mackshchim.perform.fedutil.exceptions.CryptoException;
import tatar.mackshchim.perform.fedutil.service.Impl.CipherFileEncryptDecryptService;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;


public class Main {

    private static final String TRANSFORMATION = "AES";


    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, CryptoException {

        ConsoleApp app = new ConsoleApp(new CipherFileEncryptDecryptService(TRANSFORMATION));
        app.Run();

    }
}