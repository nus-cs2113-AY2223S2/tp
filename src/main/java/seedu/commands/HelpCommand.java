package seedu.commands;

import seedu.Expenditure.ExpenditureList;

public class HelpCommand extends Command{
    // Edit file accordingly
    public static final String COMMAND_WORD = "help";

    public HelpCommand() {}

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Help...");
    }
}
