package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);

    @Test
    public void execute() {
        AddCommand addFirstCommand = new AddCommand("Ipad", "out", 120, "Default");
        addFirstCommand.setData(financialReport);
        addFirstCommand.execute();
        AddCommand addSecondCommand = new AddCommand("angpao", "in", 3000, "Default");
        addSecondCommand.setData(financialReport);
        addSecondCommand.execute();

        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.setData(financialReport);
        CommandResult result = deleteCommand.execute();
        String expectedDeleteStatement = "Done, deleted \"Ipad\" from the financial report";
        assertEquals(expectedDeleteStatement, result.output);
        System.out.println(result.output);

        DeleteCommand deleteSecondCommand = new DeleteCommand(1);
        deleteSecondCommand.setData(financialReport);
        CommandResult secondResult = deleteSecondCommand.execute();
        expectedDeleteStatement = "Done, deleted \"angpao\" from the financial report";
        assertEquals(expectedDeleteStatement, secondResult.output);
    }
}
