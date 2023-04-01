package seedu.rainyDay.command;

import seedu.rainyDay.data.UserData;
import seedu.rainyDay.exceptions.RainyDayException;

//@@author lil1n

/**
 * Represents an executable command
 */
public abstract class Command {

    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_VIEW = "view";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_FILTER = "filter";
    public static final String COMMAND_EDIT = "edit";
    public static final String COMMAND_EXPORT = "export";
    public static final String COMMAND_SHORTCUT = "shortcut";
    public static final String COMMAND_SET_BUDGET = "setbudget";
    public static final String COMMAND_DELETE_SHORTCUT = "shortcut_delete";
    public static final String COMMAND_VIEW_SHORTCUT = "shortcut_view";
    public static final String COMMAND_IGNORE = "ignore";
    public static final String COMMAND_UNIGNORE = "unignore";

    protected UserData userData;

    /**
     * Sets the attribute to reference the financial report for execution of commands
     *
     * @param userData userData provided for the commands to be performed on
     */
    public void setData(UserData userData) {
        this.userData = userData;
    }

    /**
     * Sets up logger for logging
     */
    protected abstract void setupLogger();

    /**
     * Executes the command and print the relevant output message
     */
    public abstract CommandResult execute() throws RainyDayException;
}
