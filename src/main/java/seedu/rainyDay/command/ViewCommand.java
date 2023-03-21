package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Represents a command to view the financial report
 */
public class ViewCommand extends Command implements FormatReport {
    //@@author BenjaminPoh
    private static final String ACKNOWLEDGE_VIEW_COMMAND = "" +
            "+-----+------------------------------+------------+----------------+----------+\n" +
            "|Here is your full financial report!                                          |\n" +
            "+-----+------------------------------+------------+----------------+----------+\n" +
            "|Index|Name                          |Amount      |Category        |Date      |\n";

    private static final String VIEW_SUMMARY = "" +
            "+-----+------------------------------+------------+----------------+----------+\n";

    //@@author lil1n
    private static final Logger logger = Logger.getLogger(ViewCommand.class.getName());

    private double totalInflow = 0;

    private double totalOutflow = 0;

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
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its attribute
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting ViewCommand.execute()");

        String outcome;
        if (financialReport.getStatementCount() == 0) {
            assert financialReport.getStatementCount() == 0 : "statement count mismatch";

            logger.log(Level.INFO, "empty financial report");

            outcome = "Your financial report is empty";

            return new CommandResult(outcome);
        }

        assert financialReport.getStatementCount() != 0 : "statement count mismatch";

        outcome = ACKNOWLEDGE_VIEW_COMMAND;
        outcome = getStatementsOutput(outcome);
        outcome += VIEW_SUMMARY + FormatReport.formatSummary(totalInflow, totalOutflow);

        return new CommandResult(outcome);
    }

    /**
     * Appends financial statements to the end of a string
     *
     * @param outcome string containing the incomplete output to be shown to user
     * @return an incomplete output to be shown to user when provided a view command
     */
    private String getStatementsOutput(String outcome) {
        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
            logger.log(Level.INFO, "starting statement " + i);

            FinancialStatement currentStatement = financialReport.getFinancialStatement(i);
            if (currentStatement.getFlowDirectionWord().equals("in")) {
                totalInflow += currentStatement.getValue();
            } else {
                totalOutflow += currentStatement.getValue();
            }

            outcome += FormatReport.formatFinancialStatement(i + 1, currentStatement);

            logger.log(Level.INFO, "passed statement " + i);
        }
        return outcome;
    }
}
