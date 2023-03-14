package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.modules.Storage;
import seedu.rainyDay.modules.UI;

public class DeleteFinancialStatement extends Command{
    int index;
    public DeleteFinancialStatement(int index) {
        this.index = index;
    }

    public void execute() {
        index -= 1;
        UI.printDeletedFinancialStatement(RainyDay.financialReport.getStatementDescription(index));
        RainyDay.financialReport.deleteStatement(index);
        Storage.writeToFile(RainyDay.financialReport, RainyDay.filePath);
    }
}
