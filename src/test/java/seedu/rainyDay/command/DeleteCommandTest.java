package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.UserData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    HashMap<Integer, Double> monthlyExpenditures = new HashMap<>();
    FinancialReport financialReport = new FinancialReport(statements, monthlyExpenditures);
    UserData userData = new UserData(financialReport);

    @Test
    public void execute() {
        AddCommand addFirstCommand = new AddCommand("Ipad", "out", 120, "Default",
                LocalDate.now());
        addFirstCommand.setData(userData);
        addFirstCommand.execute();
        AddCommand addSecondCommand = new AddCommand("angpao", "in", 3000, "Default",
                LocalDate.now());
        addSecondCommand.setData(userData);
        addSecondCommand.execute();

        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.setData(userData);
        CommandResult result = deleteCommand.execute();
        String expectedDeleteStatement = "Done, deleted \"Ipad\" from the financial report";
        assertEquals(expectedDeleteStatement, result.output);
        System.out.println(result.output);

        DeleteCommand deleteSecondCommand = new DeleteCommand(1);
        deleteSecondCommand.setData(userData);
        CommandResult secondResult = deleteSecondCommand.execute();
        expectedDeleteStatement = "Done, deleted \"angpao\" from the financial report";
        assertEquals(expectedDeleteStatement, secondResult.output);
    }
}
