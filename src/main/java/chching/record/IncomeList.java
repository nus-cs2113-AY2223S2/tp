package chching.record;

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
    public static void editIncome(int i, String category, String description, String date, float value) {
        Income inc = (Income) incomeList.get(i - 1);
        if(!(category == null)) {
            inc.category = category;
        }
        if(!(description == null)) {
            inc.description = description;
        }
        if(!(date == null)) {
            inc.date = date;
        }
        if(!(value == 0)) {
            inc.value = value;
        }
    }
    public static void printIncomeList() {
        for (int i = 1; i <= incomeCount; i++) {
            Record record = incomeList.get(i - 1);
            System.out.println(i + ". " + record.toString());
        }
    }
}