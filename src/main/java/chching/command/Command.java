package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.currency.Selector;
import chching.currency.Converter;

public abstract class Command {
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
            Converter converter) throws ChChingException {
    }

    /**
     * Method to indicate to exit the program after executed
     * 
     * @return boolean.
     */
    public boolean isExit() {
        return false;
    }
}
