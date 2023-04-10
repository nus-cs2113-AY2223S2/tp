package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Expense;
import chching.record.TargetStorage;

/**
 * Models a class that adds to expenseList. Inherited from Command class.
 */
public class AddExpenseCommand extends Command {
    private final Expense expense;

    /**
     * Constructor that checks validity of expense input
     *
     * @param expense       ArrayList of expenses.
     */
    public AddExpenseCommand(Expense expense) throws ChChingException {
        if (expense == null) {
            throw new ChChingException("No fields found");
        } else if (expense.getCategory() == null) {
            throw new ChChingException("Missing category field");
        } else if(expense.getCategory().length() > 30) {
            throw new ChChingException("Character limit of 30 for category field exceeded");
        } else if (expense.getDescription() == null) {
            throw new ChChingException("Missing description field");
        } else if(expense.getDescription().length() > 99) {
            throw new ChChingException("Character limit of 99 for description field exceeded");
        } else if (expense.getDate() == null) {
            throw new ChChingException("Missing date field");
        } else if (expense.getValue() <= 0) {
            throw new ChChingException("Invalid/Missing expense value");
        }
        this.expense = expense;
    }


    /**
     * Executes addition of expense to list of expenses
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
        assert expense.getValue() > 0 : "Expense value should be greater than 0";
        expenses.addExpense(expense);
        ui.showAdded(incomes, expenses, expense);
    }
}
