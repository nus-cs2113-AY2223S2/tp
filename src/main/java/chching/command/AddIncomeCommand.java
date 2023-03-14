package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Income;

public class AddIncomeCommand extends Command {
    private final Income income;

    public AddIncomeCommand(Income income) throws ChChingException {
        if(income.getValue() <= 0) {
            throw new ChChingException("Invalid income value");
        }
        this.income = income;
    }
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        incomes.addIncome(income);
        System.out.println("Income added");
        System.out.println(income.toString());
    }
}
