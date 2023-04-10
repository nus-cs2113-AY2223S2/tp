package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Income;
import chching.record.TargetStorage;

/**
 * Models a class to add income to incomeList. Inherited from Command class.
 */
public class AddIncomeCommand extends Command {
    private final Income income;

    /**
     * Constructor that checks validity of income input
     *
     * @param income       ArrayList of income.
     */
    public AddIncomeCommand(Income income) throws ChChingException {
        if (income == null) {
            throw new ChChingException("No fields found");
        } else if (income.getDescription() == null) {
            throw new ChChingException("Missing description field");
        } else if(income.getDescription().length() > 99) {
            throw new ChChingException("Character limit of 99 for description field exceeded");
        } else if (income.getDate() == null) {
            throw new ChChingException("Missing date field");
        } else if (income.getValue() <= 0) {
            throw new ChChingException("Invalid/Missing income value");
        }
        this.income = income;
    }

    /**
     * Executes addition of income to list of incomes
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
        assert income.getValue() > 0 : "Income value must be positive";
        incomes.addIncome(income);
        ui.showAdded(incomes, expenses, income);
    }
}
