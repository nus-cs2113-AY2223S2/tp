package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.modules.Storage;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class IgnoreCommand extends Command {
    private static final Logger logger = Logger.getLogger(IgnoreCommand.class.getName());
    private int index;
    private String command;


    public IgnoreCommand(int index, String command) {
        this.index = index;
        this.command = command;
    }

    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("AddCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log AddCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting IgnoreCommand.execute()");
        String output;
        index -= 1;

        FinancialStatement currentStatement = financialReport.getFinancialStatement(index);
        int previousStatementCount = financialReport.getStatementCount();
        assert (index < financialReport.getStatementCount() && index >= 0) : "invalid index provided for ignore";
        if (this.command.equalsIgnoreCase("unignore") && currentStatement.isIgnored()) {
            currentStatement.setIgnore(false);
            output = "Done, Entry " + (index + 1) + " included in overview calculations";
            financialReport.addToMonthlyExpenditure(currentStatement);
            logger.log(Level.INFO, "Ignore status updated in financial report");
            Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
        } else if (this.command.equalsIgnoreCase("unignore") && !currentStatement.isIgnored()) {
            output = "Entry " + (index + 1) + " already included in overview calculations";
        } else if (this.command.equalsIgnoreCase("ignore") && !currentStatement.isIgnored()) {
            currentStatement.setIgnore(true);
            output = "Done, Entry " + (index + 1) + " ignored from overview calculations";
            financialReport.removeFromMonthlyExpenditure(currentStatement);
            logger.log(Level.INFO, "Ignore status updated in financial report");
            Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
        } else {
            output = "Entry " + (index + 1) + " is already ignored from overview calculations";
        }

        assert previousStatementCount == financialReport.getStatementCount() : "statement count mismatch";
        return new CommandResult(output);
    }
}
