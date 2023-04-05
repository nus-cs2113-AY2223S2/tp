package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.MonthlyExpenditures;
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.data.UserData;
import seedu.rainyDay.exceptions.RainyDayException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortcutDeleteCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);
    SavedData savedData = new SavedData(financialReport);
    UserData userData = new UserData(savedData, monthlyExpenditures);

    @Test
    public void execute_deleteKeyExists_contentMatch() throws RainyDayException {
        HashMap<String, String> shortcutCommands = savedData.getShortcutCommands();
        shortcutCommands.put("key that exists", "value");

        ShortcutDeleteCommand shortcutDeleteCommand = new ShortcutDeleteCommand("key that exists");
        shortcutDeleteCommand.setData(userData);
        shortcutDeleteCommand.execute();

        HashMap<String, String> emptyShortcutCommands = new HashMap<>();

        assertEquals(emptyShortcutCommands, savedData.getShortcutCommands());
    }

    @Test
    public void execute_deleteKeyDoesNotExists_exceptionThrown() throws RainyDayException {
        ShortcutDeleteCommand shortcutDeleteCommand = new ShortcutDeleteCommand("non-existent key");
        shortcutDeleteCommand.setData(userData);
        assertThrows(RainyDayException.class, () -> shortcutDeleteCommand.execute());
    }
}