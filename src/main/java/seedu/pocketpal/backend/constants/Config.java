package seedu.pocketpal.backend.constants;

import java.nio.file.Paths;

public class Config {
    public static final String DELIMITER = ",";
    public static final String TEST_PATH_STRING = "./test/storage.txt";
    private static final String PATH_HOME = System.getProperty("user.dir");
    public static final String RELATIVE_FILE_NAME = Paths.get(PATH_HOME, "data", "storage.txt").toString();
}
