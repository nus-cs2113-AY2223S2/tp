package chching.record;

import chching.ChChingException;

import java.time.LocalDate;
import java.util.ArrayList;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.parser.DecimalsChecker;

import static chching.parser.Expenses.parseDate;

/**
 * Models a class that act as list of expenses. Inherited from RecordList Class
 */
public class ExpenseList extends RecordList {

    protected ArrayList<Expense> expenseList;

    /**
     * Constructor to instantiate IncomeList objects
     *
     * @param expenseList ArrayList of expenses
     */
    public ExpenseList(ArrayList<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    /**
     * Default constructor to instantiate ExpenseList objects
     */
    public ExpenseList() {
        expenseList = new ArrayList<>();
    }

    public int size() {
        return expenseList.size();
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

    /**
     * Method to edit an expense in the expense list.
     * Edits a specific field of an expense based on the parameters field to value.
     *
     * @param index Index of expense to be edited.
     * @param field Field to be edited.
     * @param value Updated value desired.
     * @throws ChChingException If value value is not a non-numeric input.
     */
    public void editExpense(int index, String field, String value) throws ChChingException {

        // change from 1-based indexing to 0-based indexing
        int indexZeroBased = index - 1;
        Expense expense = expenseList.get(indexZeroBased);

        // edit the according field
        switch (field) {
        case "c":
            expense.setCategory(value);
            break;
        case "de":
            expense.setDescription(value);
            break;
        case "da":
            LocalDate date = parseDate(value);
            expense.setDate(date);
            break;
        case "v":
            boolean isTwoDecimalsOrLess = DecimalsChecker.isTwoDecimals(value);
            if(!isTwoDecimalsOrLess) {
                throw new ChChingException("Expense value must be a valid positive double that is 2 d.p. or less");
            }
            try {
                double amount = Double.parseDouble(value);
                if (amount < 0.01) {
                    throw new ChChingException("Expense must be greater than or equals 0.01");
                } else if (amount > 999999.99) {
                    throw new ChChingException("Expense value must be less than 1000000");
                }
                assert amount > 0.01 : "Income must be positive and more than 0";
                expense.setValue(amount);
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    throw new ChChingException("Expense value must be a number");
                }
                throw new ChChingException(e.getMessage());
            }
            break;
        default:
            assert false : "No such field to enter here";
            throw new ChChingException("No such field in expense");
        }
    }

    /**
     * Deletes expense from an ExpenseList
     *
     * @param i index of the income entry
     */
    public void deleteExpense(int i) throws IndexOutOfBoundsException {
        try {
            expenseList.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There is no expense with this index");
        }
    }

    public void printExpenseList(Selector selector, Converter converter) {
        for (int i = 1; i <= expenseList.size(); i++) {
            Record record = expenseList.get(i - 1);
            String convertedCurrencies = converter.printConverter(record.value, selector);
            System.out.println("    " + i + ". " + record.toString() + convertedCurrencies);
        }
    }

    public void clearExpenseList() {
        expenseList.clear();
    }

    @Override
    public Expense get(int i) {
        return expenseList.get(i);
    }
}
