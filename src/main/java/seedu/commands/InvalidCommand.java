package seedu.commands;

import seedu.Expenditure.ExpenditureList;

public class InvalidCommand extends Command{
    // Edit file accordingly
    public static final String COMMAND_WORD = "delete";

    public InvalidCommand() {}

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Invalid Command! Please refer to help for acceptable commands.");
    }
}
