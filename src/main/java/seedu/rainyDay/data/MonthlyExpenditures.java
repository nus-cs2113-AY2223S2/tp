package seedu.rainyDay.data;

import java.util.HashMap;

public class MonthlyExpenditures {
    private static HashMap<Integer, Double> monthlyExpenditures;

    public MonthlyExpenditures (HashMap<Integer, Double> monthlyExpenditures) {
        this.monthlyExpenditures = monthlyExpenditures;
    }

    public void loadAllExpenditures(FinancialReport fullFinancialReport) {
        FinancialStatement currentStatement;
        for(int index = 0; index < fullFinancialReport.getStatementCount(); index++) {
            currentStatement = fullFinancialReport.getFinancialStatement(index);
            if(!currentStatement.isIgnored()) {
                addToMonthlyExpenditure(currentStatement);
            }
        }
    }

    public static double getMonthlyExpenditure(int monthAndYear) {
        if (!monthlyExpenditures.containsKey(monthAndYear)) {
            return 0;
        }
        return monthlyExpenditures.get(monthAndYear);
    }

    public static void addToMonthlyExpenditure(FinancialStatement statement) {
        int monthAndYear = statement.getMonthAndYear();
        if (!monthlyExpenditures.containsKey(monthAndYear)) {
            monthlyExpenditures.put(monthAndYear, 0.0);
        }
        double currentExpenditure = monthlyExpenditures.get(monthAndYear);
        if (statement.getFlowDirectionWord().equals("out")) {
            currentExpenditure += statement.getValue();
            monthlyExpenditures.put(monthAndYear, currentExpenditure);
        }
    }

    public static void removeFromMonthlyExpenditure(FinancialStatement statement) {
        int monthAndYear = statement.getMonthAndYear();
        double currentExpenditure = monthlyExpenditures.get(monthAndYear);
        if (statement.getFlowDirectionWord().equals("out")) {
            currentExpenditure -= statement.getValue();
            monthlyExpenditures.put(monthAndYear, currentExpenditure);
        }
    }

}
