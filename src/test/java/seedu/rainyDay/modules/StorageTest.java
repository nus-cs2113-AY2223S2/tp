package seedu.rainyDay.modules;

import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.UserData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    @Test
    public void writeToFileTest_fileExists() {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        HashMap<Integer, Double> monthlyExpenditures = new HashMap<>();
        FinancialReport financialReport = new FinancialReport(statements, monthlyExpenditures);
        String filePath = "rainyDay.txt";
        UserData userData = new UserData(financialReport);
        Storage.writeToFile(userData, filePath);
        Assertions.assertTrue(new File(filePath).exists());
    }

    @Test
    public void writeToFileTest_contentMatch() throws IOException {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        HashMap<Integer, Double> monthlyExpenditures = new HashMap<>();
        FinancialReport financialReport = new FinancialReport(statements, monthlyExpenditures);
        financialReport.addStatement(
                new FinancialStatement("noodles", "in", 5, "Default", null));
        String filePath = "rainyDay.txt";

        UserData userData = new UserData(financialReport);
        Storage.writeToFile(userData, filePath);
        UserData userDataLoaded = Storage.loadFromFile(filePath);

        assertEquals(userData.getFinancialReport().getFullStatement(0),
                userDataLoaded.getFinancialReport().getFullStatement(0));
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
