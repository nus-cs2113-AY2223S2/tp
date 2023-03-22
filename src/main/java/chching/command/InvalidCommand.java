package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;

/**
 * model a class to handle the invalid command. inherit from Command class.
 */
public class InvalidCommand extends Command {
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
            Converter converter) throws ChChingException {
        ui.showInvalidMessage();
    }
}
