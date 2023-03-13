package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public class BalanceCommand extends Command {
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        double totalIncome = 0;
        double totalExpense = 0;
        for(int i = 0; i < incomes.size(); i++) {
            totalIncome += incomes.get(i).getValue();
            System.out.println(totalIncome);
        }
        for(int i = 0; i < expenses.size(); i++) {
            totalExpense += expenses.get(i).getValue();
        }
        double balance = totalIncome - totalExpense;
        System.out.println(balance);
        ui.showBalance(balance);
    }
}
