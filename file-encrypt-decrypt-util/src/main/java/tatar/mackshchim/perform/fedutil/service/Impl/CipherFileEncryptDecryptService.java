package tatar.mackshchim.perform.fedutil.service.Impl;

import tatar.mackshchim.perform.fedutil.exceptions.CryptoException;
import tatar.mackshchim.perform.fedutil.service.EncryptDecryptService;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class CipherFileEncryptDecryptService implements EncryptDecryptService {

    private static final byte[] SECRET_KEY = "1234567890qwerty".getBytes();

    private final Cipher cipher;

    public CipherFileEncryptDecryptService(String transformation) throws NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(transformation);
    }

    @Override
    public void encryptFile(File file) throws CryptoException {
        doCryptFile(Cipher.ENCRYPT_MODE, file);
    }

    @Override
    public void decryptFile(File encryptedFile) throws CryptoException {
        doCryptFile(Cipher.DECRYPT_MODE, encryptedFile);
    }

    private void doCryptFile(int cipherMode, File file) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(SECRET_KEY, cipher.getAlgorithm());
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(file);
            byte[] inputBytes = inputStream.readAllBytes();


            byte[] outputBytes = cipher.doFinal(inputBytes);
            File outputFile = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf("."))
                    + "-crypt" + file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(".")));
            outputFile.createNewFile();


            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
            inputStream.close();
            outputStream.close();
        } catch (InvalidKeyException | BadPaddingException
                 | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }
}
