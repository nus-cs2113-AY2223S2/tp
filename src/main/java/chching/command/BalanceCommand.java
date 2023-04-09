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
 * Models a class to show the balance. Inherited from Command class.
 */


public class BalanceCommand extends Command {
    private double balance;

    public String showBalance() {
        return String.format("%.02f", balance);
    }


    /**
     * Executes showing balance.
     *
     * @param incomes       ArrayList of income.
     * @param expenses      ArrayList of income.
     * @param ui            User interface
     * @param storage       Storage of data
     * @param converter     Convert value
     * @param targetStorage store target
     */
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
                        Converter converter, TargetStorage targetStorage) throws ChChingException {
        double totalIncome = 0;
        double totalExpense = 0;
        for (int i = 0; i < incomes.size(); i++) {
            totalIncome += incomes.get(i).getValue();
        }
        for (int i = 0; i < expenses.size(); i++) {
            totalExpense += expenses.get(i).getValue();
        }
        balance = totalIncome - totalExpense;
        double currentTarget = targetStorage.getTarget().getValue();
        assert balance <= totalIncome : "Wrong calculations";
        String convertedBalance = converter.printConverter(balance, selector);
        ui.showBalance(totalExpense, totalIncome, balance, convertedBalance);
        String convertedTarget = converter.printConverter(currentTarget, selector);
        System.out.println();
        System.out.println("    Current target:");
        System.out.println("    SGD " + String.format("%.02f", currentTarget) + convertedTarget);

        if (balance >= currentTarget) {
            System.out.println("    Great work! You have met your target goal.");
        } else {
            System.out.println("    Balance has not reached your target goal. Keep on pushing!");
        }
    }
}
