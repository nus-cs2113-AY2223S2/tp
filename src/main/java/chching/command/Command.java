package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.currency.Selector;
import chching.currency.Converter;
import chching.record.TargetStorage;

/**
 * Abstract Command class that acts as a template for other command classes.
 */

public class Command {


    /**
     * Executes command
     *
     * @param incomes       ArrayList of income.
     * @param expenses      ArrayList of income.
     * @param ui        User interface
     * @param storage       Storage of data
     * @param converter     Convert value
     * @param targetStorage store target
     */
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
                        Converter converter, TargetStorage targetStorage) throws ChChingException {
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
