package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.modules.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class FinancialReport {
    private final ArrayList<FinancialStatement> financialStatements;


    private final HashMap<Integer, Double> monthlyExpenditures;
    private String reportOwner;

    public FinancialReport(ArrayList<FinancialStatement> financialStatements, HashMap<Integer, Double> monthlyOutflow) {
        this.financialStatements = financialStatements;
        this.monthlyExpenditures = monthlyOutflow;
    }

    public String getReportOwner() {
        return reportOwner;
    }

    public void setReportOwner(String name) {
        this.reportOwner = name;
    }

    public int getStatementCount() {
        return financialStatements.size();
    }

    public void addStatement(FinancialStatement statement) {
        financialStatements.add(statement);
        addToMonthlyExpenditure(statement);
        Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
    }

    public HashMap<Integer, Double> getMonthlyExpenditures() {
        return monthlyExpenditures;
    }

    public void addStatementAtIndex(FinancialStatement statement, int index) {
        financialStatements.add(index, statement);
        addToMonthlyExpenditure(statement);
    }

    public void deleteStatement(int statementNumber) {
        removeFromMonthlyExpenditure(financialStatements.get(statementNumber));
        financialStatements.remove(statementNumber);
    }

    public String getFullStatement(int statementNumber) {
        return financialStatements.get(statementNumber).getStatementForList();
    }

    public double getMonthlyExpenditure(int monthAndYear) {
        if (!monthlyExpenditures.containsKey(monthAndYear)) {
            return 0;
        }
        return monthlyExpenditures.get(monthAndYear);
    }

    public void clearReport() {
        financialStatements.clear();
    }

    public FinancialStatement getFinancialStatement(int statementNumber) {
        return financialStatements.get(statementNumber);
    }

    public ArrayList<FinancialStatement> getFinancialStatements() {
        return this.financialStatements;
    }

    public LocalDate getStatementDate(int statementNumber) {
        return financialStatements.get(statementNumber).getDate();
    }

    public void addToMonthlyExpenditure(FinancialStatement statement) {
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

    public void removeFromMonthlyExpenditure(FinancialStatement statement) {
        int monthAndYear = statement.getMonthAndYear();
        double currentExpenditure = monthlyExpenditures.get(monthAndYear);
        if (statement.getFlowDirectionWord().equals("out")) {
            currentExpenditure -= statement.getValue();
            monthlyExpenditures.put(monthAndYear, currentExpenditure);
        }
    }
}
