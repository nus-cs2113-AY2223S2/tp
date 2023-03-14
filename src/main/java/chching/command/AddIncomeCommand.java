package chching.command;

import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Income;

public class AddIncomeCommand extends Command {
    private final Income income;

    public AddIncomeCommand(Income income) {
        this.income = income;
    }
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        incomes.addIncome(income);
        System.out.println("Income added");
        System.out.println(income.toString());
    }
}
