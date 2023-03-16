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
        if (income == null) {
            throw new ChChingException("No fields found");
        } else if (income.getDescription() == null) {
            throw new ChChingException("Missing description field");
        } else if (income.getDate() == null) {
            throw new ChChingException("Missing date field");
        } else if(income.getValue() <= 0) {
            throw new ChChingException("Invalid/Missing income value");
        }
        this.income = income;
    }
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage) {
        assert income.getValue() > 0 : "Income value must be positive";
        incomes.addIncome(income);
        ui.showAdded(incomes, expenses, income);
    }
}
