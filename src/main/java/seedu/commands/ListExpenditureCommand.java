package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class ListExpenditureCommand extends Command {
    // Edit file accordingly
    public static final String COMMAND_WORD = "list";

    public ListExpenditureCommand() {
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        if(expenditures.getSize() == 0) {
            return new CommandResult("Your list is currently empty. Start adding some transactions now!\n");
        }
        
        return new CommandResult("Here is your list of expenditures: \n"
                + expenditures.toString());
    }
}
