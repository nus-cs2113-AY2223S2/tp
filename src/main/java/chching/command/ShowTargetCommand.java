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
 * Models a class that shows the user's target. Inherited from Command class.
 */
public class ShowTargetCommand extends Command{

    /**
     * Executes showing of user's target
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

        double target = targetStorage.getTarget().getValue();
        String convertedCurrencies = converter.printConverter(target, selector);
        System.out.println("     Current target: " + String.format("%.02f", target) + " SGD" + convertedCurrencies);
    }
}
