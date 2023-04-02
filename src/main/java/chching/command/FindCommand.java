package chching.command;

import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.Income;
import chching.record.IncomeList;
import chching.record.Expense;
import chching.record.ExpenseList;
import chching.record.TargetStorage;
import chching.ChChingException;

/**
 * model a class to handle the find command. inherit from Command class.
 */
public class FindCommand extends Command {
    private final String category;
    private final String keyword;

    public FindCommand(String category, String keyword) {
        this.category = category;
        this.keyword = keyword;
    }

    /**
     * Executes the find command
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
        IncomeList incomesMatched = new IncomeList();
        ExpenseList expensesMatched = new ExpenseList();

        if(category == null) {
            throw new ChChingException("No category specified");

        } else if(keyword == null) {
            throw new ChChingException("No keyword specified");

        } else if(!category.equals("income") && !category.equals("expense")) {
            throw new ChChingException("Category specified must be income or expense");

        } else if (keyword.strip() == "") {
            throw new ChChingException("No keyword specified");
        }

        if (category.equals("income")) {
            for (int i = 0; i < incomes.size(); i++) {
                Income income = incomes.get(i);
                if (income.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    incomesMatched.addIncome(income);
                }
            }
            ui.showMatchedIncome(incomesMatched);

        } else {
            for (int i = 0; i < expenses.size(); i++) {
                Expense expense = expenses.get(i);
                if (expense.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    expensesMatched.addExpense(expense);
                }
            }
            ui.showMatchedExpense(expensesMatched);
        }
    }
}
