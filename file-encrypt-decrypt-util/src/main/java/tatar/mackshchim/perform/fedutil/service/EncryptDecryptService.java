package tatar.mackshchim.perform.fedutil.service;

import tatar.mackshchim.perform.fedutil.exceptions.CryptoException;

import java.io.File;

public interface EncryptDecryptService {

    void encryptFile(File file) throws CryptoException;

    void decryptFile(File encryptedFile) throws CryptoException;

}
