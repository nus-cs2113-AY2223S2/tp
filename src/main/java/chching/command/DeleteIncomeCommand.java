package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;


public class DeleteIncomeCommand extends Command {
    
    private final int index;
    public DeleteIncomeCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) throws ChChingException {
        if(index <0){
            throw new ChChingException("Negative index");
        }
        incomes.deleteIncome(index);
        System.out.println("Income deleted, here is the updated list:");
        incomes.printIncomeList();
    }
    
}

