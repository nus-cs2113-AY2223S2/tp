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

import java.time.LocalDate;

import chching.ChChingException;

/**
 * model a class to handle the find command. inherit from Command class.
 */
public class FindCommand extends Command {
    private final String type;
    private final String category;
    private final String description;
    private final LocalDate date;

    public FindCommand(String type, String category, String description, LocalDate date) {
        this.type = type;
        this.category = category;
        this.description = description;
        this.date = date;
        
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
        Boolean emptyKeyword = true;
        if (description != null) {
            emptyKeyword = (description.strip() == "");
        }

        if(type == null) {
            throw new ChChingException("No type specified");
    
        } else if(!type.equals("income") && !type.equals("expense")) {
            throw new ChChingException("Type specified must be income or expense");

        } else if(type.equals("income") && (description == null || emptyKeyword)  && date == null) {
            throw new ChChingException("No description or date specified for Income");
            
        } else if (type.equals("income") && category != null) {
            throw new ChChingException("Income has no category");

        } else if (type.equals("expense") && (description == null || emptyKeyword) && category == null && date == null) {
            throw new ChChingException("No description or category or date specified");
        }

        if (type.equals("income")) {
            for (int i = 0; i < incomes.size(); i++) {
                Income income = incomes.get(i);
                if ((description == null 
                    || income.getDescription().toLowerCase().contains(description.toLowerCase()))
                    && (date == null || income.getDate().equals(date))) {
                    incomesMatched.addIncome(income);
                }
            }
            ui.showMatchedIncome(incomesMatched);

        } else {
            for (int i = 0; i < expenses.size(); i++) {
                Expense expense = expenses.get(i);
                if ((description == null 
                    || expense.getDescription().toLowerCase().contains(description.toLowerCase()))
                    && (category == null || expense.getCategory().equals(category)) 
                    && (date == null || expense.getDate().equals(date))) {
                    expensesMatched.addExpense(expense);
                }
            }
            ui.showMatchedExpense(expensesMatched);
        }
    }
}
