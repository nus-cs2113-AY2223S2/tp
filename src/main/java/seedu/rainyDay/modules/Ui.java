package seedu.rainyDay.modules;

import seedu.rainyDay.data.FinancialStatement;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ui {
    public static final String NO_FILE_DETECTED = "No valid save file detected. Starting with empty financial data.";
    public static final String FINANCIAL_REPORT_EMPTY = "Your financial report is empty";
    private static final String ACKNOWLEDGE_VIEW_COMMAND = "" +
            "+-----+------------------------------+------------+----------------+\n" +
            "|Here is your full financial report!                               |\n" +
            "+-----+------------------------------+------------+----------------+\n" +
            "|Index|Name                          |Amount      |Category        |";
    private static final String VIEW_SUMMARY = "" +
            "+-----+------------------------------+------------+----------------+\n";

    private static Logger UILogger = Logger.getLogger("UILogger.log");

    private final Scanner in;
    private final PrintStream out; // currently unused, to be implemented

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printLogo() {
        System.out.println("Hello from rainyDay");
    }

    public void greetUser(String username) {
        if (username.trim().isEmpty()) {
            System.out.println("Very funny, you should not have an empty name!");
            System.exit(0);
        }
        System.out.println("Welcome " + username);
    }

    public void noFileExist() {
        System.out.println(NO_FILE_DETECTED);
    }

    public static void emptyFinancialReport() {
        System.out.println(FINANCIAL_REPORT_EMPTY);
    }

    public String readUserName() {
        System.out.println("Welcome new user! What is your name?");
        return in.nextLine().trim();
    }

    public String readUserCommand() {
        String userInput = in.nextLine();
        UILogger.log(Level.INFO, userInput);
        if (userInput.trim().equals("")) {
            emptyInstructionHandling();
            readUserCommand();
        }
        return userInput.trim();
    }

    private void emptyInstructionHandling() {
        System.out.println("How can we help you? :D");
    }

    public static void printAddedFinancialStatement(FinancialStatement currentStatement) {
        String fullStatement = currentStatement.getFullStatement();
        System.out.printf("Done! Added: %s%s", fullStatement, System.lineSeparator());
    }

    public static void printDeletedFinancialStatement(String deletedStatementDescription) {
        System.out.printf("Done, deleted \"%s\" from the financial report%s",
                deletedStatementDescription, System.lineSeparator());
    }

    public static void acknowledgeViewCommand() {
        System.out.println(ACKNOWLEDGE_VIEW_COMMAND);
    }

    public static void printFinancialStatement(int statementIndex, FinancialStatement currentStatement) {
        String statementName = currentStatement.getDescription();
        int statementValue = currentStatement.getValue();
        String statementCategory = "Default";
        String statementDirection = currentStatement.getFlowSymbol();

        String index = String.format("00000%d", statementIndex);
        index = index.substring(index.length()-5);
        String value = String.format(" %s$%d            ", statementDirection, statementValue);
        value = value.substring(0, 12);
        String name = String.format("%s                              ", statementName);
        name = name.substring(0, 30);
        String category = String.format("%s                ",statementCategory);
        category = category.substring(0,16);
        System.out.printf("|%s|%s|%s|%s|\n", index, name, value, category);
    }

    public static void printSummary(int inflow, int outflow) {
        assert (inflow != 0 || outflow != 0);
        System.out.print(VIEW_SUMMARY);
        String inflowInformation = "|Inflow: $" + inflow;
        String outflowInformation = "|Outflow: $" + outflow;
        String remainingValueInformation = "|Remaining value: $" + (inflow - outflow);
        String summary = String.join(System.lineSeparator(), inflowInformation, outflowInformation,
                remainingValueInformation);
        System.out.println(summary);
    }

    public void sayFarewellToUser(String username) {
        System.out.println("Bye " + username);
    }
}
