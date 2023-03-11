package seedu.commands;

import seedu.Expenditure.ExpenditureList;

public class DeleteCommand extends Command{
    // Edit file accordingly
    public static final String COMMAND_WORD = "delete";
    public final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Deleted ...");
    }
}
