package seedu.commands;

import seedu.expenditure.ExpenditureList;

public class DeleteCommand extends Command {
    // Edit file accordingly
    public static final String COMMAND_WORD = "delete";
    public final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        try {
            expenditures.deleteExpenditure(index);
            return new CommandResult(
                    "Entry has been deleted\n" + "Here is your updated list: \n" + expenditures.toString());

        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Index is out of bounds or negative");
        }

    }
}
