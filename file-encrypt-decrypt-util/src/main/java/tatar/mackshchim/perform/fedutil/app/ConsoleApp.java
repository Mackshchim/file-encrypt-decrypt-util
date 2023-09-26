package tatar.mackshchim.perform.fedutil.app;

import tatar.mackshchim.perform.fedutil.exceptions.CryptoException;
import tatar.mackshchim.perform.fedutil.service.EncryptDecryptService;

import java.io.File;
import java.util.Scanner;

import static tatar.mackshchim.perform.fedutil.util.FilePathAdapter.adapt;

public class ConsoleApp {

    private static final String ENCRYPT_COMMAND = "/encrypt";
    private static final String DECRYPT_COMMAND = "/decrypt";
    private static final String HELP_COMMAND = "/help";


    private final EncryptDecryptService service;

    public ConsoleApp(EncryptDecryptService service) {
        this.service = service;
    }

    public void Run() throws CryptoException {

        Scanner in = new Scanner(System.in);

        System.out.println("File encrypt/decrypt util app is started");
        System.out.println("Enter a command to start working (enter \"/help\" to get commands list)");

        String command;
        String password;
        while (true) {
            command = in.nextLine();

            if (command.startsWith(ENCRYPT_COMMAND + " ")) {
                try {
                    service.encryptFile(new File(adapt(command.substring(command.lastIndexOf(" ")+1))));
                    System.out.println("Done");
                } catch (CryptoException ex) {
                    throw new CryptoException("Error encrypting file", ex);
                }
            }

            else if (command.startsWith(DECRYPT_COMMAND + " ")) {
                try {
                    service.decryptFile(new File(command.substring(command.lastIndexOf(" ")+1)));
                    System.out.println("Done");
                } catch (CryptoException ex) {
                    throw new CryptoException("Error decrypting file", ex);
                }
            }

            else if (command.equals(HELP_COMMAND)) {
                System.out.println(ENCRYPT_COMMAND + " *classpath-of-file* - to encrypt file" +
                        "\n" + DECRYPT_COMMAND + " *classpath-of-file* - to decrypt encrypted file" +
                        "\n" + HELP_COMMAND + " - to see all commands list");
            }

            else {
                System.out.println("No actions for command \"" + command + "\". " +
                        "\nEnter \"" + HELP_COMMAND + "\" to find out available commands");
            }
        }

    }


}
