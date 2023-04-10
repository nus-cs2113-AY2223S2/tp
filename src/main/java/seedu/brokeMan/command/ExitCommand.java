package seedu.brokeMan.command;

import seedu.brokeMan.save.SaveBudget;
import seedu.brokeMan.save.SaveExpense;
import seedu.brokeMan.save.SaveIncome;
import seedu.brokeMan.ui.Ui;

import static seedu.brokeMan.budget.Budget.budgetEachMonth;
import static seedu.brokeMan.entry.expense.ExpenseList.expenseList;
import static seedu.brokeMan.entry.income.IncomeList.incomeList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": exits the program\n" +
            "|  Example: " + COMMAND_WORD;
    public void execute() {
        SaveExpense.writeFile(expenseList);
        SaveIncome.writeFile(incomeList);
        SaveBudget.writeFile(budgetEachMonth);
        Ui.showToUserWithLineBreak("Exiting program...", "");
    }
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
