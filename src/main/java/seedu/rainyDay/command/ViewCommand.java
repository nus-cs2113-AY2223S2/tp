package seedu.rainyDay.command;

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

        int totalInflow = 0;
        int totalOutflow = 0;
        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
            logger.log(Level.INFO, "starting statement " + i);

            if (financialReport.getStatementDirection(i).equals("in")) {
                totalInflow += financialReport.getStatementValue(i);
            } else {
                totalOutflow += financialReport.getStatementValue(i);
            }
            int index = i + 1;
            String financialStatement = String.format("%d. %s", index,
                    financialReport.getFullStatement(i));
            Ui.printFinancialStatement(financialStatement);

            logger.log(Level.INFO, "passed statement " + i);
        }

        logger.log(Level.INFO, "passed Ui acknowledge view command");

        Ui.printSummary(totalInflow, totalOutflow);

        logger.log(Level.INFO, "passed Ui, exiting method");
    }
}
