package seedu.duke;

public class ByeCommand {

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    /**
     * Constructs a new ByeCommand object and exits the program.
     */
    public ByeCommand() {
        doByeCommand();
    }

    private void doByeCommand() {
        System.out.println(BYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
        System.exit(0);
    }

}
