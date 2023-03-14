package seedu.rainyDay.command;

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
            if (financialReport.getStatementDirection(i).equals("in")) {
                totalInflow += financialReport.getStatementValue(i);
            } else {
                totalOutflow += financialReport.getStatementValue(i);
            }
            int index = i + 1;
            String financialStatement = String.format("%d. %s", index,
                    financialReport.getFullStatement(i));
            Ui.printFinancialStatement(financialStatement);
        }
        Ui.printSummary(totalInflow, totalOutflow);
    }
}
