package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.modules.Ui;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AddCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddCommand.class.getName());

    private final String description;

    private final String flowDirection;

    private final int value;

    private final String category;

    public AddCommand(String description, String flowDirection, int value, String category) {
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
            FileHandler fileHandler = new FileHandler("AddCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log AddCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e); // todo check if useless
        }
    }

    @Override
    public void execute() {
        setupLogger();
        logger.log(Level.INFO, "starting AddCommand.execute()");

        int totalStatementCount = financialReport.getStatementCount();

        FinancialStatement currentFinancialStatement =
                new FinancialStatement(description, flowDirection, value, category);
        financialReport.addStatement(currentFinancialStatement);

        assert totalStatementCount + 1 == financialReport.getStatementCount() : "statement count mismatch";

        logger.log(Level.INFO, " passed assertion");

        Ui.printAddedFinancialStatement(currentFinancialStatement);

        logger.log(Level.INFO, " passed Ui");

        logger.log(Level.INFO, " end of AddCommand.execute()");
    }
}
