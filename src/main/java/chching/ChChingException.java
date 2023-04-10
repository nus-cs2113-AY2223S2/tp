package chching;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Models a class for exception thrown
 */
public class ChChingException extends Exception {
    /**
     * Program Logging
     */
    private static final Logger logger = Logger.getLogger(ChChing.class.getName());

    static {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            new File("data/ExceptionLog.log").createNewFile();
            FileHandler fileHandler = new FileHandler("data/ExceptionLog.log");
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    private String message;

    public ChChingException(String message) {
        super(message);
        this.message = message;
    }
}

