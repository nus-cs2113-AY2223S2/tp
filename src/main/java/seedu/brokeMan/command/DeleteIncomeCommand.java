package seedu.brokeMan.command;

import seedu.brokeMan.entry.IncomeList;

public class DeleteIncomeCommand extends Command {
    public static final String COMMAND_WORD = "deleteIncome";
    int index;

    public DeleteIncomeCommand(int index) {
        this.index = index;
    }

    public void execute() {
        IncomeList.deleteIncome(index);
    }
}
