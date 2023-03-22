package chching.record;

import java.util.ArrayList;

/**
 * Models a class that act as list of expenses. Inherited from RecordList Class
 */
public class ExpenseList extends RecordList{

    protected ArrayList<Expense> expenseList;

    /**
     * Constructor to instantiate IncomeList objects
     *
     * @param expenseList ArrayList of expenses
     */
    public ExpenseList(ArrayList<Expense> expenseList){
        this.expenseList = expenseList;
    }

    /**
     * Default constructor to instantiate ExpenseList objects
     */
    public ExpenseList(){
        expenseList = new ArrayList<>();
    }

    public int size() {
        return expenseList.size();
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }


    /**
     * Deletes expense from an ExpenseList
     *
     * @param i     index of the income entry
     */
    public void deleteExpense(int i) throws IndexOutOfBoundsException{
        try {
            expenseList.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There is no expense with this index");
        }
    }

    public void printExpenseList() {
        for (int i = 1; i <= expenseList.size(); i++) {
            Record record = expenseList.get(i - 1);
            System.out.println("    " + i + ". " + record.toString());
        }
    }

    @Override
    public Expense get(int i) {
        return expenseList.get(i);
    }
}
