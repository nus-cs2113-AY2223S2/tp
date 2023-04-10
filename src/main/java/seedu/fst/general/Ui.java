package seedu.fst.general;

import java.util.Scanner;

/**
 * Text UI of the application
 */
public class Ui {

    public static final String LOGO_PATTERN = "F O O D   S U P P L Y   T R A C K E R\n";

    public Ui() {
    }

    /**
     * Reading in user input.
     *
     * @return user input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String fullInputLine = sc.nextLine();
        assert fullInputLine != null : "Input cannot be empty";
        return fullInputLine;
    }

    public void showWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO_PATTERN);
        System.out.println("Hi! I am FoodSupplyTracker:)");
        System.out.println("Let us track expiry dates and combat food waste!");
        showLine();
    }

    public void showLine() {
        System.out.println("______________________________");
    }

    public static void showError(String message) {
        System.out.println(message);
    }


}
