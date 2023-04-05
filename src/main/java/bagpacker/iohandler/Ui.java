package bagpacker.iohandler;

import bagpacker.commands.AddCommand;
import bagpacker.commands.DeleteCommand;
import bagpacker.commands.EditQuantityCommand;
import bagpacker.commands.ListCommand;
import bagpacker.commands.ListUnpackedCommand;
import bagpacker.commands.PackCommand;
import bagpacker.commands.UnpackCommand;
import bagpacker.commands.DeleteListCommand;
import bagpacker.commands.PackAllCommand;
import bagpacker.commands.UnpackAllCommand;
import bagpacker.commands.FindCommand;
import bagpacker.commands.ByeCommand;


/**
 * Ui class contains methods to print messages to user interface on Command Line Interface
 */
public class Ui {
    public static String logo = " ____              _____           _\n"
            + "|  _ \\            |  __ \\         | |\n"
            + "| |_) | __ _  __ _| |__) |_ _  ___| | _____ _ __\n"
            + "|  _ < / _` |/ _` |  ___/ _` |/ __| |/ / _ \\ '__|\n"
            + "| |_) | (_| | (_| | |  | (_| | (__|   <  __/ |\n"
            + "|____/ \\__,_|\\__, |_|   \\__,_|\\___|_|\\_\\___|_|\n"
            + "              __/ |\n"
            + "             |___/\n";
    private static final String RETURN_USER_GREET = "Welcome back, User!";
    private static final String NEW_USER_GREET = "No save files detected. Hello new user!";

    public static void printErrorLine() {
        System.out.println("/////////////////////////////////////////////////////////////");
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints initialising Message
     */
    public static void initialMessage() {
        printLine();
        System.out.println("Hi this is,\n" + logo);
        System.out.println("Enter \"help\" to find out how to use BagPacker");
        printLine();
    }

    /**
     * Prints goodbye Message
     */
    public static void goodbyeMessage() {
        printLine();
        System.out.println("Bye thanks for using,\n" + logo);
        printLine();
    }

    /**
     * Prints Error Message with the type of error and a helping message
     *
     * @param errorType   the type of error (e.g. invalid integer input)
     * @param helpMessage a message to help the user (e.g. try to input a whole number digit)
     */
    public static void errorMessage(String errorType, String helpMessage) {
        printErrorLine();
        System.out.print("Error " + errorType);
        System.out.println(":\n" + helpMessage);
        printErrorLine();
    }

    /**
     * Prints help message
     */
    public static void helpMessage() {
        printLine();
        System.out.println("All Commands:");
        System.out.println("1. " + AddCommand.HELP_MSG);
        System.out.println("2. " + DeleteCommand.HELP_MSG);
        System.out.println("3. " + ListCommand.HELP_MSG);
        System.out.println("4. " + PackCommand.HELP_MSG);
        System.out.println("5. " + UnpackCommand.HELP_MSG);
        System.out.println("6. " + DeleteListCommand.HELP_MSG);
        System.out.println("7. " + ListUnpackedCommand.HELP_MSG);
        System.out.println("8. " + EditQuantityCommand.HELP_MSG);
        System.out.println("9. " + PackAllCommand.HELP_MSG);
        System.out.println("10. " + UnpackAllCommand.HELP_MSG);
        System.out.println("11. " + FindCommand.HELP_MSG);
        System.out.println("12. " + ByeCommand.HELP_MSG);
        printLine();
    }

    /**
     * Prints messages(s) to the user
     *
     * @param message Message to be shown to the user
     */
    public static void printToUser(String... message) {
        printLine();
        for (String m : message) {
            System.out.println(m);
        }
        printLine();
    }

    public static void showUserReturn() {
        printToUser(RETURN_USER_GREET);
    }

    public static void showNewUser() {
        printToUser(NEW_USER_GREET);
    }
}
