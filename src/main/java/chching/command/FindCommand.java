package chching.command;

import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.Income;
import chching.record.IncomeList;
import chching.record.Expense;
import chching.record.ExpenseList;

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

    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
                              Converter converter) {

        IncomeList incomesMatched = new IncomeList();
        ExpenseList expensesMatched = new ExpenseList();

        if (category == "income") {
            for (int i = 0; i < incomes.size(); i++) {
                Income income = incomes.get(i);
                if (income.toString().contains(keyword)) {
                    incomesMatched.addRecord(income);
                }
            }
            ui.showMatchedRecord(incomesMatched);

        } else {
            for (int i = 0; i < expenses.size(); i++) {
                Expense expense = expenses.get(i);
                if (expense.toString().contains(keyword)) {
                    expensesMatched.addRecord(expense);
                }
            }
            ui.showMatchedRecord(expensesMatched);
        }
    }

}
