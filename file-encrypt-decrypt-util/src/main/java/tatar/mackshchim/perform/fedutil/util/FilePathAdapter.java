package tatar.mackshchim.perform.fedutil.util;

public class FilePathAdapter {

    public static String adapt(String filepath) {
        return filepath.replace("/","\\");
    }

}
