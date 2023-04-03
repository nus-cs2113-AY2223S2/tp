package seedu.moneymind.ui;

import static seedu.moneymind.string.Strings.BYE_MESSAGE;
import static seedu.moneymind.string.Strings.HORIZONTAL_LINE;

public class Ui {
    private static final String LOAD_ERROR_RISK_MESSAGE = "Please correct the save file and restart the program, or risk data loss.";
    private static final String ERROR_LOADING_FILE = "Error loading file. ";
    private static final String LOGO = "[LOGO_PLACEHOLDER]\n";
    private static final String GREETING = "Welcome to Moneymind\n" + LOGO + "How may I help you?";
    private static final String ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String LIST = "Here are the events in your list:";

    public void greet() {
        System.out.println(GREETING);
    }

    public void goodbye() {
        System.out.println(BYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    public void loadingError(Exception e) {
        System.out.println(HORIZONTAL_LINE + ERROR_LOADING_FILE + e.getMessage());
        System.out.println(LOAD_ERROR_RISK_MESSAGE);
    }

    public void error(Exception e) {
        System.out.println(ERROR + "\n" + e);
    }

    public void list() {
        System.out.println(LIST);
    }

}
