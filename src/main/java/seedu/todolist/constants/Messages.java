package seedu.todolist.constants;

/**
 * Enum that holds all the notification and help messages to be displayed.
 */
public enum Messages {
    LINE("_".repeat(90)),
    START("Hello, I am your To-Do list and I will help you remember the tasks you need to do!"),
    NEW_SAVE("No save data found, creating a new task list for you!"),
    LOAD_SAVE("Your saved task list was successfully loaded with "),
    EXIT("See you again, bye!"),

    ADD_TASK("Okay, I have added this task:"),
    MARK_TASK("Okay, I have marked this task as complete:"),
    UNMARK_TASK("Okay, I have marked this task as incomplete:"),
    DELETE_TASK("Okay, I have removed this task:"),
    EDIT_TASK("Okay, I have edited the %s of this task to [%s]:"),
    EDIT_DELETE_TASK("Okay, I have deleted the %s of this task:"),
    PRIORITY_LEVELS("Please enter a number from 1-3 (1:Low, 2:Medium, 3:High)"),

    LIST_TASKS("Okay, here is your task list, with "),
    TAGS_INFO("Okay, here are the tags associated with your task list:"),
    LIST_EMPTY("There are no tasks in your list."),
    TAGS_EMPTY("There are no tags associated with your task list."),
    FULL_INFO("Okay, here is the detailed information of this task:"),
    SAVED_FILE_SYNTAX_ERROR("There was a problem with your saved file. " +
            "Please correct it before restarting the program.");
    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
