package chching.record;

import chching.ChChingException;

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
    
    public void editExpense(int index, String field, String value) throws ChChingException {
    
        // change from 1-based indexing to 0-based indexing
        int indexZeroBased = index - 1;
        Expense expense = expenseList.get(indexZeroBased);
        
        // edit the according field
        switch(field) {
        case "c":
            expense.setCategory(value);
            break;
        case "de":
            expense.setDescription(value);
            break;
        case "da":
            expense.setDate(value);
            break;
        case "v":
            try {
                double amount = Float.parseFloat(value);
                expense.setValue(amount);
            } catch (Exception e) {
                throw new ChChingException("Trouble adding expense value");
            }
            break;
        default:
            assert false : "No such field to enter here";
            throw new ChChingException("No such field in expense");
        }
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
            System.out.println("    " + i + ". " + record.toString());
        }
    }

    @Override
    public Expense get(int i) {
        return expenseList.get(i);
    }
}
