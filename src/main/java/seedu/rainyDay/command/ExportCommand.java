package seedu.rainyDay.command;

import seedu.rainyDay.data.UserData;
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
    private static final String CSV_EXPORT_ERROR = "Error exporting to CSV, please close the CSV file if you " +
            "currently have it open.";
    private static final String EMPTY_STATEMENT = "Your financial statements are empty, export to CSV will not be " +
            "performed.";
    private static final String CSV_EXPORT_SUCCESS = "Financial statements successfully saved into CSV.";

    public ExportCommand() {
    }

    /**
     * Executes the export command and returns the result
     *
     * @return CommandResult with the relevant success or error message
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting ExportCommand.execute()");
        UserData userData = allData.getUserData();
        String output;

        if (userData.getStatementCount() == 0) {
            logger.log(Level.INFO, "empty financial report, export aborted.");
            output = EMPTY_STATEMENT;
            return new CommandResult(output);
        }
        assert userData.getStatementCount() > 0 : "Should have at least 1 financial statement to export";

        try {
            Storage.writeToCSV(userData.getFinancialReport());
            output = CSV_EXPORT_SUCCESS;
            logger.log(Level.INFO, "Export to CSV successful");
        } catch (IOException e) {
            output = CSV_EXPORT_ERROR;
            logger.log(Level.INFO, "Error when exporting to CSV");
        }
        return new CommandResult(output);
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("ExportCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ExportCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}

