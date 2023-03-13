package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class ViewExpenditureCommand extends Command{
    // Edit file accordingly
    public static final String COMMAND_WORD = "list";

    public ViewExpenditureCommand() {}

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("List shown ...\n"
        + expenditures.toString());
    }
}
