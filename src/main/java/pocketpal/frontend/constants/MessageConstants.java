package pocketpal.frontend.constants;

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

    // Command Messages
    public static final String MESSAGE_EXIT = "Bye. See you again :)" + NEWLINE;
    public static final String MESSAGE_EXPENDITURE_ADDED = "The following expenditure has been added:" + NEWLINE;
    public static final String MESSAGE_EXPENDITURE_DELETED = "The following expenditure has been deleted:" + NEWLINE;
    public static final String MESSAGE_EXPENDITURE_EDITED = "The following expenditure has been updated:" + NEWLINE;
    public static final String MESSAGE_NO_ENTRIES = "There are no entries available." + NEWLINE;
    public static final String MESSAGE_ADD_COMMAND = "Add - Adds an expense to your current expenditure." + NEWLINE
            + "Usage: /add -d <description> -c <category> -p <price>" + NEWLINE
            + "Options:" + NEWLINE
            + "-d <description>" + NEWLINE
            + "-c <category>" + NEWLINE
            + "-p <price>" + NEWLINE
            + "See below for examples" + NEWLINE
            + "/add -d Apple Macbook Air -p 1300 -c Personal" + NEWLINE
            + "/add -p 1300 -c Personal -d Apple Macbook Air" + NEWLINE + NEWLINE;
    public static final String MESSAGE_DELETE_COMMAND = "Delete - Deletes specified expense(s) from your expenditure."
            + NEWLINE
            + "Usage: /delete <index> [additional_index...]" + NEWLINE
            + "See below for examples" + NEWLINE
            + "/delete 10 11 13 " + NEWLINE
            + "/delete 1" + NEWLINE + NEWLINE;
    public static final String MESSAGE_EDIT_COMMAND = "Edit - Edits a specified expense in your current expenditure."
            + NEWLINE
            + "Usage: /edit <index> [options]" + NEWLINE
            + "Options:" + NEWLINE
            + "-d <description>" + NEWLINE
            + "-c <category>" + NEWLINE
            + "-p <price>" + NEWLINE
            + "See below for examples" + NEWLINE
            + "/edit 5 -d Grab to school -c Transportation -p 20.00" + NEWLINE + NEWLINE;

    public static final String MESSAGE_VIEW_COMMAND = "View - Displays a list of your current expenditure."
            + NEWLINE
            + "Usage: /view [count] [filter_options]" + NEWLINE
            + "Filter options:" + NEWLINE
            + "-c <category>" + NEWLINE
            + "-sp <startprice>" + NEWLINE
            + "-ep <endprice>" + NEWLINE
            + "-sd <startdate>, -ed <enddate>" + NEWLINE
            + "See below for examples" + NEWLINE
            + "/view 100 -c Transportation -sp 2.00 -ep 5.00" + NEWLINE
            + "/view -sd 21/11/97 -ed 22/11/97 -c Transportation -sp 2.00" + NEWLINE
            + "/view 10 -sd 21/11/97 -ed 22/12/97 -c Transportation -sp 2.00 -ep 6.00" + NEWLINE + NEWLINE;
    public static final String MESSAGE_HELP_COMMAND = "Help - Displays the help menu." + NEWLINE
            + "Usage: /help" + NEWLINE + NEWLINE;
    public static final String MESSAGE_BYE_COMMAND = "Exit - Terminates PocketPal." + NEWLINE
            + "Usage: /bye" + NEWLINE;
    public static final String MESSAGE_WELCOME = "Welcome to" + NEWLINE
            + LOGO + NEWLINE + NEWLINE
            + "How may I help you?" + NEWLINE;
    public static final String MESSAGE_HELP = "PocketPal is a expense tracking app, "
            + "optimised for use via a Command Line Interface. " + NEWLINE
            + "Users can take advantage of the input flags for entering entries quickly." + NEWLINE
            + "Listed below are the various commands that are currently supported." + NEWLINE + NEWLINE;

    // Exception Messages
    public static final String MESSAGE_EMPTY_INPUT = "Use /help for a list of supported commands!";
    public static final String MESSAGE_INVALID_COMMAND = "Please enter a valid command!";
    public static final String MESSAGE_INVALID_CATEGORY = "Please specify a valid category!";
    public static final String MESSAGE_INVALID_DESCRIPTION = "Description can only contain letters and numbers!";
    public static final String MESSAGE_ID_NOT_FOUND = "Entry not found. Please enter an ID from 1 to ";
    public static final String MESSAGE_NON_EXISTENT_ID = "Item ID does not exist: ";
    public static final String MESSAGE_INVALID_ID = "Please specify a valid integer from 1 to 2147483647!";
    public static final String MESSAGE_INVALID_AMOUNT = "Please enter a valid amount!" + NEWLINE
            + "Value should be between 0.01 and 999999999.99";
    public static final String MESSAGE_INVALID_AMOUNT_RANGE = "Please specify a valid range!" + NEWLINE
            + "Values should be between 0 and 1000000000";
    public static final String MESSAGE_UNKNOWN_OPTION = "Unknown option: ";
    public static final String MESSAGE_UNKNOWN_ARGUMENTS = "Unknown arguments: ";
    public static final String MESSAGE_MISSING_REQUIRED_OPTION = "Missing required options: ";
    public static final String MESSAGE_MISSING_OPTION_EDIT = "Please specify at least one option!";
    public static final String MESSAGE_MISSING_OPTION_ARG = "Missing argument for option: ";
    public static final String MESSAGE_MISSING_ID_EDIT = "Please specify the ID of the item you would like to " +
            "edit!";
    public static final String MESSAGE_MISSING_ID_DELETE = "Please specify the ID of the item you would like to " +
            "delete!";
    public static final String MESSAGE_INVALID_DATE = "Please specify date in dd/MM/yy format" +
            " and make sure the date exists!";
    public static final String MESSAGE_MIXED_DATE = "Start date should not be after end date!";
    public static final String MESSAGE_MISSING_DATE = "Please enter BOTH the start and end date!";
    public static final String MESSAGE_INVALID_DATE_READ = "Date format not recognised.";
    public static final String MESSAGE_INVALID_SAVE_DATA = NEWLINE + "Warning: Save file contains invalid data. "
            + "The next operation will discard all saved entries." + NEWLINE;

    private MessageConstants() {
    }
}
