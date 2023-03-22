package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;

/**
 * Models a class to list the entries in the expenseList. Inherited from Command class.
 */
public class ListExpenseCommand extends Command {

    /**
     * Executes listing of expenses from expenseList
     *
     * @param incomes       ArrayList of income.
     * @param expenses      ArrayList of income.
     * @param ui        User interface
     * @param storage       Storage of data
     */
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        System.out.println("    Expenses:");
        expenses.printExpenseList();
    }
}
