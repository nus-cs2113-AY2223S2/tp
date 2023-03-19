package seedu.rainyDay.modules;

import seedu.rainyDay.data.FinancialStatement;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ui {
    private static final String WELCOME_MESSAGE = "" +
            "Hello from rainyDay!\n" +
            "        __.|.__\n" +
            "    .-\"'..':`..`\"-.\n" +
            "  .' .' .  :  . `. `.\n" +
            " / .   .   :   .   . \\\n" +
            "/_ _._ _.._:_.._ _._ _\\\n" +
            "  '   '    |    '   '\n" +
            "           |\n" +
            "           |\n" +
            "           |\n" +
            "         `='";
    private static final String NO_FILE_DETECTED = "No valid save file detected. Starting with empty financial data.";
    private static final String FINANCIAL_REPORT_EMPTY = "Your financial report is empty";
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
        System.out.println(WELCOME_MESSAGE);
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
        double statementValue = currentStatement.getValue();
        String statementCategory = currentStatement.getCategory();
        String statementDirection = currentStatement.getFlowSymbol();

        String index = String.format("00000%d", statementIndex);
        index = index.substring(index.length() - 5);
        String value = String.format(" %s$%.2f            ", statementDirection, statementValue);
        value = value.substring(0, 12);
        String name = String.format("%s                              ", statementName);
        name = name.substring(0, 30);
        String category = String.format("%s                ", statementCategory);
        category = category.substring(0, 16);
        System.out.printf("|%s|%s|%s|%s|\n", index, name, value, category);
    }

    public static void printSummary(double inflow, double outflow) {
        assert (inflow != 0 || outflow != 0);
        System.out.print(VIEW_SUMMARY);
        String inflowInformation = String.format("|Inflow: $%.2f", inflow);
        String outflowInformation = String.format("|Outflow: $%.2f", outflow);
        ;
        String remainingValueInformation = String.format("|Remaining value: $%.2f", (inflow - outflow));
        String summary = String.join(System.lineSeparator(), inflowInformation, outflowInformation,
                remainingValueInformation);
        System.out.println(summary);
    }

    public void sayFarewellToUser(String username) {
        System.out.println("Bye " + username);
    }

    public void showToUser(String message) {
        out.println(message);
    }
}
