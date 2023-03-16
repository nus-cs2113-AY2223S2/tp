package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.modules.Ui;

public class ViewCommand extends Command {

    public ViewCommand() {
    }

    public void execute() {
        if (financialReport.getStatementCount() == 0) {
            Ui.emptyFinancialReport();
            return;
        }
        Ui.acknowledgeViewCommand();
        int totalInflow = 0;
        int totalOutflow = 0;
        for (int i = 0; i < financialReport.getStatementCount(); i += 1) {
            FinancialStatement currentStatement = financialReport.getFinancialStatement(i);
            if (currentStatement.getFlowDirection().equals("in")) {
                totalInflow += currentStatement.getValue();
            } else {
                totalOutflow += currentStatement.getValue();
            }
            Ui.printFinancialStatement(i + 1, currentStatement);
        }
        Ui.printSummary(totalInflow, totalOutflow);
    }
}
