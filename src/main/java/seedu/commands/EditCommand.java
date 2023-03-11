package seedu.commands;

import seedu.Expenditure.ExpenditureList;

public class EditCommand extends Command{
    // Edit file accordingly
    public static final String COMMAND_WORD = "edit";

    public EditCommand() {}

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Edited ...");
    }
}
