package seedu.duke.data;

import java.util.ArrayList;

public class ExpenseList extends RecordList{

    protected static ArrayList<Record> expenseList = new ArrayList<>();
    protected static int expenseCount = 0;

    public ExpenseList() {
        expenseList = new ArrayList<>();
        expenseCount = 0;
    }
    public static void printExpenseList() {
        for (int i = 1; i <= expenseCount; i++) {
            Record record = expenseList.get(i - 1);
            System.out.println(i + ". " + record.toString());
        }
    }
}
