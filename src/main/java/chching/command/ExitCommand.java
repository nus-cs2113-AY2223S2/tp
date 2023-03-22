package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;

/**
 * model a class to handle the bye command. inherit from Command class.
 */
public class ExitCommand extends Command {
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
