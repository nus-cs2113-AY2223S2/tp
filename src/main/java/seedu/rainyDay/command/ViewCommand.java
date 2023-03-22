package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Represents a command to view the financial report
 */
public class ViewCommand extends Command{

    //@@author lil1n
    private static final Logger logger = Logger.getLogger(ViewCommand.class.getName());

    public ViewCommand() {
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("ViewCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ViewCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Executes the command and print the relevant output message
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting ViewCommand.execute()");

        String output;
        if (financialReport.getStatementCount() == 0) {
            assert financialReport.getStatementCount() == 0 : "statement count mismatch";
            logger.log(Level.INFO, "empty financial report");
            output = "Your financial report is empty";
            CommandResult result = new CommandResult(output);
            result.printResult();
            return result;
        }
        assert financialReport.getStatementCount() != 0 : "statement count mismatch";
        output = "Your financial report is not empty";
        CommandResult result = new CommandResult(output);
        ViewResult.printFullReport();
        return result;
    }
}
