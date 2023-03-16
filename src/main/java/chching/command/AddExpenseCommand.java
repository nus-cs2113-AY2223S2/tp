package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Expense;

public class AddExpenseCommand extends Command {
    private final Expense expense;

    public AddExpenseCommand(Expense expense) throws ChChingException {
        if (expense == null) {
            throw new ChChingException("No fields found");
        } else if (expense.getCategory() == null) {
            throw new ChChingException("Missing category field");
        } else if (expense.getDescription() == null) {
            throw new ChChingException("Missing description field");
        } else if (expense.getDate() == null) {
            throw new ChChingException("Missing date field");
        } else if (expense.getValue() <= 0) {
            throw new ChChingException("Invalid/Missing expense value");
        }
        this.expense = expense;
    }
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        assert expense.getValue() > 0 : "Expense value should be greater than 0";
        expenses.addExpense(expense);
        ui.showAdded(incomes, expenses, expense);
    }
}
