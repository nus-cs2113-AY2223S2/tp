package seedu.rainyDay.command;

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

    private final LocalDate timeLimit;
    private final boolean sortingRequired;

    public ViewCommand(LocalDate timeLimit, boolean sortingRequired) {
        this.timeLimit = timeLimit;
        this.sortingRequired = sortingRequired;
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
     * Helper function used to check if entries in financialReport are before a specific date
     * Indexes of entries with a date equal or after the specific date are stored
     * The lower bound of the date is specified in timeLimit
     * Sorting is done if required
     *
     * @return ArrayList of indexes which passed the check
     */
    private ArrayList<Integer> filterIndexes () {
        ArrayList<Integer> filteredIndexes = new ArrayList<>();
        for (int index = 0; index < userData.getStatementCount(); index++) {
            FinancialStatement currentStatement = userData.getStatement(index);
            LocalDate statementDate = currentStatement.getDate();
            if (statementDate.isAfter(timeLimit) && !statementDate.isAfter(LocalDate.now())) {
                filteredIndexes.add(index);
            }
        }
        return filteredIndexes;
    }

    /**
     * Helper function used to sort the indexes
     * Sorts in non-decreasing order of absolute value, with inflows always prioritised over outflows
     */
    class sortByValue implements Comparator<Integer> {
        public int compare (Integer firstIndex, Integer secondIndex) {
            FinancialStatement firstStatement = userData.getStatement(firstIndex);
            FinancialStatement secondStatement = userData.getStatement(secondIndex);
            if(firstStatement.getFlowSymbol().equals("+") && secondStatement.getFlowSymbol().equals("-")) {
               return -1;
            }
            if(firstStatement.getFlowSymbol().equals("-") && secondStatement.getFlowSymbol().equals("+")) {
                return 1;
            }
            return (int)((firstStatement.getValue() * 100) - (secondStatement.getValue() *100));
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
        boolean viewAll = timeLimit.equals(LocalDate.of(1800, 1, 1));
        validIndexes = filterIndexes();
        if (validIndexes.size() == 0) {
            assert userData.getStatementCount() == 0 : "statement count mismatch";
            logger.log(Level.INFO, "empty financial report");
            String output = "Your financial report is empty";
            return new CommandResult(output);
        }
        assert userData.getStatementCount() != 0 : "statement count mismatch";
        if(sortingRequired) {
            validIndexes.sort(new sortByValue());
        }
        ViewResult.printReport(validIndexes, timeLimit, sortingRequired, viewAll);
        return null;
    }
}
