package command;

import data.Expense;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import static common.MessageList.MESSAGE_DIVIDER;
import static common.MessageList.MESSAGE_DIVIDER_CATEGORY;

public class CommandCategory extends Command {
    public static final String COMMAND_NAME = "classify";
    public static final String ALL_CATEGORY = "Here are all your expense categories: ";

    protected ArrayList<Expense> expenseList;
    private String category;
    private Set<String> categorySet = new HashSet<>();

    public CommandCategory(ArrayList<Expense> expenseList, String category) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
        this.category = category;
        for (Expense e: expenseList) {
            String categoryOfe = e.getDescription();
            if (!categorySet.contains(categoryOfe)) {
                categorySet.add(categoryOfe);
            }
        }
    }

    /**
     * Execution of to classify the expenses based on all categories.
     * It will display all the categories within it.
     * It will also display all the expenses with the specified category.
     *
     * @return printing the list of command
     *
     */
    @Override
    public CommandRes execute() {
        if (expenseList.size() == 0) {
            System.out.println("Sorry, there are no expenses tracked currently.");
            System.out.println(MESSAGE_DIVIDER);
        } else if (!categorySet.contains(category)) {
            System.out.println("Sorry, none of your previous expenses corresponds to this category.");
            System.out.println(MESSAGE_DIVIDER);
        } else {
            displayAllCategories();
            System.out.println(MESSAGE_DIVIDER_CATEGORY);
            int index = 1;
            for (Expense e: expenseList) {
                if (e.getDescription().equals(category)) {
                    System.out.print((index) + ".");
                    System.out.println(e.sortedDisplay("C"));
                }
            }
            System.out.println(MESSAGE_DIVIDER);
        }
        return null;
    }

    private void displayAllCategories() {
        System.out.println(ALL_CATEGORY);
        for (String categoryStored : categorySet) {
            System.out.print(categoryStored + " ");
        }
        System.out.println();
        System.out.println("Totally there are " + categorySet.size() + " categories.");
    }
}
