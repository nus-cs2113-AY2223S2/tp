package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ViewCommand extends Command implements FormatReport {
    private static final String ACKNOWLEDGE_VIEW_COMMAND = "" +
            "+-----+------------------------------+------------+----------------+\n" +
            "|Here is your full financial report!                               |\n" +
            "+-----+------------------------------+------------+----------------+\n" +
            "|Index|Name                          |Amount      |Category        |\n";
    private static final String VIEW_SUMMARY = "" +
            "+-----+------------------------------+------------+----------------+\n";
    private static final Logger logger = Logger.getLogger(ViewCommand.class.getName());

    public ViewCommand() {
    }

    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("ViewCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ViewCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e); // todo check if useless
        }
    }

//    @Override
//    public void execute() {
//        setupLogger();
//        logger.log(Level.INFO, "starting ViewCommand.execute()");
//
//        if (financialReport.getStatementCount() == 0) {
//            assert financialReport.getStatementCount() == 0 : "statement count mismatch";
//
//            logger.log(Level.INFO, "empty financial report");
//
//            Ui.emptyFinancialReport();
//
//            logger.log(Level.INFO, "passed Ui, exiting method");
//            return;
//        }
//
//        assert financialReport.getStatementCount() != 0 : "statement count mismatch";
//
//        Ui.acknowledgeViewCommand();
//
//        logger.log(Level.INFO, "passed Ui acknowledge view command");
//
//        double totalInflow = 0;
//        double totalOutflow = 0;
//        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
//            logger.log(Level.INFO, "starting statement " + i);
//            FinancialStatement currentStatement = financialReport.getFinancialStatement(i);
//            if (currentStatement.getFlowDirection().equals("in")) {
//                totalInflow += currentStatement.getValue();
//            } else {
//                totalOutflow += currentStatement.getValue();
//            }
//            Ui.printFinancialStatement(i + 1, currentStatement);
//            logger.log(Level.INFO, "passed statement " + i);
//        }
//
//        logger.log(Level.INFO, "passed Ui acknowledge view command");
//
//        Ui.printSummary(totalInflow, totalOutflow);
//
//        logger.log(Level.INFO, "passed Ui, exiting method");
//    }

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

        double totalInflow = 0;
        double totalOutflow = 0;
        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
            logger.log(Level.INFO, "starting statement " + i);

            FinancialStatement currentStatement = financialReport.getFinancialStatement(i);
            if (currentStatement.getFlowDirection().equals("in")) {
                totalInflow += currentStatement.getValue();
            } else {
                totalOutflow += currentStatement.getValue();
            }

            outcome += FormatReport.formatFinancialStatement(i + 1, currentStatement);

            logger.log(Level.INFO, "passed statement " + i);
        }
        outcome += VIEW_SUMMARY;

        logger.log(Level.INFO, "passed Ui acknowledge view command");

        outcome += FormatReport.formatSummary(totalInflow, totalOutflow);

        logger.log(Level.INFO, "passed Ui, exiting method");
        return new CommandResult(outcome);
    }
}
