package seedu.commands;

import seedu.expenditure.Expenditure;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

public class EditCommand extends Command {
    // Edit file accordingly
    public static final String COMMAND_WORD = "edit";
    public final int index;
    public final LocalDate date;
    public final String amount;
    public final String category;

    public EditCommand(int index, LocalDate date, String amount, String category) {
        this.index = index;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        try {
            Expenditure editedExpenditure = expenditures.getExpenditure(index);
            editedExpenditure.setDate(date);
            editedExpenditure.setValue(Double.parseDouble(amount));
            editedExpenditure.setDescription(category);
            return new CommandResult(String.format("Edited! Here is the updated list:\n" + expenditures.toString()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Failed to edit! Please check the format and try again!");
        }
    }
}
