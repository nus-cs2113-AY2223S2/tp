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
     * Executes the command and returns the result
     *
     * @return CommandResult with the relevant output message as its attribute
     */
    public abstract CommandResult execute();
}
