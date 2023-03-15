package seedu.moneymind;

public class Strings {
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String WHITE_SPACE = " ";
    public static final String BYE = "bye";
    public static final String VIEW = "view";
    public static final String DELETE = "delete";
    public static final String EVENT = "event";
    public static final String CATEGORY = "category";
    public static final String INVALID_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String DELETE_FORMAT = "Please following the correct format: " +
            "delete c/<category name> e/<event name>" +
            " or delete c/<category name>";
    public static final String REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY = "Remember do not leave any things " +
            "inside the brackets empty!";
    public static final String EMPTY_DELETION = "☹ OOPS!!! The description of a delete cannot be empty.";
    public static final String SUBTLE_BUG_MESSAGE = "☹ OOPS!!! Something went wrong, please report to the developer.";
    public static final String EVENT_REGEX = "(.+) b/(-?\\d+) e/(-?\\d+)";
    public static final String EVENT_FORMAT = "Please following the correct format: " +
            "event <event name> b/<budget number> e/<expense number>";
    public static final String EVENT_EMPTY = "☹ OOPS!!! The description of an event cannot be empty.";
    public static final String CATEGORY_EMPTY = "☹ OOPS!!! The description of a category cannot be empty.";
    public static final String DELETE_REGEX = "^c/(?=\\S)(.*?)(?:\\s+e/(.*))?\\s*$";
    public static final String NULL_INPUT_ASSERTION = "Input cannot be null";
    public static final String NULL_DESCRIPTION = "Separated keyword and description cannot be null";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_POSITIVE_NUMBER =
            "Please enter a positive number for budget and expense";
    public static final String STORAGE_NEXT_VARIABLE = "&&next_detail&&";
    public static final String NEW_LINE = System.lineSeparator();
    public static final String STORAGE_CATEGORY_NAME = "&&new_category&&";
    public static final String STORAGE_CATEGORY_MAP = "&&category_map&&";
    public static final String EXISTED_CATEGORY = "Category already exists";
    public static final String EXTRA_SPACE_REGEX_FORMAT = "\\s+";
    public static final String HELP = "help";
}
