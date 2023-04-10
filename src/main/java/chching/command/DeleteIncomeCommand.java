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
 * Models a class to handle the DeleteIncome command. Inherited from Command class.
 */
public class DeleteIncomeCommand extends Command {

    private int index;

    public DeleteIncomeCommand(int index) {
        this.index = index;
    }


    /**
     * Executes deletion of income from list of incomes
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
        if (index <= 0) {
            throw new ChChingException("Negative/Zero index");
        } else if (index > incomes.size()) {
            throw new ChChingException("The number is too big");
        }
        incomes.deleteIncome(index);
        System.out.println("Income deleted, here is the updated list:");
        incomes.printIncomeList(selector, converter);
    }
}
