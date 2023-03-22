package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Selector;
import chching.currency.Converter;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public class SetCurrencyCommand extends Command {
    private final String currency;

    public SetCurrencyCommand(String currency) {
        this.currency = currency;
    }

    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
            Converter converter)
            throws ChChingException {
        if (!selector.containsCurrency(currency)) {
            throw new ChChingException("Currency not available!");
        }
        selector.setCurrency(currency);
        selector.printSelector();
    }
}
