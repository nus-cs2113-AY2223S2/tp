package seedu.duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import seedu.duke.commands.Command;
import seedu.duke.constants.UIConstants;
import seedu.duke.constants.StorageConstants;
import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;
import seedu.duke.exceptions.InvalidCategoryException;
import seedu.duke.exceptions.InvalidReadFileException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static final Logger logger = Logger.getLogger(Duke.class.getName());
    private static FileHandler fileHandler;

    public static void main(String[] args) {
        UI ui = new UI();
        Command command = new Command();
        Parser parser = new Parser();
        Storage storage = new Storage();
        List<Entry> savedEntries;

        try {
            setupLogging();
            logger.info("Application started.");
            savedEntries = storage.readFromDatabase();
        } catch (InvalidReadFileException e) {
            ui.printException(e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to perform IO operation.");
            throw new RuntimeException(e);
        } catch (InvalidCategoryException e) {
            throw new RuntimeException(e);
        }

        EntryLog entrylog = new EntryLog(savedEntries);
        Scanner in = new Scanner(System.in);
        ui.printWelcome();
        do {
            ui.printAwaitUserInput();
            String userInput = in.nextLine();
            logger.info("> User entered: " + userInput);
            ui.printLine();
            try {
                command = parser.parseUserInput(userInput);
                logger.info("Executing command: " + command.getClass().getName());
                command.execute(entrylog, ui, storage);
            } catch (Exception e) {
                ui.print(e.getMessage() + UIConstants.NEWLINE);
                ui.printLine();
            }
        } while (!command.getIsExit());

        logger.info("Exiting application");
        ui.printExit();
        exitLogging();
    }

    private static void setupLogging() throws IOException {
        Logger globalLogger = Logger.getLogger("");
        Handler[] handlers = globalLogger.getHandlers();
        SimpleFormatter formatter = new SimpleFormatter();
        try {
            final Path logPath = StorageConstants.PATH_LOG_OUTPUT;
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
            throw e;
        }
        logger.info("Logging setup complete.");
    }

    private static void exitLogging() {
        logger.info("Saving logs.");
        fileHandler.close();
    }
}
