package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.Storage;
import seedu.rainyDay.UI;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

public class Command {

    public static final String COMMAND_ADD = "add";

    public static final String COMMAND_DELETE = "delete";

    public static final String COMMAND_VIEW = "view";

    public static final String COMMAND_HELP = "help";

    public static final String COMMAND_EXIT = "bye";

    public static void addFinancialStatement(String description, String flowDirection, int value) {
        FinancialStatement currentFinancialStatement = new FinancialStatement(description, flowDirection, value);
        RainyDay.financialReport.addStatement(currentFinancialStatement);
        //int statementCount = RainyDay.financialReport.getStatementCount();
        //String direction = RainyDay.financialReport.getStatementDirection(statementCount - 1);
        //what was this above 2 lines for?
        UI.printAddedFinancialStatement(currentFinancialStatement);
        Storage.writeToFile(RainyDay.financialReport, RainyDay.filePath);
    }

    public static void deleteFinancialStatement(int index) {
        index -= 1;
        UI.printDeletedFinancialStatement(RainyDay.financialReport.getStatementDescription(index));
        RainyDay.financialReport.deleteStatement(index);
        Storage.writeToFile(RainyDay.financialReport, RainyDay.filePath);
    }

    public static String generateReport(FinancialReport financialReport) {
        if (financialReport.getStatementCount() == 0) {
            System.out.println("Your financial report is empty");
            return "x";
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
        System.out.println(report);
        return report;
    }
}
