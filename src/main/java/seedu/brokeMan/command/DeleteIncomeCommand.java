package seedu.brokeMan.command;

import seedu.brokeMan.income.IncomeList;

public class DeleteIncomeCommand extends Command {
    public static final String COMMAND_WORD = "deleteIncome";
    int index;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": deletes an income from the list\n" +
            "|  Parameter: i/ <index>\n" +
            "|  Example: " + COMMAND_WORD + " i/ 1";

    public DeleteIncomeCommand(int index) {
        this.index = index;
    }

    public void execute() {
        IncomeList.deleteIncome(index);
    }
}
