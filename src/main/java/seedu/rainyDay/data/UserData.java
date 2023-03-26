package seedu.rainyDay.data;

import java.time.LocalDate;
import java.util.HashMap;

public class UserData {
    private FinancialReport financialReport;
    private HashMap<String, String> shortcutCommands;
    private double budgetGoal;

    public UserData(FinancialReport financialReport) {
        this.financialReport = financialReport;
        shortcutCommands = new HashMap<>();
        this.budgetGoal = 0;
    }

    public FinancialReport getFinancialReport() {
        return financialReport;
    }

    public HashMap<String, String> getShortcutCommands() {
        return shortcutCommands;
    }

    public void setBudgetGoal(double goal) {
        this.budgetGoal = goal;
    }

    public double getBudgetGoal() {
        return this.budgetGoal;
    }

    public String checkUserBudgetLimit(double currentSpending, FinancialStatement newStatement) {
        double budgetLimit = getBudgetGoal();
        int currentMonthYear = LocalDate.now().getMonthValue() + LocalDate.now().getYear() * 12;
        if (newStatement.getMonthAndYear() != currentMonthYear) {
            return "";
        }
        if (budgetLimit == 0) {
            return "";
        }
        if (currentSpending >= budgetLimit) {
            return "\nYou've exceeded your budget! Try harder next time :(";
        }
        return String.format("\nYou have spent $%.2f/$%.2f, Keep it up!", currentSpending, budgetLimit);
    }

}
