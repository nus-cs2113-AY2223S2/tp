package seedu.brokeMan.command;

import seedu.brokeMan.save.SaveExpense;
import seedu.brokeMan.save.SaveIncome;
import seedu.brokeMan.ui.Ui;

import static seedu.brokeMan.entry.expense.ExpenseList.expenseList;
import static seedu.brokeMan.entry.income.IncomeList.incomeList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": exits the program\n" +
            "|  Example: " + COMMAND_WORD;
    public void execute() {
        Ui.showToUserWithLineBreak("Exiting program...", "");
        SaveExpense.writeFile(expenseList);
        SaveIncome.writeFile(incomeList);
    }
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
