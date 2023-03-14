package chching.record;

import java.util.ArrayList;

public class ExpenseList extends RecordList{

    protected ArrayList<Expense> expenseList;

    public ExpenseList(ArrayList<Expense> expenseList){
        this.expenseList = expenseList;
    }

    public ExpenseList(){
        expenseList = new ArrayList<>();

    }

    public int size() {
        return expenseList.size();
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

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
            System.out.println(i + ". " + record.toString());
        }
    }

    @Override
    public Expense get(int i) {
        return expenseList.get(i);
    }
}
