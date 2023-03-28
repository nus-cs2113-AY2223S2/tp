package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

//@@author BenjaminPoh
/**
 * Represents a command to view the financial report
 */
public class ViewCommand extends Command {

    private static final Logger logger = Logger.getLogger(ViewCommand.class.getName());

    private final LocalDate timeLimit;
    private final boolean sortByValue;

    public ViewCommand(LocalDate timeLimit, boolean sortByValue) {
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

    /**
     * Helper function used to check if entries in financialReport are before a specific date
     * Indexes of entries with a date equal or after the specific date are stored
     * The lower bound of the date is specified in timeLimit
     *
     * @return ArrayList of indexes which passed the check
     */
    private ArrayList<Integer> filterBeforeSpecificDate() {
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
     * Helper function used to check if entries in financialReport are before a specific date
     * Indexes of entries with a date equal or after the specific date are stored
     * The lower bound of the date is specified in timeLimit
     * Similar to filterBeforeSpecificDate(), with the additional benefit of sorting the
     * entries by their absolute value in ascending order, with inflows displayed before outflows
     *
     * @return ArrayList of indexes which passed the check
     */
    private ArrayList<Integer> filterBeforeSpecificDateSorted() {
        Map<Double, LinkedList<Integer>> sortedIndexesInflows = new TreeMap<>();
        Map<Double, LinkedList<Integer>> sortedIndexesOutflows = new TreeMap<>();
        LocalDate today = LocalDate.now();
        for (int index = 0; index < userData.getStatementCount(); index++) {
            FinancialStatement currentStatement = userData.getStatement(index);
            double statementValue = currentStatement.getValue();
            String direction = currentStatement.getFlowSymbol();
            LocalDate statementDate = currentStatement.getDate();
            if (statementDate.isAfter(timeLimit) && !statementDate.isAfter(today) && direction.equals("+")) {
                if (!sortedIndexesInflows.containsKey(statementValue)) {
                    LinkedList<Integer> list = new LinkedList<>();
                    sortedIndexesInflows.put(statementValue, list);
                }
                sortedIndexesInflows.get(statementValue).add(index);
            } else if (statementDate.isAfter(timeLimit) && !statementDate.isAfter(today) && direction.equals("-")) {
                if (!sortedIndexesOutflows.containsKey(statementValue)) {
                    LinkedList<Integer> list = new LinkedList<>();
                    sortedIndexesOutflows.put(statementValue, list);
                }
                sortedIndexesOutflows.get(statementValue).add(index);
            }
        }
        ArrayList<Integer> filteredIndexes = new ArrayList<>();

        for (Map.Entry<Double, LinkedList<Integer>> currentEntry : sortedIndexesInflows.entrySet()) {
            LinkedList<Integer> currentList = currentEntry.getValue();
            while (!currentList.isEmpty()) {
                filteredIndexes.add(currentList.getFirst());
                currentList.removeFirst();
            }
        }
        for (Map.Entry<Double, LinkedList<Integer>> currentEntry : sortedIndexesOutflows.entrySet()) {
            LinkedList<Integer> currentList = currentEntry.getValue();
            while (!currentList.isEmpty()) {
                filteredIndexes.add(currentList.getFirst());
                currentList.removeFirst();
            }
        }
        return filteredIndexes;
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
        if (sortByValue) {
            validIndexes = filterBeforeSpecificDateSorted();
        } else {
            validIndexes = filterBeforeSpecificDate();
        }
        if (validIndexes.size() == 0) {
            assert userData.getStatementCount() == 0 : "statement count mismatch";
            logger.log(Level.INFO, "empty financial report");
            String output = "Your financial report is empty";
            return new CommandResult(output);
        }
        assert userData.getStatementCount() != 0 : "statement count mismatch";
        ViewResult.printReport(validIndexes, timeLimit, sortByValue);
        return null;
    }
}
