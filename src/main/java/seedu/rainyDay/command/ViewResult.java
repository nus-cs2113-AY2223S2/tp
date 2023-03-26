package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static seedu.rainyDay.RainyDay.userData;

//@@author BenjaminPoh
public class ViewResult {
    private static final String ACKNOWLEDGE_VIEW_COMMAND = "" +
            "|Here is your financial report!                                                                   |\n";
    private static final String ACKNOWLEDGE_FILTER_COMMAND = "" +
            "|Here is your filtered financial report!                                                          |\n";
    private static final String TABLE_FORMAT = "" +
            "+-----+---------------------------------------------+------------+---------------------+----------+\n" +
            "|Index|Description                                  |Amount      |Category             |Date      |\n";
    private static final String TABLE_BORDER = "" +
            "+-----+---------------------------------------------+------------+---------------------+----------+\n";

    private static final Logger logger = Logger.getLogger(ViewResult.class.getName());

    /**
     * Sets up logger for logging
     */
    protected void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("ViewResult.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("unable to log ViewResult class");
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Used to format the information shown to the user such that it fits the table.
     *
     * @param statementIndex   1-based indexing to be shown to the user
     * @param currentStatement the FinancialStatement
     * @return A formatted string
     */
    private static String formatFinancialStatement(int statementIndex, FinancialStatement currentStatement) {
        String statementOutput;
        String statementName = currentStatement.getDescription();
        double statementValue = currentStatement.getValue();
        String statementCategory = currentStatement.getCategory();
        String statementDirection = currentStatement.getFlowSymbol();
        String date;
        if (currentStatement.getDate() == null) {
            date = "no date   ";
        } else {
            date = currentStatement.getDate().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        }
        String index = String.format("00000%d", statementIndex);
        index = index.substring(index.length() - 5);
        String value = String.format(" %s$%.2f            ", statementDirection, statementValue);
        value = value.substring(0, 12);
        String name = String.format("%s                                             ", statementName);
        name = name.substring(0, 45);
        String category = String.format("%s                     ", statementCategory);
        category = category.substring(0, 21);
        statementOutput = "|" + index + "|" + name + "|" + value + "|" + category + "|" + date + "|"
                + System.lineSeparator();
        return statementOutput;
    }

    private static String formatSummary(double inflow, double outflow) {
        assert (inflow != 0 || outflow != 0);
        String inflowInformation = String.format("|Total Inflow: $%.2f", inflow);
        String outflowInformation = String.format("|Total Outflow: $%.2f", outflow);

        String remainingValueInformation = String.format("|Remaining value: $%.2f\n", (inflow - outflow));
        return String.join(System.lineSeparator(), inflowInformation, outflowInformation,
                remainingValueInformation);
    }

    /**
     * Prints all statements specified by an ArrayList to the user.
     * Note that indices are 1-based in input, while it is 0-based in financialStatement
     *
     * @param indexArray ArrayList of Integers with the indices of the entries to print from financialReport
     */
    public static void printItemsInList(ArrayList<Integer> indexArray) {
        String output;

        System.out.print(TABLE_BORDER);
        System.out.print(ACKNOWLEDGE_FILTER_COMMAND);
        System.out.print(TABLE_FORMAT);

        for (int index : indexArray) {
            logger.log(Level.INFO, "starting statement " + index);
            FinancialStatement currentStatement = userData.getFinancialReport().getFinancialStatement(index - 1);
            output = formatFinancialStatement(index, currentStatement);

            System.out.print(output);
            logger.log(Level.INFO, "passed statement " + index);
        }

        System.out.print(TABLE_BORDER);
    }

    /**
     * Prints all statements specified by an ArrayList to the user.
     * Additional information, including the user's total inflow and outflow are also provided
     * Note that indices are 1-based in input, while it is 0-based in financialStatement
     *
     * @param validIndexes ArrayList of Integers with the indices of the entries to print from financialReport
     */
    public static void printReport(ArrayList<Integer> validIndexes) {
        double totalInflow = 0;
        double totalOutflow = 0;

        System.out.print(TABLE_BORDER);
        System.out.print(ACKNOWLEDGE_VIEW_COMMAND);
        System.out.print(TABLE_FORMAT);
        for (Integer index : validIndexes) {
            logger.log(Level.INFO, "starting statement " + index);
            FinancialStatement currentStatement = userData.getFinancialReport().getFinancialStatement(index);
            if (currentStatement.getFlowDirectionWord().equals("in")) {
                totalInflow += currentStatement.getValue();
            } else {
                totalOutflow += currentStatement.getValue();
            }
            String formattedOutput = formatFinancialStatement(index + 1, currentStatement);
            System.out.print(formattedOutput);
            logger.log(Level.INFO, "passed statement " + index);
        }
        System.out.print(TABLE_BORDER);
        System.out.print(formatSummary(totalInflow, totalOutflow));
    }
}

