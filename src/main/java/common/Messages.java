package common;

/**
 * A class containing the list of messages that will be shown to the user. "ERROR for error. MESSAGE for message"
 */
public class Messages {
    /**
     * Messages for programs to print.
     */
    public static final String MESSAGE_VALID_COMMAND_LIST =
            "List of valid commands:\n" +
            "GENERIC COMMANDS:\n%s\n"+
            "MEETING COMMANDS:\n%s\n"+
            "DEADLINE COMMANDS:\n%s\n"+
            "RECIPE COMMANDS:\n%s\n"+
            "WORKER COMMANDS:\n%s";
    public static final String MESSAGE_GENERIC_COMMANDS =
            "help - View list of commands\n" +
            "exit - exits the program\n";
    public static final String MESSAGE_MEETING_COMMANDS =
            "add_meeting n/<name> t/<time>\n" +
            "delete_meeting n/<meeting>\n" +
            "view_meetings\n";
    public static final String MESSAGE_DEADLINE_COMMANDS =
            "add_deadline n/<name> t/<time>\n" +
            "delete_deadline n/<name>\n" +
            "view_deadlines\n";
    public static final String MESSAGE_RECIPE_COMMANDS =
            "add_recipe n/<name>\n" +
            "delete_recipe n/<name>\n" +
            "view_recipe\n";
    public static final String MESSAGE_WORKER_COMMANDS =
            "add_worker n/<name> w/<working day> d/<date of birth> p/<phone>\n" +
            "delete_worker n/<name>\n" +
            "view_workers\n";
    public static final String MESSAGE_COMMAND_EXIT = "Thank you for using DinerDirector!";

    /**
     * Errors for programs to print.
     */
    public static final String ERROR_COMMAND_INVALID = "Please give a valid command! " +
            "Type \"help\" for list of valid commands";
}
