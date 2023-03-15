package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;

public class BalanceCommand extends Command {
    private double balance;
    
    public String showBalance() {
        return String.format("%.02f", balance);
    }
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        double totalIncome = 0;
        double totalExpense = 0;
        for(int i = 0; i < incomes.size(); i++) {
            totalIncome += incomes.get(i).getValue();
        }
        for(int i = 0; i < expenses.size(); i++) {
            totalExpense += expenses.get(i).getValue();
        }
        balance = totalIncome - totalExpense;
        assert balance <= totalIncome : "Wrong calculations";
        
        ui.showBalance(totalExpense, totalIncome, balance);
    }
}
