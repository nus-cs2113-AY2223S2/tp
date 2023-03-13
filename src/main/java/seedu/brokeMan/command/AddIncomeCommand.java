package seedu.brokeMan.command;

import seedu.brokeMan.income.Income;
import seedu.brokeMan.income.IncomeList;

public class AddIncomeCommand extends Command {
    public static final String COMMAND_WORD = "addIncome";
    private final Income income;

    public AddIncomeCommand(double amount, String info, String time) {
        this.income = new Income(amount, info, time);
    }

    public void execute() {
        IncomeList.addIncome(income);
    }
}
