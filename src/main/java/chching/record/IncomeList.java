package chching.record;

import java.util.ArrayList;

import chching.currency.Converter;
import chching.currency.Selector;

public class IncomeList extends RecordList {
    protected ArrayList<Income> incomeList;

    public IncomeList(ArrayList<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public IncomeList() {
        incomeList = new ArrayList<>();

    }

    public int size() {
        return incomeList.size();
    }

    public void addIncome(Income income) {
        incomeList.add(income);

    }

    public void deleteIncome(int i) throws IndexOutOfBoundsException {
        try {
            incomeList.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There is no income with this index");
        }
    }

    public void printIncomeList(Selector selector, Converter converter) {
        for (int i = 1; i <= incomeList.size(); i++) {
            Record record = incomeList.get(i - 1);
            String convertedCurrencies = converter.printConverter(record.value, selector);
            System.out.println("    " + i + ". " + record.toString() + convertedCurrencies);
        }
    }

    @Override
    public Income get(int i) {
        return incomeList.get(i);
    }
}
