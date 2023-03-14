package seedu.moneymind;

import static seedu.moneymind.Strings.BYE_MESSAGE;
import static seedu.moneymind.Strings.HORIZONTAL_LINE;

public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private static final String LOGO = "[LOGO_PLACEHOLDER]\n";
    private static final String GREETING = "Hello from\n" + LOGO + "How may I help you?";
    private static final String NAME_REQUEST = "What is your name?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";
    private static final String ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String LIST = "Here are the events in your list:";

    public void greet() {
        System.out.println(GREETING);
    }

    public void requestName() {
        System.out.println(NAME_REQUEST);
    }

    public void goodbye() {
        System.out.println(BYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    public void error(Exception e) {
        System.out.println(ERROR + "\n" + e);
    }

    public void list() {
        System.out.println(LIST);
    }

    public void echo(String value) {
        System.out.println(value);
    }
}
