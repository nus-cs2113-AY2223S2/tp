package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.modules.UI;

public class GenerateReport extends Command {
    private FinancialReport financialReport;

    public GenerateReport(FinancialReport financialReport) {
        this.financialReport = financialReport;
    }

    public void execute() {
        if (financialReport.getStatementCount() == 0) {
            UI.emptyFinancialReport();
            return;
        }
        UI.acknowledgeViewCommand();
        int totalInflow = 0;
        int totalOutflow = 0;
        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
            if (financialReport.getStatementDirection(i).equals("in")) {
                totalInflow += financialReport.getStatementValue(i);
            } else {
                totalOutflow += financialReport.getStatementValue(i);
            }
            int index = i + 1;
            String financialStatement = String.format("%d. %s", index,
                    financialReport.getFullStatement(i));
            UI.printFinancialStatement(financialStatement);
        }
        UI.printSummary(totalInflow, totalOutflow);
    }
}
