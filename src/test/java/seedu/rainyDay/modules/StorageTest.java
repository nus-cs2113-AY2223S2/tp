package seedu.rainyDay.modules;

import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.MonthlyExpenditures;
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.exceptions.RainyDayException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    @Test
    public void writeToFileTest_fileExists() {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport financialReport = new FinancialReport(statements);
        String filePath = "rainyDay.txt";
        SavedData savedData = new SavedData(financialReport);
        Storage.writeToFile(savedData, filePath);
        Assertions.assertTrue(new File(filePath).exists());
    }

    @Test
    public void writeToFileTest_contentMatch() throws IOException, RainyDayException {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport financialReport = new FinancialReport(statements);
        financialReport.setReportOwner("bob");

        HashMap<Integer, Double> expenditures = new HashMap<>();
        MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);

        financialReport.addStatement(
                new FinancialStatement("noodles", "in", 5, "Default", LocalDate.now()));
        String filePath = "rainyDay.txt";

        HashMap<String, String> shortcutCommands = new HashMap<>();
        shortcutCommands.put("a", "add -out noodles $5");
        shortcutCommands.put("v", "view");

        SavedData savedData = new SavedData(financialReport, shortcutCommands);
        Storage.writeToFile(savedData, filePath);

        SavedData savedDataLoaded = Storage.loadFromFile(filePath);
        FinancialReport loadedFinancialReport = savedDataLoaded.getFinancialReport();
        System.out.println(loadedFinancialReport.getFullStatement(0));
        System.out.println(loadedFinancialReport.getFullStatement(0));
        assertEquals(loadedFinancialReport.getFullStatement(0),
                loadedFinancialReport.getFullStatement(0));

        HashMap<String, String> loadedShortcutCommands = savedDataLoaded.getShortcutCommands();
        assertEquals(loadedShortcutCommands, shortcutCommands);
    }

    @Test
    public void loadFromFile_emptyFile_iOExceptionThrown() {
        String emptyFilePath = "thisFileDoesNotExist.txt";
        assertThrows(IOException.class, () -> Storage.loadFromFile(emptyFilePath));
    }

    @Test
    public void loadFromFile_invalidFileType_JsonParseExceptionThrown() {
        String invalidTypeFilePath = "test files/thisFileTypeIsInvalid.txt";
        assertThrows(JsonParseException.class, () -> Storage.loadFromFile(invalidTypeFilePath));
    }
}
