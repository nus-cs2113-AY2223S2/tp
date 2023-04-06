package seedu.todolist.constants;

/**
 * Enum that holds all the notification and help messages to be displayed.
 */

public final class Messages {
    public static final String LINE = "_".repeat(90);
    public static final String START = "Hello, I am your To-Do List " +
            "and I will help you remember the tasks you need to do!";
    public static final String NEW_SAVE = "No save data found, creating a new task list for you!";
    public static final String LOAD_SAVE = "Your saved task list was successfully loaded with ";
    public static final String NEW_CONFIG = "No configuration data found, your settings will be set to the default!";
    public static final String LOAD_CONFIG = "Your configuration file was successfully loaded.";
    public static final String EXIT = "See you again, bye!";

    public static final String ADD_TASK = "Okay, I have added this task:";
    public static final String MARK_TASK = "Okay, I have marked the following task(s) as complete:";
    public static final String UNMARK_TASK = "Okay, I have marked the following task(s) as incomplete:";
    public static final String DELETE_TASK = "Okay, I have removed the following task(s):";
    public static final String EDIT_TASK = "Okay, I have edited the %s of the following task(s) to [%s]:";
    public static final String EDIT_DELETE_TASK = "Okay, I have deleted the %s of the following task(s):";
    public static final String EDIT_TAGS_TASK = "Okay, I have %s the tags [%s] %s the following task(s):";
    public static final String EDIT_CONFIG = "Okay, I have changed the configuration to as follows:";
    public static final String FILTERED_NONE = "No tasks matched the given filter.";

    public static final String LIST_TASKS = "Okay, here is your task list, with ";
    public static final String TAGS_INFO = "Okay, here are the tags associated with your task list:";
    public static final String LIST_EMPTY = "There are no tasks in your list.";
    public static final String TAGS_EMPTY = "There are no tags associated with your task list.";
    public static final String FULL_INFO = "Okay, here is the detailed information of the following task(s):";
    public static final String CONFIG_INFO = "Here are the configuration settings for your system:";

    private Messages() {}
}
