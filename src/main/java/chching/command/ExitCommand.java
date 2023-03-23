package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.TargetStorage;

/**
 * Models a class to handle the bye command. Inherited from Command class.
 */
public class ExitCommand extends Command {


    /**
     * Executes program termination
     *
     * @param incomes       ArrayList of income.
     * @param expenses      ArrayList of income.
     * @param ui        User interface
     * @param storage       Storage of data
     * @param converter     Convert value
     * @param targetStorage store target
     */
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
            Converter converter, TargetStorage targetStorage) throws ChChingException {
        storage.save(incomes, expenses);
        ui.showGoodbye();
    }

    /**
     * Method to indicate to exit the program after executed
     * 
     * @return boolean.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
