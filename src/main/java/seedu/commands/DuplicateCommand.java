package seedu.commands;
import seedu.expenditure.ExpenditureList;

public class DuplicateCommand extends Command {

    public static final String COMMAND_WORD = "duplicate";
    public final int index;
    public DuplicateCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        try {
            expenditures.duplicateExpenditure(index);
            return new CommandResult(String.format("Duplicated " + expenditures.getExpenditure(index)));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("Index is out of bounds or negative");
        }
    }
}
