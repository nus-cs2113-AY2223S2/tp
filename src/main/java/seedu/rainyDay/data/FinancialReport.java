package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.modules.Storage;

import java.time.LocalDate;
import java.util.ArrayList;


public class FinancialReport {
    private final ArrayList<FinancialStatement> financialStatements;
    private String reportOwner;

    public FinancialReport(ArrayList<FinancialStatement> financialStatements) {
        this.financialStatements = financialStatements;
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
        MonthlyExpenditures.addToMonthlyExpenditure(statement);
        Storage.writeToFile(RainyDay.userData, RainyDay.filePath);
    }


    public void addStatementAtIndex(FinancialStatement statement, int index) {
        financialStatements.add(index, statement);
        MonthlyExpenditures.addToMonthlyExpenditure(statement);
    }

    public void deleteStatement(int statementNumber) {
        MonthlyExpenditures.removeFromMonthlyExpenditure(financialStatements.get(statementNumber));
        financialStatements.remove(statementNumber);
    }

    public String getFullStatement(int statementNumber) {
        return financialStatements.get(statementNumber).getStatementForList();
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


}
