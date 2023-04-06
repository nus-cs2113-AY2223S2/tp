package seedu.apollo.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public interface LoggerInterface {
    default void setUpLogger(Logger logger) {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler logConsole = new ConsoleHandler();
        logConsole.setLevel(Level.SEVERE);
        logger.addHandler(logConsole);
        try {

            if (!new File("apollo.log").exists()) {
                assert (new File("apollo.log").createNewFile()) : "Error creating logger";
            }

            FileHandler logFile = new FileHandler("apollo.log", true);
            logFile.setLevel(Level.FINE);
            logger.addHandler(logFile);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
