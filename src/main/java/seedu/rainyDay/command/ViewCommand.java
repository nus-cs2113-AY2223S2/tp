package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a command to view the financial report
 */
public class ViewCommand extends Command{

    private static final Logger logger = Logger.getLogger(ViewCommand.class.getName());

    private final LocalDate timeLimit;
    private final boolean sortByValue;

    public ViewCommand (LocalDate timeLimit, boolean sortByValue) {
        this.timeLimit = timeLimit;
        this.sortByValue = sortByValue;
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

    private ArrayList<Integer> filterBeforeSpecificDateSorted() {
        Map<Double, Integer> sortedIndexes = new TreeMap<>();
        for(int index = 0; index < financialReport.getStatementCount(); index++) {
            FinancialStatement currentStatement = financialReport.getFinancialStatement(index);
            double statementValue = currentStatement.getValue();
            LocalDate statementDate = currentStatement.getDate();
            if(statementDate.isAfter(timeLimit)) {
                sortedIndexes.put(statementValue, index);
            }
        }
        ArrayList<Integer> filteredIndexes = new ArrayList<>();
        for(Map.Entry<Double, Integer> currentEntry : sortedIndexes.entrySet()) {
            filteredIndexes.add(currentEntry.getValue());
        }
        return filteredIndexes;
    }

    private ArrayList<Integer> filterBeforeSpecificDate() {
        ArrayList<Integer> filteredIndexes = new ArrayList<>();
        for(int index = 0; index < financialReport.getStatementCount(); index++) {
            FinancialStatement currentStatement = financialReport.getFinancialStatement(index);
            LocalDate statementDate = currentStatement.getDate();
            if(statementDate.isAfter(timeLimit)) {
                filteredIndexes.add(index);
            }
        }
        return filteredIndexes;
    }

    /**
     * Executes the command and print the relevant output message
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting ViewCommand.execute()");
        String output = "";
        ArrayList<Integer> validIndexes;
        if(sortByValue) {
            validIndexes = filterBeforeSpecificDateSorted();
        } else {
            validIndexes = filterBeforeSpecificDate();
        }
        if (validIndexes.size() == 0) {
            assert financialReport.getStatementCount() == 0 : "statement count mismatch";
            logger.log(Level.INFO, "empty financial report");
            output = "Your financial report is empty";
            return new CommandResult(output);
        }
        assert financialReport.getStatementCount() != 0 : "statement count mismatch";
        CommandResult result = new CommandResult(output);
        ViewResult.printReport(validIndexes);
        return result;
    }
}
