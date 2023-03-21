package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class EditCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditCommand.class.getName());
    private int index;
    private final String description;
    private final String flowDirection;
    private final double value;
    private final String category;

    public EditCommand(int index, String description, String flowDirection, double value, String category) {
        this.index = index;
        this.description = description;
        this.flowDirection = flowDirection;
        this.value = value;
        this.category = category;
    }

    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("EditCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log EditCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting EditCommand.execute()");

        index -= 1;

        int previousStatementCount = financialReport.getStatementCount();
        assert (index < financialReport.getStatementCount() && index >= 0) : "invalid index provided for edit";

        financialReport.deleteStatement(index);
        financialReport.addStatementAtIndex(new FinancialStatement(description, flowDirection, value, category), index);

        String output = "Done, edited entry " + index
                + " from the financial report";

        assert previousStatementCount == financialReport.getStatementCount() : "statement count mismatch";

        logger.log(Level.INFO, "deleted from financial report");

        return new CommandResult(output);
    }
}
