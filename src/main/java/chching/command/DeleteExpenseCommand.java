package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public class DeleteExpenseCommand extends Command {
    private final int index;

    public DeleteExpenseCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
            Converter converter) throws ChChingException {
        if (index <= 0) {
            throw new ChChingException("Negative/Zero index");
        } else if (index > expenses.size()) {
            throw new ChChingException("The number is too big");
        }
        assert index > 0 : "Index must be a positive integer";
        expenses.deleteExpense(index);
        System.out.println("Expense deleted, here is the updated list:");
        expenses.printExpenseList(selector, converter);
    }

}
