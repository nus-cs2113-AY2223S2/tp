package seedu.pocketpal.frontend.constants;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static final String PATH_HOME = System.getProperty("user.dir");
    public static final Path PATH_LOG_OUTPUT = Paths.get(PATH_HOME, "logs", "pocketpal.txt");
}
