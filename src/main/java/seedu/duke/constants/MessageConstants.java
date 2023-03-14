package seedu.duke.constants;

public final class MessageConstants {
    public static final String NEWLINE = System.lineSeparator();
    public static final String LOGO = " _____           _        _   _____      _" + NEWLINE
            + "|  __ \\         | |      | | |  __ \\    | |" + NEWLINE
            + "| |__) |__   ___| | _____| |_| |__) |_ _| |" + NEWLINE
            + "|  ___/ _ \\ / __| |/ / _ \\ __|  ___/ _` | |" + NEWLINE
            + "| |  | (_) | (__|   <  __/ |_| |  | (_| | |" + NEWLINE
            + "|_|   \\___/ \\___|_|\\_\\___|\\__|_|   \\__,_|_|";
    public static final String CATEGORY = "Category: ";
    public static final String DESCRIPTION = "Description: ";
    public static final String PRICE = "Price: $";
    public static final String MESSAGE_EXIT = "Bye. See you again :)" + NEWLINE;
    public static final String MESSAGE_EXPENDITURE_ADDED = "The following expenditure has been added:" + NEWLINE;
    public static final String MESSAGE_EXPENDITURE_DELETED = "The following expenditure has been deleted:" + NEWLINE;
    public static final String MESSAGE_EXPENDITURE_EDITED = "The following expenditure has been updated:" + NEWLINE;
    public static final String MESSAGE_INVALID_ID = "Please enter a valid numerical index!";
    public static final String MESSAGE_INVALID_PRICE = "Please enter the price in numeric or decimal form!";
    public static final String MESSAGE_EMPTY_INPUT = "Use /help for a list of supported commands!";
    public static final String MESSAGE_INVALID_ARGUMENTS = "Please enter the required argument(s)!";
    public static final String MESSAGE_MISSING_ARGS_ADD = "Please specify the description, category and price!";
    public static final String MESSAGE_MISSING_ARGS_EDIT = "Please specify at least 1 detail you would like to edit!";
    public static final String MESSAGE_INVALID_COMMAND = "Please enter a valid command!";
    public static final String MESSAGE_INVALID_CATEGORY = "Please specify a valid category!";
    // TODO: Add commands after constants class is implemented
    public static final String MESSAGE_HELP = "PocketPal is a expense tracking app, "
            + "optimised for use via a Command Line Interface. " + NEWLINE
            + "Users can take advantage of the input flags for entering entries quickly." + NEWLINE
            + "Listed below are the various commands that are available." + NEWLINE;
    public static final String MESSAGE_ADD_COMMAND = "Add command" + NEWLINE
            + "/add [expenditure description] -p [amount spent] -c [expenditure category]" + NEWLINE + NEWLINE;
    public static final String MESSAGE_DELETE_COMMAND = "Delete command" + NEWLINE
            + "/delete [expense ID number]" + NEWLINE + NEWLINE;
    public static final String MESSAGE_EDIT_COMMAND = "Edit command" + NEWLINE
            + "/edit [expense ID number] -p [amount spent] -c [expenditure category]" + NEWLINE + NEWLINE;
    public static final String MESSAGE_VIEW_COMMAND = "View command" + NEWLINE
            + "/view" + NEWLINE + NEWLINE;
    public static final String MESSAGE_HELP_COMMAND = "Help command" + NEWLINE
            + "/help" + NEWLINE + NEWLINE;
    public static final String MESSAGE_BYE_COMMAND = "Exit command" + NEWLINE
            + "/bye" + NEWLINE;
    public static final String MESSAGE_WELCOME = "Welcome to" + NEWLINE
            + LOGO + NEWLINE + NEWLINE
            + "How may I help you?" + NEWLINE;


    private MessageConstants() {
    }
}
