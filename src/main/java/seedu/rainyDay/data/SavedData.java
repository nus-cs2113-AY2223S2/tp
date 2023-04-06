package seedu.rainyDay.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SavedData {
    private FinancialReport financialReport;
    private HashMap<String, String> shortcutCommands;
    private double budgetGoal;

    public SavedData(FinancialReport financialReport) {
        this.financialReport = financialReport;
        shortcutCommands = new HashMap<>();
        this.budgetGoal = 0;
    }

    public SavedData(FinancialReport financialReport, HashMap<String, String> shortcutCommands) {
        this.financialReport = financialReport;
        this.shortcutCommands = shortcutCommands;
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

    public int getStatementCount() {
        return financialReport.getStatementCount();
    }

    /**
     * Helper command used to check if the user's expenditure for the month has exceeded his set budget
     * The progress is show if and only if a budget is present. Otherwise, an empty string is returned
     *
     * @param currentDate the date to be checked
     * @return A string denoting the progress if applicable
     */
    public String checkUserBudgetLimit(LocalDate currentDate) {
        double budgetLimit = getBudgetGoal();
        if (budgetLimit < 0) {
            return "\nYour budget was set to negative.\nUse the setbudget command to have a more realistic goal!";
        }
        if (budgetLimit == 0) {
            return "";
        }
        int currentMonth = currentDate.getMonthValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL uuuu");
        String monthYearString = currentDate.format(formatter);
        int currentYear = currentDate.getYear();
        int monthYear = currentMonth + currentYear * 12;
        double currentSpending = MonthlyExpenditures.getMonthlyExpenditure(monthYear);
        if (currentSpending >= budgetLimit) {
            return String.format("\nYou've spent $%.2f/$%.2f for %s and exceeded your budget! " +
                    "Try harder next time! :(", currentSpending, budgetLimit, monthYearString);
        }
        return String.format("\nYou have spent $%.2f/$%.2f for %s, Keep it up!",
                currentSpending, budgetLimit, monthYearString);
    }

    public FinancialStatement getStatement(int statementIndex) {
        return financialReport.getFinancialStatement(statementIndex);
    }

    public void deleteStatement(int statementIndex) {
        financialReport.deleteStatement(statementIndex);
    }

    public LocalDate getStatementDate(int statementIndex) {
        return financialReport.getStatementDate(statementIndex);
    }

    public void addStatement(FinancialStatement statement) {
        financialReport.addStatement(statement);
    }

    public String getReportOwner() {
        return financialReport.getReportOwner();
    }
}
