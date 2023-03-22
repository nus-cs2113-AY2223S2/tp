package seedu.ui;

import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to Fitness checker, Let's get moving!\n" + "\"/start <DD/MM/YY>\" to begin " +
            "your workout";
    private static final String LOGO_MESSAGE = " _______  __  .___________.________  \n" +
            "|   ____||  | |           |       /  \n" +
            "|  |__   |  | `---|  |----`---/  /   \n" +
            "|   __|  |  |     |  |       /  /    \n" +
            "|  |     |  |     |  |      /  /----.\n" +
            "|__|     |__|     |__|     /________|";
    private static final String ENDING_MESSAGE = "Thank you, hope you had a great workout!!!";

    private static final String LINE = "=======================================";
    private final Scanner in = new Scanner(System.in);

    public static void showGreeting() {
        System.out.println(WELCOME_MESSAGE);
    }
    public static void showExit(){
        System.out.println(ENDING_MESSAGE);
    }

    public static void showLogo(){
        System.out.println(LOGO_MESSAGE);
    }
    public static void showLine(){
        System.out.println(LINE);
    }

    public String getUserInput() {
        return in.nextLine();
    }
}
