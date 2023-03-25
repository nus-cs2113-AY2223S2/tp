package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.modules.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class FinancialReport {
    private final ArrayList<FinancialStatement> financialReport;
    private final HashMap<Integer, Double> monthlyExpenditures;
    private String reportOwner;

    public FinancialReport(ArrayList<FinancialStatement> financialReport, HashMap<Integer, Double> monthlyOutflow) {
        this.financialReport = financialReport;
        this.monthlyExpenditures = monthlyOutflow;
    }

    public String getReportOwner() {
        if (this.reportOwner == null) {
            return "no name";
        }
        return reportOwner;
    }

    public void setReportOwner(String name) {
        this.reportOwner = name;
        Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
    }

    public int getStatementCount() {
        return financialReport.size();
    }

    public void addStatement(FinancialStatement statement) {
        financialReport.add(statement);

        Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
    }

    public void addStatementAtIndex(FinancialStatement statement, int index) {
        financialReport.add(index, statement);
        Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
    }

    public FinancialStatement deleteStatement(int statementNumber) {
        FinancialStatement oldStatement = financialReport.get(statementNumber);
        financialReport.remove(financialReport.get(statementNumber));
        Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
        return oldStatement;
    }

    public String getFullStatement(int statementNumber) {
        return financialReport.get(statementNumber).getStatementForList();
    }

    public String getStatementDescription(int statementNumber) {
        return financialReport.get(statementNumber).getDescription();
    }

    public void clearReport() {
        financialReport.clear();
    }

    public FinancialStatement getFinancialStatement(int statementNumber) {
        return financialReport.get(statementNumber);
    }

    public ArrayList<FinancialStatement> getFinancialReport() {
        return this.financialReport;
    }

    public LocalDate getStatementDate(int statementNumber) {
        return financialReport.get(statementNumber).getDate();
    }

    public double updateMonthlyExpenditure(FinancialStatement statement, boolean addToExpenditure) {
        int monthAndYear = statement.getMonthAndYear();
        if(!monthlyExpenditures.containsKey(monthAndYear)) {
            monthlyExpenditures.put(monthAndYear, 0.0);
        }
        double currentExpenditure = monthlyExpenditures.get(monthAndYear);
        if(statement.getFlowDirectionWord().equals("out")) {
            if(addToExpenditure) {
                currentExpenditure += statement.getValue();
            } else {
                currentExpenditure -= statement.getValue();
            }
            monthlyExpenditures.put(monthAndYear, currentExpenditure);
        }
        return currentExpenditure;
    }
}
