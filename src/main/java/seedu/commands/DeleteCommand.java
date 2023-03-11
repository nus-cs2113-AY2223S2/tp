package seedu.commands;

import seedu.Expenditure.ExpenditureList;

public class DeleteCommand extends Command{
    // Edit file accordingly
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand() {}

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Deleted ...");
    }
}
