package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.UserData;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.data.MonthlyExpenditures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeleteCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);
    SavedData savedData = new SavedData(financialReport);
    UserData userData = new UserData(savedData, monthlyExpenditures);

    @Test
    public void execute() {
        AddCommand addCommand = new AddCommand("Ipad", "out", 120, "Default",
                LocalDate.now());
        addCommand.setData(userData);
        addCommand.execute();

        addCommand = new AddCommand("noodles", "out", 3, "Default",
                LocalDate.now());
        addCommand.setData(userData);
        addCommand.execute();

        addCommand = new AddCommand("angpao", "in", 3000, "Default",
                LocalDate.now());
        addCommand.setData(userData);
        addCommand.execute();

        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.setData(userData);
        CommandResult result = deleteCommand.execute();
        String expectedDeleteStatement = "Done, deleted \"Ipad\" from the financial report";
        assertEquals(expectedDeleteStatement, result.output);
        FinancialStatement statement = new FinancialStatement("Ipad", "out", 120,
                "Default", LocalDate.now());
        for (int i = 0; i < userData.getSavedData().getFinancialReport().getStatementCount(); i += 1) {
            assertNotEquals(statement, userData.getSavedData().getStatement(i));
        }

        deleteCommand = new DeleteCommand(2);
        deleteCommand.setData(userData);
        CommandResult secondResult = deleteCommand.execute();
        expectedDeleteStatement = "Done, deleted \"angpao\" from the financial report";
        assertEquals(expectedDeleteStatement, secondResult.output);
        statement = new FinancialStatement("angpao", "in", 3000,
                "Default", LocalDate.now());
        for (int i = 0; i < userData.getSavedData().getFinancialReport().getStatementCount(); i += 1) {
            assertNotEquals(statement, userData.getSavedData().getStatement(i));
        }
    }
}
