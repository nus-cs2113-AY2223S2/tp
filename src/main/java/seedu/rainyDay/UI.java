package seedu.rainyDay;

import seedu.rainyDay.data.FinancialStatement;

public class UI {

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

    public static void displayHelp() {
        System.out.println("Have you tried reading the UG?");
    }

    public static void unrecognisedInput() {
        System.out.println("sorry! I do not understand your input!");
        System.out.println("Please refer to the help table!");
    }

    public static void sayFarewellToUser() {
        System.out.println("Bye " + username);
    }
}
