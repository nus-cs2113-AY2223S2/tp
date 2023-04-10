package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author lil1n

/**
 * Represents a command that delete statement from the financial report
 */
public class DeleteCommand extends Command {

    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("./logs/DeleteCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log DeleteCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Executes the command and print the relevant output message
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting DeleteCommand.execute()");

        index -= 1;

        int previousStatementCount = savedData.getStatementCount(); // only used for assertion
        assert (index < savedData.getStatementCount() && index >= 0) : "invalid index provided for delete";

        FinancialStatement oldStatement = savedData.getStatement(index);
        savedData.deleteStatement(index);
        assert previousStatementCount - 1 == savedData.getStatementCount() : "statement count mismatch";

        String budgetInfo = savedData.checkUserBudgetLimit(oldStatement.getDate());

        String output = "Done, deleted \"" + oldStatement.getDescription() + "\" from the financial report"
                + budgetInfo;
        logger.log(Level.INFO, "deleted from financial report");

        return new CommandResult(output);
    }
}
