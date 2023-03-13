package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public abstract class Command {
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) throws ChChingException {
    }

    /**
     * Method to indicate to exit the program after executed
     * @return boolean.
     */
    public boolean isExit() {
        return false;
    }
}
