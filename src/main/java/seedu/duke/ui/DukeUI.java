package seedu.duke.ui;

import java.util.Scanner;

/**
 * Contains the UI helpers for the current Duke session.
 */
public class DukeUI {
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner scanner;

    public DukeUI(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Returns a string representing the next command the user enters.
     * @return the next command string.
     */
    public String getNextCommandString() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void printIntroduction() {
        System.out.println(this.LOGO);
    }

    /**
     * Prints the provided message.
     * @param message the message to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
