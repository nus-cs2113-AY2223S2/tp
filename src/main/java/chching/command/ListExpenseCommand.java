package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public class ListExpenseCommand extends Command {
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        System.out.println("Expenses:");
        expenses.printExpenseList();
    }
}
