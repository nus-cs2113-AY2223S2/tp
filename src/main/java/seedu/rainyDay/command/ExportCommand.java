package seedu.rainyDay.command;

import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;
import seedu.rainyDay.modules.Storage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author KN-CY

/**
 * Represents a command that exports the financial statements into a CSV file.
 */
public class ExportCommand extends Command {
    private static final Logger logger = Logger.getLogger(ExportCommand.class.getName());

    private static final String CSV_EXPORT_SUCCESS = "Financial statements successfully saved into CSV.";

    public ExportCommand() {
    }

    /**
     * Executes the export command and returns the result
     *
     * @return CommandResult with the relevant success or error message
     */
    @Override
    public CommandResult execute() throws RainyDayException {
        setupLogger();
        logger.log(Level.INFO, "starting ExportCommand.execute()");

        if (savedData.getStatementCount() == 0) {
            logger.log(Level.INFO, "empty financial report, export aborted.");
            throw new RainyDayException(ErrorMessage.CSV_EMPTY_STATEMENT.toString());
        }
        assert savedData.getStatementCount() > 0 : "Should have at least 1 financial statement to export";

        try {
            Storage.writeToCSV(savedData.getFinancialReport());
            logger.log(Level.INFO, "Export to CSV successful");
        } catch (IOException e) {
            logger.log(Level.INFO, "Error when exporting to CSV");
            throw new RainyDayException(ErrorMessage.CSV_EXPORT_ERROR.toString());
        }
        logger.log(Level.INFO, "ExportCommand.execute() successful");

        return new CommandResult(CSV_EXPORT_SUCCESS);
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("./logs/ExportCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ExportCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}

