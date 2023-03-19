package seedu.rainyDay.command;

import seedu.rainyDay.modules.Ui;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DeleteCommand extends Command {

    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());

    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("DeleteCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log DeleteCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e); // todo check if useless
        }
    }

//    @Override
//    public void execute() {
//        setupLogger();
//        logger.log(Level.INFO, "starting DeleteCommand.execute()");
//
//        index -= 1;
//
//        int previousStatementCount = financialReport.getStatementCount();
//        assert (index < financialReport.getStatementCount() && index >= 0) : "invalid index provided for delete";
//
//        Ui.printDeletedFinancialStatement(financialReport.getStatementDescription(index));
//
//        logger.log(Level.INFO, "passed Ui");
//
//        financialReport.deleteStatement(index);
//
//        assert previousStatementCount - 1 == financialReport.getStatementCount() : "statement count mismatch";
//
//        logger.log(Level.INFO, "deleted from financial report");
//    }

    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting DeleteCommand.execute()");

        index -= 1;

        int previousStatementCount = financialReport.getStatementCount();
        assert (index < financialReport.getStatementCount() && index >= 0) : "invalid index provided for delete";

        String output = "Done, deleted \"" + financialReport.getStatementDescription(index)
                + "\" from the financial report";

        financialReport.deleteStatement(index);

        assert previousStatementCount - 1 == financialReport.getStatementCount() : "statement count mismatch";

        logger.log(Level.INFO, "deleted from financial report");

        return new CommandResult(output);
    }
}
