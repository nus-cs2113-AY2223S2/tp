package seedu.rainyDay.data;

import java.util.HashMap;

public class UserData {
    private FinancialReport financialReport;
    private HashMap<String, String> shortcutCommands;
    private double budgetGoal = 0;

    public UserData(FinancialReport financialReport) {
        this.financialReport = financialReport;
        shortcutCommands = new HashMap<>();
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

}
