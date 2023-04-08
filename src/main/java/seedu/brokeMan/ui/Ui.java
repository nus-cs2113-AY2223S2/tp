package seedu.brokeMan.ui;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static seedu.brokeMan.common.Messages.*;

/*
Some parts of the code are copied and adapted from TextUI.java of addressbook-level2
with the link to the original code at:
https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
 */
public class Ui {
    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;
    private static final String LINE_DIVIDER =
            "-----------------------------------------------------------------------";
    private static final String LINE_PREFIX = "|  ";


    /**
     * Gets user command from the terminal
     * @return command entered by the user
     */
    public static String getUserCommand() throws NoSuchElementException {
        showToUser("");
        out.print(LINE_PREFIX + "Enter command: ");
        String userInput;
        userInput = in.nextLine().trim();
        return userInput;
    }

    public static void showWelcomeMessages() {
        showToUserWithLineBreak(LINE_DIVIDER, LINE_DIVIDER, "", MESSAGE_LOGO,
                MESSAGE_WELCOME, "", LINE_DIVIDER);
    }

    public static void showGoodByeMessages() {
        showToUserWithLineBreak(LINE_DIVIDER, "", MESSAGE_GOODBYE,
                "", LINE_DIVIDER);
    }

    public static void showToUserWithLineBreak(String... messages) {
        for (String message : messages) {
            if (message.equals("")) {
                out.println(LINE_PREFIX.trim() + message);
            } else {
                out.println(LINE_PREFIX + message);
            }
        }
        out.println(LINE_PREFIX + LINE_DIVIDER);
    }

    public static void showToUser(String... messages) {
        for (String message : messages) {
            if (message.equals("")) {
                out.println(LINE_PREFIX.trim() + message);
            } else {
                out.println(LINE_PREFIX + message);
            }
        }
    }
}
