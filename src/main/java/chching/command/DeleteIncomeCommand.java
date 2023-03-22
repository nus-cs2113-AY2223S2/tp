package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;

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
     */
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) throws ChChingException {
        if (index <= 0){
            throw new ChChingException("Negative/Zero index");
        } else if (index > incomes.size()) {
            throw new ChChingException("The number is too big");
        }
        incomes.deleteIncome(index);
        System.out.println("Income deleted, here is the updated list:");
        incomes.printIncomeList();
    }
}
