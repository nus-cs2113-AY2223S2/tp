package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.modules.Storage;

import java.io.Serializable;
import java.util.ArrayList;

public class FinancialReport implements Serializable {
    private final ArrayList<FinancialStatement> financialReport;
    private String reportOwner;

    public FinancialReport(ArrayList<FinancialStatement> financialReport) {
        this.financialReport = financialReport;
    }

    public String getReportOwner() {
        if (this.reportOwner == null) {
            return "no name";
        }
        return reportOwner;
    }

    public void setReportOwner(String name) {
        this.reportOwner = name;
        Storage.writeToFile(this, RainyDay.filePath);
    }

    public int getStatementCount() {
        return financialReport.size();
    }

    public void addStatement(FinancialStatement statement) {
        financialReport.add(statement);
        Storage.writeToFile(this, RainyDay.filePath);
    }

    public void deleteStatement(int statementNumber) {
        financialReport.remove(financialReport.get(statementNumber));
        Storage.writeToFile(this, RainyDay.filePath);
    }

    public String getFullStatement(int statementNumber) {
        return financialReport.get(statementNumber).getStatementForList();
    }

    public String getStatementDirection(int statementNumber) {
        return financialReport.get(statementNumber).getFlowDirection();
    }

    public double getStatementValue(int statementNumber) {
        return financialReport.get(statementNumber).getValue();
    }

    public String getStatementFlowSymbol(int statementNumber) {
        return financialReport.get(statementNumber).getFlowSymbol();
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

}
