package seedu.logger;

import seedu.exceptions.LifeTrackerException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFileHandler {

    private static final Logger logger = Logger.getLogger(LogFileHandler.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            logger.setUseParentHandlers(false);
            fileHandler = new FileHandler("myLogFile.log");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warning(message);
    }

    public static void logError(String message) {
        logger.severe(message);
    }
}

