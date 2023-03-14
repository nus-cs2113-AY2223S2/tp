package chching.record;

import java.util.ArrayList;

public class IncomeList extends RecordList{
    protected ArrayList<Income> incomeList;

    public IncomeList(ArrayList<Income> incomeList){
        this.incomeList = incomeList;
    }

    public IncomeList(){
        incomeList = new ArrayList<>();

    }

    public int size() {
        return incomeList.size();
    }

    public void addIncome(Income income) {
        incomeList.add(income);

    }
    public void deleteIncome(int i) throws IndexOutOfBoundsException{
        try {
            incomeList.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There is no income with this index");
        }
    }

    public void editIncome(int i, String category, String description, String date, float value) {
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
        if(value > 0) {
            inc.value = value;
        }
    }

    public void printIncomeList() {
        for (int i = 1; i <= incomeList.size(); i++) {
            Record record = incomeList.get(i - 1);
            System.out.println(i + ". " + record.toString());
        }
    }

    @Override
    public Income get(int i) {
        return incomeList.get(i);
    }
}
