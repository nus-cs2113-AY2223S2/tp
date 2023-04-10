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
 * Models a class that unsets currency settings. Inherited from Command class.
 */
public class UnsetCurrencyCommand extends Command {
    private final String currency;

    public UnsetCurrencyCommand(String currency) {
        this.currency = currency;
    }

    /**
     * Executes unset currency
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

        if (!selector.containsCurrency(currency)) {
            throw new ChChingException("Currency not available!");
        }
        selector.unsetCurrency(currency);
        selector.printSelector(converter);
        System.out.println("Not displaying " + currency + " now.");
    }
}
