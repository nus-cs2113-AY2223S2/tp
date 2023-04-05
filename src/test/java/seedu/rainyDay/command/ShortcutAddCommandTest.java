package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.MonthlyExpenditures;
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.data.UserData;
import seedu.rainyDay.exceptions.RainyDayException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortcutAddCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);
    SavedData savedData = new SavedData(financialReport);
    UserData userData = new UserData(savedData, monthlyExpenditures);

    @Test
    public void execute_contentMatch() throws RainyDayException {
        ShortcutAddCommand shortcutAddCommand = new ShortcutAddCommand("key", "value");
        shortcutAddCommand.setData(userData);
        shortcutAddCommand.execute();

        HashMap<String, String> shortcutCommands = new HashMap<>();
        shortcutCommands.put("key", "value");

        assertEquals(shortcutCommands, savedData.getShortcutCommands());
    }
}