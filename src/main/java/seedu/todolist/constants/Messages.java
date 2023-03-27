package seedu.todolist.constants;

/**
 * Enum that holds all the notification and help messages to be displayed.
 */
public enum Messages {
    START("Hello, I am your todo list and I will help you remember the tasks you need to do!"),
    NEW_SAVE("No save data found, creating a new task list for you!"),
    LOAD_SAVE("Your saved task list was successfully loaded with "),
    EXIT("See you again, bye!"),

    ADD_TASK("Okay, I have added this task:"),
    MARK_TASK("Okay, I have marked this task as complete:"),
    UNMARK_TASK("Okay, I have marked this task as incomplete:"),
    DELETE_TASK("Okay, I've removed this task:"),
    EDIT_TASK("Okay, I have edited the parameters of this task:"),
    LIST_TASKS("Okay, here is your task list, with "),
    LIST_EMPTY("There are no tasks in your list."),
    SET_EMAIL("Okay, I have set the email of the Professors/TA for this task:"),
    GET_EMAIL("Okay, Here is the email of the Professor/TA for this task:"),
    CHECK_REPEATING("The list has been checked for any repeating tasks."),
    TAG_INFO("These are the current tags associated with your task list:"),
    TAG_EMPTY("There are no tags associated with your task list."),
    TAG_DELETE("Okay, these tags have been deleted:");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
