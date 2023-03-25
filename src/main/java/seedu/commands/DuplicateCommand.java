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
        expenditures.duplicateExpenditure(index);
        return new CommandResult(String.format("Duplicated " + expenditures.getExpenditure(index)));
    }
}
