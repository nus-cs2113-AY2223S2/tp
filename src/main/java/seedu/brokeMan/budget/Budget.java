package seedu.brokeMan.budget;

import seedu.brokeMan.ui.Ui;

import static seedu.brokeMan.entry.Expenses.getTotalExpense;

//import static seedu.brokeMan.entry.Expenses.expenseList;

public class Budget {
    private static double budget;
    public static boolean hasSetBudget = false;

//    public Budget(double budget) {
//        this.budget = budget;
//    }

    public static void viewBudget() {
//        if (!hasSetBudget) {
//            throw new hasNotSetBudgetException();
//        }
        double totalExpenses = getTotalExpense();
        double budgetLeft = budget - totalExpenses;

//        System.out.println("You have " + this.budget + " left.");
        Ui.showToUser("You have set your budget as $" + budget);
        if (budgetLeft >= 0) {
            Ui.showToUserWithLineBreak("The amount of budget left is $" + budgetLeft, "");
        } else {
            Ui.showToUserWithLineBreak("You have overspent your expenses by $" + budgetLeft, "");
        }
    }

//    public double getBudget() {
//        return budget;
//    }

    public static void setBudget(double budget) {
        hasSetBudget = true;
        Budget.budget = budget;
        Ui.showToUserWithLineBreak("You have successfully set " + budget + " as your budget.", "");
    }
}
