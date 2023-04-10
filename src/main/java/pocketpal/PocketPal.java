package pocketpal;

import pocketpal.backend.Backend;
import pocketpal.frontend.Frontend;
import pocketpal.frontend.constants.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PocketPal {
    private static final Logger logger = Logger.getLogger(PocketPal.class.getName());
    private static FileHandler fileHandler;

    public static void main(String[] args) {
        setupLogging();
        final Backend backend = new Backend();
        final Frontend frontend = new Frontend(backend);
        frontend.start();
        exitLogging();
    }

    private static void setupLogging() {
        Logger globalLogger = Logger.getLogger("");
        Handler[] handlers = globalLogger.getHandlers();
        SimpleFormatter formatter = new SimpleFormatter();
        try {
            final Path logPath = Config.PATH_LOG_OUTPUT;
            // create directory if required
            if (!Files.exists(logPath.getParent())) {
                Files.createDirectory(logPath.getParent());
            }
            // set log output to file
            fileHandler = new FileHandler(logPath.toString());
            globalLogger.addHandler(fileHandler);
            fileHandler.setFormatter(formatter);
            // disable console logging
            Arrays.stream(handlers)
                  .forEach((globalLogger::removeHandler));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Encountered exception during logging setup.", e);
            throw new RuntimeException(e);
        }
        logger.info("Logging setup complete.");
    }

    private static void exitLogging() {
        logger.info("Saving logs.");
        fileHandler.close();
    }
}
