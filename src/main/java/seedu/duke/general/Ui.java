package seedu.duke.general;

import java.util.Scanner;

/**
 * Text UI of the application
 */
public class Ui {

    public static final String LOGO_PATTERN = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String LIST_MESSAGE = "Here is your food list!";

    public Ui() {
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String fullInputLine = sc.nextLine();
        return fullInputLine;
    }

    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO_PATTERN);
        System.out.println("Hi! I am Duke:)");
        System.out.println("Let us track expiry dates and combat food waste!");
        showLine();
    }

    public void showLine() {
        System.out.println("______________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }


}
