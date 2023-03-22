package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;

/**
 * Models a class to list incomes and expenses. Inherited from Command class.
 */
public class ListCommand extends Command {

    /**
     * Executes the listing of incomes and expenses
     *
     * @param incomes       ArrayList of income.
     * @param expenses      ArrayList of income.
     * @param ui        User interface
     * @param storage       Storage of data
     */
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        ui.showAllRecords(incomes, expenses);
    }
}
