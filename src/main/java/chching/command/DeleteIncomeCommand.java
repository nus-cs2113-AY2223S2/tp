package chching.command;

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
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        incomes.deleteIncome(index);
        System.out.println("Income deleted, here is the updated list:");
        incomes.printIncomeList();
    }
    
}

