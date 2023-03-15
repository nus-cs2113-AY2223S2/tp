package seedu.ui;

import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "Let's get moving!\n" + "\"/start <DD/MM/YY>\" to begin " +
            "your workout";

    private static final String ENDING_MESSAGE = "Thank you and see you next time";
    private final Scanner in = new Scanner(System.in);

    public static void showGreeting() {
        System.out.println(WELCOME_MESSAGE);
    }
    public static void showExit(){
        System.out.println(ENDING_MESSAGE);
    }

    public String getUserInput() {
        return in.nextLine();
    }
}
