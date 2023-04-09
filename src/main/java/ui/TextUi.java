package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {
    private final Scanner in;
    private final PrintStream out;

    //@@damithc darrenangwx-reused
    //Source: https://github.com/nus-cs2113-AY2223S2/personbook/blob/main/src/main/java/seedu/personbook/ui/TextUi.java
    //Reused TextUi, shouldIgnoreEmpty, getUserInput methods with minor modifications.

    /**
     * Calls the other constructor.
     */
    public TextUi() {
        this(System.in, System.out);
    }

    /**
     * Sets the scanner and printstream of TextUi.
     *
     * @param in  the Scanner of TextUi.
     * @param out the PrintStream of TextUi.
     */
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the input is empty.
     *
     * @param fullUserInput What the user typed in as inputs.
     * @return true if inputs are empty, only spaces, or only line breaks.
     */
    private boolean shouldIgnoreEmpty(String fullUserInput) {
        return fullUserInput.trim().isEmpty();
    }

    /**
     * Asks the user for an input.
     * It ignores whitespaces and line breaks.
     *
     * @return the command with the input the user typed.
     */
    public String getUserInput() {
        String fullUserInput = "";
        while (shouldIgnoreEmpty(fullUserInput)) {
            System.out.print(">");
            fullUserInput = in.nextLine();
        }

        return fullUserInput;
    }
    //@@damithc

    /**
     * Prints the specified message to the user.
     *
     * @param message The specified message to be shown to the user.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the banner for the application.
     */
    public static void printBanner() {
        String logo =
                "  _____  _                 _____  _               _\n" +
                        " |  __ \\(_)               |  __ \\(_)             | |\n" +
                        " | |  | |_ _ __   ___ _ __| |  | |_ _ __ ___  ___| |_ ___  _ __\n" +
                        " | |  | | | '_ \\ / _ \\ '__| |  | | | '__/ _ \\/ __| __/ _ \\| '__|\n" +
                        " | |__| | | | | |  __/ |  | |__| | | | |  __/ (__| || (_) | |\n" +
                        " |_____/|_|_| |_|\\___|_|  |_____/|_|_|  \\___|\\___|\\__\\___/|_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome to DinerDirector! Please type \"help\" for a list of valid commands.");
        System.out.println("What can I do for you?");
    }
}
