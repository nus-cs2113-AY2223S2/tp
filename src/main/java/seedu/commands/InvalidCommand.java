package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class InvalidCommand extends Command {
    // Edit file accordingly
    public final String commandError;

    public InvalidCommand(String commandError) {
        this.commandError = commandError;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult(commandError);
    }
}
