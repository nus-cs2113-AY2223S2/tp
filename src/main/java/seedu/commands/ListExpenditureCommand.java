package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class ListExpenditureCommand extends Command {
    // Edit file accordingly
    public static final String COMMAND_WORD = "list";

    public ListExpenditureCommand() {
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Here is your list of expenditures: \n"
                + expenditures.toString());
    }
}
