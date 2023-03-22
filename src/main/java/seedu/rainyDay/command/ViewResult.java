package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static seedu.rainyDay.RainyDay.financialReport;

public class ViewResult {
    private static final String ACKNOWLEDGE_VIEW_COMMAND = "" +
            "|Here is your full financial report!                                          |\n";
    private static final String ACKNOWLEDGE_FILTER_COMMAND = "" +
            "|Here is your filtered financial report!                                      |\n";
    private static final String TABLE_FORMAT = "" +
            "+-----+------------------------------+------------+----------------+----------+\n" +
            "|Index|Name                          |Amount      |Category        |Date      |\n";
    private static final String TABLE_BORDER = "" +
            "+-----+------------------------------+------------+----------------+----------+\n";

    private static final Logger logger = Logger.getLogger(ViewResult.class.getName());

    /**
     * Sets up logger for logging
     */
    //@Override
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

    //@@author BenjaminPoh
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
        String name = String.format("%s                              ", statementName);
        name = name.substring(0, 30);
        String category = String.format("%s                ", statementCategory);
        category = category.substring(0, 16);
        statementOutput = "|" + index + "|" + name + "|" + value + "|" + category + "|" + date + "|"
                + System.lineSeparator();
        return statementOutput;
    }

    private static String formatSummary(double inflow, double outflow) {
        assert (inflow != 0 || outflow != 0);
        String inflowInformation = String.format("|Inflow: $%.2f", inflow);
        String outflowInformation = String.format("|Outflow: $%.2f", outflow);

        String remainingValueInformation = String.format("|Remaining value: $%.2f", (inflow - outflow));
        return String.join(System.lineSeparator(), inflowInformation, outflowInformation,
                remainingValueInformation);
    }

    public static void printItemsInList(ArrayList<Integer> indexArray) {
        String output;

        System.out.print(TABLE_BORDER);
        System.out.print(ACKNOWLEDGE_FILTER_COMMAND);
        System.out.print(TABLE_FORMAT);

        for (int index : indexArray) {
            logger.log(Level.INFO, "starting statement " + index);
            FinancialStatement currentStatement = financialReport.getFinancialStatement(index);

            output = formatFinancialStatement(index, currentStatement);
            System.out.print(output);
            logger.log(Level.INFO, "passed statement " + index);
        }
        System.out.print(TABLE_BORDER);
    }

    /**
     * Appends financial statements to the end of a string
     */
    public static void printFullReport() {
        double totalInflow = 0;
        double totalOutflow = 0;

        System.out.print(TABLE_BORDER);
        System.out.print(ACKNOWLEDGE_VIEW_COMMAND);
        System.out.print(TABLE_FORMAT);
        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
            logger.log(Level.INFO, "starting statement " + i);
            FinancialStatement currentStatement = financialReport.getFinancialStatement(i);
            if (currentStatement.getFlowDirectionWord().equals("in")) {
                totalInflow += currentStatement.getValue();
            } else {
                totalOutflow += currentStatement.getValue();
            }
            String formattedOutput = formatFinancialStatement(i + 1, currentStatement);
            System.out.print(formattedOutput);
            logger.log(Level.INFO, "passed statement " + i);
        }
        System.out.print(TABLE_BORDER);
        System.out.print(formatSummary(totalInflow, totalOutflow));
    }
}

