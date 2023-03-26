package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialReport;

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
    public static final String COMMAND_DELETE_SHORTCUT = "delete_shortcut";
    public static final String COMMAND_VIEW_SHORTCUT = "view_shortcut";
    public static final String COMMAND_IGNORE = "ignore";
    public static final String COMMAND_UNIGNORE = "unignore";


    protected FinancialReport financialReport;

    /**
     * Sets the attribute to reference the financial report for execution of commands
     *
     * @param financialReport financial report for the commands to be performed on
     */
    public void setData(FinancialReport financialReport) {
        this.financialReport = financialReport;
    }

    /**
     * Sets up logger for logging
     */
    protected abstract void setupLogger();

    /**
     * Executes the command and print the relevant output message
     */
    public abstract CommandResult execute();
}
