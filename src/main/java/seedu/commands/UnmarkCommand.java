package seedu.commands;

import seedu.exceptions.AlreadyUnmarkException;
import seedu.exceptions.NoPaidFieldException;
import seedu.expenditure.ExpenditureList;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        try {
            expenditures.unmarkExpenditure(index);
            return new CommandResult(
                    "Unmarked your expenditure!\n" + expenditures.getExpenditure(index));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Index is out of bounds or negative");
        } catch (NoPaidFieldException e) {
            return new CommandResult(e.getMessage());
        } catch (AlreadyUnmarkException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
