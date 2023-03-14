package seedu.commands;

import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class EditCommand extends Command{
    // Edit file accordingly
    public static final String COMMAND_WORD = "edit";

    public EditCommand(int index, LocalDate date, String amount, String category) {}

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Edited ...");
    }
}
