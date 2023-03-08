package Expense;

import java.util.Comparator;

public class ExpenseCostComparator implements Comparator<Expense> {
    @Override
    public int compare(Expense e1, Expense e2) {
        return (int)((e2.getCost() - e1.getCost()) * 100);
    }
}
