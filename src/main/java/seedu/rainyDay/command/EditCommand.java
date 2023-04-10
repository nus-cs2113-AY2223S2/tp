package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.FlowDirection;
import seedu.rainyDay.data.MonthlyExpenditures;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author ChongQiRong

/**
 * Represents a command that edits a statement from the financial report
 */
public class EditCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditCommand.class.getName());
    private int index;
    private final ArrayList<String> editFlagAndField;

    public EditCommand(int index, ArrayList<String> editFlagAndField) {
        this.index = index;
        this.editFlagAndField = editFlagAndField;
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("./logs/EditCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log EditCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Executes the edit command and returns the result
     *
     * @return CommandResult with the relevant success or error message
     */
    @Override
    public CommandResult execute() { // todo add -date
        setupLogger();
        logger.log(Level.INFO, "starting EditCommand.execute()");

        index -= 1;
        int previousStatementCount = savedData.getStatementCount();
        assert (index < savedData.getStatementCount() && index >= 0) : "invalid index provided for edit";

        if (editFlagAndField.get(0).equalsIgnoreCase("-in") ||
                editFlagAndField.get(0).equalsIgnoreCase("-out")) {
            editFlagAndField.add(editFlagAndField.get(0));
            editFlagAndField.remove(0);
        }

        FinancialStatement editedStatement = savedData.getStatement(index);
        MonthlyExpenditures.removeFromMonthlyExpenditure(editedStatement);
        for (int i = 0; i < editFlagAndField.size(); i += 2) {
            if (editFlagAndField.get(i).equalsIgnoreCase("-d")) {
                editedStatement.setDescription(editFlagAndField.get(i + 1));
            } else if (editFlagAndField.get(i).equalsIgnoreCase("-c")) {
                editedStatement.setCategory(editFlagAndField.get(i + 1));
            } else if (editFlagAndField.get(i).equalsIgnoreCase("-v")) {
                editedStatement.setValue(Double.parseDouble(editFlagAndField.get(i + 1)));
            } else if (editFlagAndField.get(i).equalsIgnoreCase("-date")) {
                editedStatement.setDate(LocalDate.parse(editFlagAndField.get(i + 1),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else if (editFlagAndField.get(i).equalsIgnoreCase("-out")) {
                editedStatement.setFlowDirection(FlowDirection.OUTFLOW);
            } else if (editFlagAndField.get(i).equalsIgnoreCase("-in")) {
                editedStatement.setFlowDirection(FlowDirection.INFLOW);
            }
        }
        MonthlyExpenditures.addToMonthlyExpenditure(editedStatement);

        String output = "Done, edited index " + (index + 1) + " from the financial report";

        assert previousStatementCount == savedData.getStatementCount() : "statement count mismatch";

        logger.log(Level.INFO, "deleted from financial report");

        return new CommandResult(output);
    }
}
