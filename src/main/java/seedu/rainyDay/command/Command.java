package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialReport;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public abstract class Command {

    public static final String COMMAND_ADD = "add";

    public static final String COMMAND_DELETE = "delete";

    public static final String COMMAND_VIEW = "view";

    public static final String COMMAND_HELP = "help";

    public static final String COMMAND_EXIT = "bye";

    protected FinancialReport financialReport;

    public void setData(FinancialReport financialReport) {
        this.financialReport = financialReport;
    }

    protected abstract void setupLogger();

    public abstract void execute();
}
