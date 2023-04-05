package common;

public class MessageList {
    public static final String MESSAGE_DIVIDER =
            "_____________________________________________________________________________________";
    public static final String MESSAGE_DIVIDER_LIST =
            "_________________________________________LIST_________________________________________";

    public static final String MESSAGE_DIVIDER_CATEGORY =
            "_______________________________________CATEGORY_______________________________________";
    public static final String MESSAGE_DIVIDER_FIND =
            "_________________________________________FIND_________________________________________";
    public static final int OFFSET = 1;

    public static final String SUCCESSFUL_DELETE = "The following expense is successfully deleted:";
    public static final String SUCCESSFUL_ADD = "The following expense is successfully added:";

    public static final String TAB = "    ";
    public static final String ACCOUNT_MESSAGE = "> Type \"login\" to start manage your expense. \n" +
            "> Haven't got your account yet? Type \"signup\" to create your account. \n" +
            "> Type \"logout\" to end and auto-save your work.";
    public static final String COMMAND_LIST_MESSAGE =
            "> Type \"list\" to list all the expenses. \n" +
                    "> Type \"add amt/x t/y cat/z\" with x is the amount of expense, " +
                    "y is the date in \n the form DD-MM-YYYY, z is the category of the expense. \n" +
                    "> Type \"delete\" follow by a number z to delete task z in the the expense list. \n" +
                    "> Type \"bye\" to exit.";
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String HELLO_MESSAGE = "Hello from\n" + logo + "Please Type The Command As Follow:";
    public static final String NAME_QUESTION = "What is your name?";
    public static final String WHITESPACE = " ";
    public static final String PERIOD = ".";

}
