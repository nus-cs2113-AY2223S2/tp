package seedu.ui;


import seedu.commands.Command;
import seedu.exceptions.InvalidArgumentException;

import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "Let's get moving!\n" + "\"/wstart DD/MM/YY\" to start " +
            "your workout!";
    private static final String LOGO_MESSAGE = " _______  __  .___________.________  \n" +
            "|   ____||  | |           |       /  \n" +
            "|  |__   |  | `---|  |----`---/  /   \n" +
            "|   __|  |  |     |  |       /  /    \n" +
            "|  |     |  |     |  |      /  /----.\n" +
            "|__|     |__|     |__|     /________|";
    private static final String LINE = "=======================================";
    private static final String LINE_SEPARATOR = "----------------------------------";
    private static final Scanner in = new Scanner(System.in);
    private static final String HELP_MESSAGE =
            "Here are the list of commands that you can use:" +System.lineSeparator() + LINE + System.lineSeparator()
                    + "- [Start a Workout: /start])" + System.lineSeparator()
                    + "- [Add exercise: /wadd]" + System.lineSeparator()
                    + "- [Add calories: /cadd]" + System.lineSeparator()
                    + "- [End current workout: /end]" + System.lineSeparator()
                    + "- [Display workout list: /list]" + System.lineSeparator()
                    + "- [Display a workout on a specific date : /wview]" + System.lineSeparator()
                    + "- [Display calories consumed on a specific date : /cview]" + System.lineSeparator()
                    + "- [Display the amount of reps and set on a specific exercise /count]" + System.lineSeparator()
                    + "- [Delete a workout: /delete]" + System.lineSeparator()
                    + "- [Exit app: /exit]" + System.lineSeparator() + LINE;
    private static final String READ_FILE_ERROR_MESSAGE = "Error reading file: ";
    public static void showGreeting() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void showLogo(){
        System.out.println(LOGO_MESSAGE);
    }

    public static String showLine(){
        return LINE;
    }
    public static void showOneLine(){
        System.out.println(LINE);
    }


    public static String line() {
        return LINE;
    }

    public static String getUserInput() {
        return in.nextLine();
    }

    public static void showWelcomeMessage() {
        showOneLine();
        showLogo();
        showOneLine();
        showGreeting();
    }

    public static String getHelpMessage() {
        return HELP_MESSAGE;
    }

    public static void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void showCommandResult(Command command) throws InvalidArgumentException {
        System.out.println(command.execute());
    }

    public static void showReadFileErrorMessage(String fileName) {
        System.out.println(READ_FILE_ERROR_MESSAGE + fileName);
    }

    public static void showNoSavedDataMessage(String fileName) {
        System.out.println("No saved data found for " + fileName + '.');
        System.out.println("Creating new file for " + fileName + ".......");
    }

    public static void showCreatedNewFileMessage(String fileName) {
        System.out.println("New file for " + fileName + " created.");
    }
    public static void showNewFileNotCreatedMessage(String fileName) {
        System.out.println("Error creating new file for " + fileName + ".");
        System.out.println("User data may not be saved!");
    }

    public static void showCreateDirectoryMessage() {
        System.out.println("Creating directories.....");
    }

    public static void showDirectoryNotCreatedMessage() {
        System.out.println("Unable to create directories. User data may not be saved.");
    }

    public static void showSaveUserDataErrorMessage() {
        System.out.println("Error saving user data.");
    }
    public static void showSuccessfulLoadDataMessage(String fileName) {
        System.out.println("Successfully loaded " + fileName + " data.");
    }
}
