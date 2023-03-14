package seedu.pocketpal.constants;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageConstants {
    public static final String DELIMITER = ",";
    private static final String PATH_HOME = System.getProperty("user.dir");
    public static final Path PATH_LOG_OUTPUT = Paths.get(PATH_HOME, "logs", "pocketpal.txt");
    public static final String RELATIVE_FILE_NAME = Paths.get(PATH_HOME, "data", "storage.txt").toString();
}
