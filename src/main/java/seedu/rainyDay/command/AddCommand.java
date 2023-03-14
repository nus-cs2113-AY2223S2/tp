package seedu.rainyDay.command;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.modules.Storage;
import seedu.rainyDay.modules.UI;

public class AddFinancialStatement extends Command {
    private final String description;
    private final String flowDirection;
    private final int value;

    public AddFinancialStatement(String description, String flowDirection, int value) {
        this.description = description;
        this.flowDirection = flowDirection;
        this.value = value;
    }

    @Override
    public void execute() {
        int totalStatementCount = RainyDay.financialReport.getStatementCount();

        FinancialStatement currentFinancialStatement = new FinancialStatement(description, flowDirection, value);
        RainyDay.financialReport.addStatement(currentFinancialStatement);

        assert totalStatementCount + 1 == RainyDay.financialReport.getStatementCount() : "statement count mismatch";

        UI.printAddedFinancialStatement(currentFinancialStatement);
        Storage.writeToFile(RainyDay.financialReport, RainyDay.filePath);
    }
}
