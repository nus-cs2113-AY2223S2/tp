package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;

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
     */
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
            Converter converter) throws ChChingException {
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
