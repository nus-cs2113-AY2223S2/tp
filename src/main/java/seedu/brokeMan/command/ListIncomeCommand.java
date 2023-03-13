package seedu.brokeMan.command;

import seedu.brokeMan.income.IncomeList;

public class ListIncomeCommand extends Command {
    public static final String COMMAND_WORD = "listIncome";

    public void execute() {
        IncomeList.listIncome();
    }
}
