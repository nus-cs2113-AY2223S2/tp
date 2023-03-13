package seedu.rainyDay.modules;

import seedu.rainyDay.data.FinancialStatement;
import java.util.Scanner;

public class UI {

    public static final String WRONG_INPUT_FORMAT = "Wrong input format! Please refer to help for correct user input!";
    public static final String DISPLAY_HELP = "Have you tried reading the UG?";

    private static String username;

    public static void printLogo() {
        System.out.println("Hello from rainyDay");
        System.out.println("What is your name?");
    }

    public static void greetUser(String name) {
        username = name;
        if(name.trim().isEmpty()) {
            System.out.println("Very funny");
            System.exit(0);
        }
        System.out.println("Welcome " + username);
    }

    public static String getUserInput() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine().trim();
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

    public static void sayFarewellToUser() {
        System.out.println("Bye " + username);
    }
}
