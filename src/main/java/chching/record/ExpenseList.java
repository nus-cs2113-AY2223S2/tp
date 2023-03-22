package chching.record;

import java.util.ArrayList;

import chching.currency.Converter;
import chching.currency.Selector;

public class ExpenseList extends RecordList {

    protected ArrayList<Expense> expenseList;

    public ExpenseList(ArrayList<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public ExpenseList() {
        expenseList = new ArrayList<>();
    }

    public int size() {
        return expenseList.size();
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

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

    @Override
    public Expense get(int i) {
        return expenseList.get(i);
    }
}
