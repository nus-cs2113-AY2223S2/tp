package seedu.securenus.ui;

import seedu.securenus.messages.OperationMessages;

import java.util.Scanner;

/**
 * Ui class to handle all user interface printing.
 */
public class Ui {
    public static final String DUPLICATE_WHITESPACE_FMT = "\\s+";

    public static Scanner in = new Scanner(System.in);

    /**
     * Greets the user upon start up of the application.
     */
    public static void greetUser() {
        System.out.println(OperationMessages.INITIALISE);
    }

    /**
     * Notifies user if the database is corrupted
     */
    public static void printCorruptedDataMessage() {
        System.out.println("    ____________");
        System.out.println("    | ALERT !! |");
        System.out.println("    ------------");
        System.out.println("Data stored is corrupted. Manual editing from the database.txt is not allowed!");
        System.out.println("1. Enter 'save' or continue this session to discard all previous data   OR");
        System.out.println("2. Press Ctrl + C to exit the application and " +
                "manually revert the data in database.txt back to it's last saved state");
        System.out.println();
    }

    /**
     * Notifies user that information from the previous session has been loaded
     */
    public static void printValidDataMessage() {
        System.out.println("---------------------------------------------------");
        System.out.println("| User data from previous session has been loaded |");
        System.out.println("---------------------------------------------------");
        System.out.println();
    }

    /**
     * Notifies user that a new session has been created
     */
    public static void printNewSessionMessage() {
        System.out.println("-----------------------------");
        System.out.println("| A new session has started |");
        System.out.println("-----------------------------");
        System.out.println();
    }

    /**
     * Prints a horizontal line to separate UI elements.
     */
    public static void printLine() {
        System.out.print("_____________________________________________________\n");
    }

    public static void inform(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Reads the user's command from the console.
     *
     * @return String containing user's command.
     */
    public static String readCommand() {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            return removeExtraWhiteSpaces(line);
        }
        return "";
    }

    public static String readLine() {
        while (in.hasNextLine()) {
            String line = in.nextLine();

            return line;
        }
        return "";
    }

    public static String removeBackSlashes(String input) {
        return input.replaceAll("\\", "\\\\");
    }

    public static String removeExtraWhiteSpaces(String line) {
        // remove duplicate whitespaces
        line = line.replaceAll(DUPLICATE_WHITESPACE_FMT, " ");
        return line.trim(); // remove leading and trailing whitespaces
    }

    public static void informUserToStartCommand() {
        System.out.print("Enter Command:");
    }

    // TODO can this be removed with logging?
    /**
     * Prints error message to console.
     *
     * @param message Error message to be printed.
     */
    public static void printError(String message) {
        System.out.println("Oops! Error encountered: "+ message);
    }
    public static void informOperationCancel () {
        System.out.println(OperationMessages.CANCEL_OPERATION);
    }

    public static void close() {
        in.close();
    }
}
