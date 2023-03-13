package seedu.duke.constants;

import java.nio.file.Paths;

public class StorageConstants {
    private static final String PATH_HOME = System.getProperty("user.dir");
    public static final String RELATIVE_FILE_NAME = Paths.get(PATH_HOME, "data", "storage.txt").toString();
    public static final String PATH_LOG_OUTPUT = Paths.get(PATH_HOME, "logs", "pocketpal.txt").toString();
    public static final String DELIMITER = ",";
}
