package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Expense;

public class AddExpenseCommand extends Command {
    private final Expense expense;

    public AddExpenseCommand(Expense expense) {
        this.expense = expense;
    }
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        expenses.addExpense(expense);
        System.out.println("Expense added:");
        System.out.println(expense.toString());
    }
}