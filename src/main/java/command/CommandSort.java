package command;

import data.Expense;
import data.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_LIST;

public class CommandSort extends Command {
    public static final String COMMAND_NAME = "sort";
    protected ArrayList<Expense> expenseList;
    protected String sortBy;

    private ArrayList<Expense> expenseListDate = new ArrayList<>();
    private ArrayList<Expense> expenseListCategory = new ArrayList<>();

    public CommandSort(ArrayList<Expense> expenseList) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
    }

    public CommandSort(ArrayList<Expense> expenseList, String sortBy) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
        this.sortBy = sortBy;
        for (int i = 0; i < expenseList.size(); i++) {
            this.expenseListDate.add(expenseList.get(i));
            this.expenseListCategory.add(expenseList.get(i));
        }

    }

    /**
     * Method allows the user to sort their expenses list based on either categories or date
     * If it is sorted by category, it will display the category first.
     * If it is sorted by date, it will display the date first.
     * The above two is to make it easy for user to see the sorted things.
     */
    @Override
    public CommandRes execute() {
        if (expenseList.size() == 0) {
            System.out.println("Sorry, there are no expenses tracked currently.");
            System.out.println(MESSAGE_DIVIDER);
        } else {
            if (!sortBy.equals("C") && !sortBy.equals("D")) {
                System.out.println("Please indicate you want your expenses sorted by Date/Category? ");
                System.out.println("Enter: D (represent Date); C (represent Category)");
                assert false;
                return null;
            }

            System.out.println(MESSAGE_DIVIDER_LIST);
            if (sortBy.equals("C")) {
                sortByCategory();
                for (int i = 0; i < expenseListCategory.size(); i++) {
                    System.out.print((i + 1) + ".");
                    System.out.println(expenseListCategory.get(i).sortedDisplay(sortBy));
                }
            } else {
                sortByDate();
                for (int i = 0; i < expenseListDate.size(); i++) {
                    System.out.print((i + 1) + ".");
                    System.out.println(expenseListDate.get(i).sortedDisplay(sortBy));
                }
            }
            System.out.println(MESSAGE_DIVIDER);
        }
        return null;
    }

    private void sortByDate() {
        int size = expenseListDate.size();
        for (int i = 0; i < size; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                Time timeOfj = Time.toTime(expenseListDate.get(j).getExpenseTime());
                Time timeOfMinIndex = Time.toTime(expenseListDate.get(minIndex).getExpenseTime());
                if (timeOfj.compareTo(timeOfMinIndex) < 0) {
                    minIndex = j;
                }
            }
            Expense tempExpense = new Expense(expenseListDate.get(i).getExpenseAmount(),
                    Time.toTime(expenseListDate.get(i).getExpenseTime()),
                    expenseListDate.get(i).getDescription(),
                    expenseListDate.get(i).getCurrencyType(), expenseListDate.get(i).getRate());
            Expense minIndexExpense = new Expense(expenseListDate.get(minIndex).getExpenseAmount(),
                    Time.toTime(expenseListDate.get(minIndex).getExpenseTime()),
                    expenseListDate.get(minIndex).getDescription(),
                    expenseListDate.get(minIndex).getCurrencyType(), expenseListDate.get(i).getRate());

            expenseListDate.set(i, minIndexExpense);
            expenseListDate.set(minIndex, tempExpense);
        }
    }

    private void sortByCategory() {
        int size = expenseListCategory.size();
        for (int i = 0; i < size; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                String categoryOfj = expenseListCategory.get(j).getDescription();
                String categoryOfMinIndex = expenseListCategory.get(minIndex).getDescription();
                if (categoryOfj.compareTo(categoryOfMinIndex) < 0) {
                    minIndex = j;
                }
            }
            Expense tempExpense = new Expense(expenseListCategory.get(i).getExpenseAmount(),
                    Time.toTime(expenseListCategory.get(i).getExpenseTime()),
                    expenseListCategory.get(i).getDescription(),
                    expenseListCategory.get(i).getCurrencyType(), expenseListDate.get(i).getRate());
            Expense minIndexExpense = new Expense(expenseListCategory.get(minIndex).getExpenseAmount(),
                    Time.toTime(expenseListCategory.get(minIndex).getExpenseTime()),
                    expenseListCategory.get(minIndex).getDescription(),
                    expenseListCategory.get(minIndex).getCurrencyType(), expenseListDate.get(i).getRate());

            expenseListCategory.set(i, minIndexExpense);
            expenseListCategory.set(minIndex, tempExpense);
        }
    }

    /* @@Ju Can
     */

    // TODO: sort descending
    public ArrayList<Expense> sortByCategorySum() {
        CommandCategory commandCategory = new CommandCategory(expenseList);
        ArrayList<Expense> expensesByCategorySum = commandCategory.sumByCategory();
        expensesByCategorySum.sort(Comparator.comparing(Expense::getExpenseAmount));
        return expensesByCategorySum;
    }

}
