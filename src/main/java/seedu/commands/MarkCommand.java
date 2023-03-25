package seedu.commands;

import seedu.exceptions.AlreadyMarkException;
import seedu.exceptions.NoPaidFieldException;
import seedu.expenditure.ExpenditureList;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        try {
            expenditures.markExpenditure(index);
            return new CommandResult(
                    "Marked your expenditure!\n" + expenditures.getExpenditure(index));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Index is out of bounds or negative");
        } catch (NoPaidFieldException e) {
            return new CommandResult(e.getMessage());
        } catch (AlreadyMarkException e) {
            return new CommandResult(e.getMessage());
        }

    }
}
