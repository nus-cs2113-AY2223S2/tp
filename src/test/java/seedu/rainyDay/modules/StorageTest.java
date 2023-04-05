package seedu.rainyDay.modules;

import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.exceptions.RainyDayException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
        financialReport.addStatement(
                new FinancialStatement("noodles", "in", 5, "Default", LocalDate.now()));
        String filePath = "rainyDay.txt";

        SavedData savedData = new SavedData(financialReport);
        Storage.writeToFile(savedData, filePath);
        SavedData savedDataLoaded = Storage.loadFromFile(filePath);

        System.out.println(savedData.getFinancialReport().getFullStatement(0));
        System.out.println(savedDataLoaded.getFinancialReport().getFullStatement(0));
        assertEquals(savedData.getFinancialReport().getFullStatement(0),
                savedDataLoaded.getFinancialReport().getFullStatement(0));
    }

    @Test
    public void loadFromFile_emptyFile_iOExceptionThrown() {
        String emptyFilePath = "thisFileDoesNotExist.txt";
        assertThrows(IOException.class, () -> Storage.loadFromFile(emptyFilePath));
    }

    @Test
    public void loadFromFile_invalidFileType_classNotFoundExceptionThrown() {
        String invalidTypeFilePath = "test files/thisFileTypeIsInvalid.txt";
        assertThrows(JsonParseException.class, () -> Storage.loadFromFile(invalidTypeFilePath));
    }
}
