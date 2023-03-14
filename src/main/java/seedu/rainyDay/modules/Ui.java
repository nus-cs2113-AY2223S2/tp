package seedu.rainyDay.modules;

import seedu.rainyDay.data.FinancialStatement;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    public static final String WRONG_INPUT_FORMAT = "Wrong input format! " +
            "Please refer to 'help' for correct user input!";
    public static final String WRONG_ADD_FORMAT = "'add' input not correct! " +
            "Please refer to 'help' for correct user inputs for 'add' commands";
    public static final String WRONG_DELETE_INDEX = "Please ensure delete index is a number!";
    public static final String NO_DELETE_INDEX = "Please include a delete index!";
    public static final String DISPLAY_HELP = "Have you tried reading the UG?";
    public static final String NO_FILE_DETECTED = "No valid save file detected. Starting with empty financial data.";
    public static final String FINANCIAL_REPORT_EMPTY = "Your financial report is empty";

    private static String username;
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
        System.out.println("What is your name?");
    }

    public void greetUser(String name) {
        username = name;
        if (name.trim().isEmpty()) {
            System.out.println("Very funny");
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

    public static void printFinancialStatement(String financialStatement) {
        System.out.println(financialStatement);
    }

    public String readUserName() {
        String userInput = in.nextLine().trim();
        return userInput;
    }

    public String readUserCommand() {
        String userInput = in.nextLine().trim();
        while (userInput.trim().isEmpty()) {
            userInput = in.nextLine().trim();
        }
        return userInput;
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
        System.out.println("Here is your full financial report!");
    }

    public static void printSummary(int inflow, int outflow) {
        System.out.print(System.lineSeparator());
        String inflowInformation = "Inflow: $" + inflow;
        String outflowInformation = "Outflow: $" + outflow;
        String remainingValueInformation = "Remaining value: $" + (inflow - outflow);
        String summary = String.join(System.lineSeparator(), inflowInformation, outflowInformation,
                remainingValueInformation);
        System.out.println(summary);
    }


    public static void wrongInputFormat() {
        System.out.println(WRONG_INPUT_FORMAT);
    }

    public static void displayHelp() {
        System.out.println(DISPLAY_HELP);
    }

    public static void unrecognisedInput() {
        System.out.println("sorry! I do not understand your input!");
        System.out.println("Please refer to the help table!");
    }

    public void sayFarewellToUser() {
        System.out.println("Bye " + username);
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
