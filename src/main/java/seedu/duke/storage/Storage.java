package seedu.duke.storage;

import java.io.IOException;
import java.io.File;

public class Storage {
    private static final String dirPath = "data";
    public static void checkFileAccess(String filePath) throws IOException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
    }
}
