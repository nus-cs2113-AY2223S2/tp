package seedu.duke;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import seedu.duke.commands.Command;
import seedu.duke.constants.StorageConstants;
import seedu.duke.entrylog.EntryLog;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static final Logger logger = Logger.getLogger(Duke.class.getName());
    private static FileHandler fileHandler;

    public static void main(String[] args) {
        setupLogging();
        logger.info("Application started.");
        UI ui = new UI();
        ui.printWelcome();
        EntryLog entrylog = new EntryLog();
        Scanner in = new Scanner(System.in);
        do {
            ui.printAwaitUserInput();
            String userInput = in.nextLine();
            logger.info("> User entered: " + userInput);
            ui.printLine();
            try {
                Command command = new Parser().parseUserInput(userInput);
                command.execute(entrylog);
            } catch (Exception e) {
                ui.print("error");
            }
            // TODO: condition to be replaced when exit command is implemented
        } while (in.hasNextLine());
        logger.info("Exiting application");
        exitLogging();
    }

    private static void setupLogging() {
        Logger globalLogger = Logger.getLogger("");
        Handler[] handlers = globalLogger.getHandlers();
        SimpleFormatter formatter = new SimpleFormatter();
        try {
            // set log output to file
            fileHandler = new FileHandler(StorageConstants.PATH_LOG_OUTPUT);
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
