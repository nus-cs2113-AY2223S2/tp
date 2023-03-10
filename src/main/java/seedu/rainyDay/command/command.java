package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

public class command {

    public static String addFinancialStatement(String description, String flowDirection, int value) {
        RainyDay.financialReport.addStatement(new FinancialStatement(description, flowDirection, value));
        String direction = RainyDay.financialReport.getStatementDirection(RainyDay.financialReport.getStatementCount() - 1);
        String addStatement = "Done, added: " + direction + " for " + description + ", $" + value;
        RainyDay.writeToFile(RainyDay.financialReport, RainyDay.filePath);
        return addStatement;
    }

    public static String deleteFinancialStatement(int index) {
        index -= 1;
        String deleteStatement = "Done, deleted \"" + RainyDay.financialReport.getStatementDescription(index)
                + "\" from the financial report";
        RainyDay.financialReport.deleteStatement(index);
        RainyDay.writeToFile(RainyDay.financialReport, RainyDay.filePath);
        return deleteStatement;
    }

    public static String generateReport(FinancialReport financialReport) {
        if (financialReport.getStatementCount() == 0) {
            return "Your financial report is empty";
        }
        int totalInflow = 0;
        int totalOutflow = 0;
        String financialStatements = "";
        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
            if (financialReport.getStatementDirection(i).equals("in")) {
                totalInflow += financialReport.getStatementValue(i);
            } else {
                totalOutflow += financialReport.getStatementValue(i);
            }
            int index = i + 1;
            String financialStatement = String.join("", String.valueOf(index), ". ",
                    financialReport.getFullStatement(i), System.lineSeparator());
            financialStatements = String.join("", financialStatements, financialStatement);
        }
        String inflowInformation = "Inflow: $" + totalInflow;
        String outflowInformation = "Outflow: $" + totalOutflow;
        String remainingValueInformation = "Remaining value: $" + (totalInflow - totalOutflow);
        String report = String.join(System.lineSeparator(), financialStatements, inflowInformation, outflowInformation,
                remainingValueInformation);
        return report;
    }
}
