package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.modules.Ui;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ViewCommand extends Command {

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

    @Override
    public void execute() {
        setupLogger();
        logger.log(Level.INFO, "starting ViewCommand.execute()");

        if (financialReport.getStatementCount() == 0) {
            assert financialReport.getStatementCount() == 0 : "statement count mismatch";

            logger.log(Level.INFO, "empty financial report");

            Ui.emptyFinancialReport();

            logger.log(Level.INFO, "passed Ui, exiting method");
            return;
        }

        assert financialReport.getStatementCount() != 0 : "statement count mismatch";

        Ui.acknowledgeViewCommand();

        logger.log(Level.INFO, "passed Ui acknowledge view command");

        double totalInflow = 0;
        double totalOutflow = 0;
        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
            logger.log(Level.INFO, "starting statement " + i);
            FinancialStatement currentStatement = financialReport.getFinancialStatement(i);
            if (currentStatement.getFlowDirectionWord().equals("in")) {
                totalInflow += currentStatement.getValue();
            } else {
                totalOutflow += currentStatement.getValue();
            }
            Ui.printFinancialStatement(i + 1, currentStatement);
            logger.log(Level.INFO, "passed statement " + i);
        }

        logger.log(Level.INFO, "passed Ui acknowledge view command");

        Ui.printSummary(totalInflow, totalOutflow);

        logger.log(Level.INFO, "passed Ui, exiting method");
    }
}
