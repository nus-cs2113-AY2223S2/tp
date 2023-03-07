package seedu.moneymind;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private static final String LOGO = "[LOGO_PLACEHOLDER]\n";
    private static final String GREETING = "Hello from\n" + LOGO + "How may I help you?";
    private static final String NAME_REQUEST = "What is your name?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";
    private static final String ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String LIST = "Here are the events in your list:";

    Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public Command processNextCommand() throws InvalidCommandException {
        String input = in.nextLine();
        return new Command(input);
    }

    public void greet() {
        System.out.println(GREETING);
    }

    public void requestName() {
        System.out.println(NAME_REQUEST);
    }

    public void goodbye() {
        System.out.println(GOODBYE);
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
