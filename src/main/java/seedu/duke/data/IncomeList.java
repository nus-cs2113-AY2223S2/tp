package seedu.duke.data;

import java.util.ArrayList;

public class IncomeList extends RecordList{
    protected static ArrayList<Record> incomeList;
    protected static int incomeCount;
    public IncomeList(){
        incomeList = new ArrayList<>();
        incomeCount = 0;
    }
    public static void addIncome(Income income) {
        incomeList.add(income);
        incomeCount++;
    }
    public static void printIncomeList() {
        for (int i = 1; i <= incomeCount; i++) {
            Record record = incomeList.get(i - 1);
            System.out.println(i + ". " + record.toString());
        }
    }
}
