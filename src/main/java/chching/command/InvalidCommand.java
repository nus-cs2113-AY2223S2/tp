package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;

/**
 * Models a class to handle the invalid command. Inherited from Command class.
 */
public class InvalidCommand extends Command {

    /**
     * Executes invalidCommand method
     *
     * @param incomes       ArrayList of income.
     * @param expenses      ArrayList of income.
     * @param ui        User interface
     * @param storage       Storage of data
     */
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        ui.showInvalidMessage();
    }
}
