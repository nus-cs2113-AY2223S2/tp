package chching;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Models a class for exception thrown
 */
public class ChChingException extends Exception {
    /**
     * Program Logging
     */
    private final static Logger logger = Logger.getLogger(ChChing.class.getName());
    static {
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }
    private String message;
    public ChChingException(String message) {
        super(message);
        this.message = message;
    }
}

