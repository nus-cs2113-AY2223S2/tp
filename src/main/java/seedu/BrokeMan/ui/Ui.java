package seedu.BrokeMan.ui;

import java.io.PrintStream;
import java.util.Scanner;

import static seedu.BrokeMan.common.Messages.*;

/*
Some parts of the code are copied and adapted from TextUI.java of addressbook-level2
with the link to the original code at:
https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
 */
public class Ui {
//    private static final Scanner in = new Scanner(System.in);
    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;
//    private static final String LINE_DIVIDER = "________________________________________________________________________";
    private static final String LINE_DIVIDER = "-----------------------------------------------------------------------";
    private static final String LINE_PREFIX = "|  ";


    /**
     * Gets user command from the terminal
     * @return command entered by the user
     */
    public static String getUserCommand() {
        out.println(LINE_PREFIX + "Enter command: ");
        return in.nextLine();
    }

    public static void showWelcomeMessages() {
        showToUser(LINE_DIVIDER, LINE_DIVIDER, "", MESSAGE_LOGO,
                MESSAGE_WELCOME, "", LINE_DIVIDER);
    }

    public static void showGoodByeMessages() {
        showToUser("", MESSAGE_GOODBYE, "",
                LINE_DIVIDER, LINE_DIVIDER);
    }

    public static void showToUser(String... messages) {
        for (String message : messages) {
            out.println(LINE_PREFIX + message);
        }
    }
}
