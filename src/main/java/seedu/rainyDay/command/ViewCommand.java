package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//@@author BenjaminPoh

/**
 * Represents a command to view the financial report
 */
public class ViewCommand extends Command {
    private static final Logger logger = Logger.getLogger(ViewCommand.class.getName());
    private final LocalDate lowerLimit;
    private final LocalDate upperLimit;
    private final boolean sortingRequired;
    private final boolean viewAll;

    public ViewCommand(LocalDate startTime, LocalDate endTime, boolean sortingRequired, boolean viewAll) {
        this.lowerLimit = startTime;
        this.upperLimit = endTime;
        this.sortingRequired = sortingRequired;
        this.viewAll = viewAll;
    }

    /**
     * Sets up logger for logging
     */
    @Override
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("./logs/ViewCommand.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ViewCommand class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Helper function used to check if entries in financialReport are before a specific date
     * Indexes of entries with a date equal or after the specific date are stored
     * The lower bound of the date is specified in timeLimit
     * Sorting is done if required
     *
     * @return ArrayList of indexes which passed the check
     */
    private ArrayList<Integer> filterIndexes() {
        ArrayList<Integer> filteredIndexes = new ArrayList<>();
        for (int index = 0; index < savedData.getStatementCount(); index++) {
            FinancialStatement currentStatement = savedData.getStatement(index);
            LocalDate statementDate = currentStatement.getDate();
            if (!statementDate.isBefore(lowerLimit) && !statementDate.isAfter(upperLimit)) {
                filteredIndexes.add(index);
            }
        }
        return filteredIndexes;
    }

    /**
     * Helper function used to sort the indexes
     * Sorts in non-decreasing order of absolute value, with inflows always prioritised over outflows
     */
    static class sortByValue implements Comparator<Integer> {
        private FinancialReport report;

        public void setReport(FinancialReport report) {
            this.report = report;
        }

        public int compare(Integer firstIndex, Integer secondIndex) {
            FinancialStatement firstStatement = report.getFinancialStatement(firstIndex);
            FinancialStatement secondStatement = report.getFinancialStatement(secondIndex);
            if (firstStatement.getFlowSymbol().equals("+") && secondStatement.getFlowSymbol().equals("-")) {
                return -1;
            }
            if (firstStatement.getFlowSymbol().equals("-") && secondStatement.getFlowSymbol().equals("+")) {
                return 1;
            }
            return (int) ((firstStatement.getValue() * 100) - (secondStatement.getValue() * 100));
        }
    }
    /**
     * Helper function used to sort the indexes
     * Sorts in ascending orders of date first, breaking ties by ascending orders of index.
     */
    static class sortByDate implements Comparator<Integer> {
        private FinancialReport report;

        public void setReport(FinancialReport report) {
            this.report = report;
        }

        public int compare(Integer firstIndex, Integer secondIndex) {
            FinancialStatement firstStatement = report.getFinancialStatement(firstIndex);
            FinancialStatement secondStatement = report.getFinancialStatement(secondIndex);
            LocalDate firstDate = firstStatement.getDate();
            LocalDate secondDate = secondStatement.getDate();
            if(firstDate.isBefore(secondDate)) {
                return -1;
            }
            if (firstDate.isAfter(secondDate)) {
                return 1;
            }
            return (firstIndex - secondIndex);
        }
    }

    /**
     * Executes the command and print the relevant statements by calling ViewResult
     * If there are no valid Indices, an error message to indicate this is printed instead
     */
    @Override
    public CommandResult execute() {
        setupLogger();
        logger.log(Level.INFO, "starting ViewCommand.execute()");
        ArrayList<Integer> validIndexes;
        validIndexes = filterIndexes();
        if (validIndexes.size() == 0) {
            assert savedData.getStatementCount() == 0 : "statement count mismatch";
            logger.log(Level.INFO, "empty financial report");
            String output = String.format("Your financial report is empty for %s till %s", lowerLimit, upperLimit);
            if(viewAll) {
                output = "Your financial report is completely empty!";
            }
            return new CommandResult(output);
        }
        assert savedData.getStatementCount() != 0 : "statement count mismatch";
        if (sortingRequired) {
            sortByValue sortingClass = new sortByValue();
            sortingClass.setReport(savedData.getFinancialReport());
            validIndexes.sort(sortingClass);
        } else {
            sortByDate sortingClass = new sortByDate();
            sortingClass.setReport(savedData.getFinancialReport());
            validIndexes.sort(sortingClass);
        }
        ViewResult.printReport(validIndexes, lowerLimit, upperLimit, sortingRequired, viewAll);
        return new CommandResult("");
    }
}
