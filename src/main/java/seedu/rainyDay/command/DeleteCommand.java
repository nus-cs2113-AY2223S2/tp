package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.modules.Storage;
import seedu.rainyDay.modules.Ui;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute() {
        index -= 1;
        Ui.printDeletedFinancialStatement(financialReport.getStatementDescription(index));
        financialReport.deleteStatement(index);
        Storage.writeToFile(financialReport, RainyDay.filePath);
    }
}
