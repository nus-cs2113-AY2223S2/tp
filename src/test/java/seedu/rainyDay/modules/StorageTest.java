package seedu.rainyDay.modules;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    @Test
    public void writeToFileTest_fileExists() {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport financialReport = new FinancialReport(statements);
        String filePath = "rainyDay.txt";
        Storage.writeToFile(financialReport, filePath);
        Assertions.assertTrue(new File(filePath).exists());
    }

    @Test
    public void writeToFileTest_contentMatch() throws IOException, ClassNotFoundException {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport financialReport = new FinancialReport(statements);
        financialReport.addStatement(new FinancialStatement("noodles", "in", 5));
        String filePath = "rainyDay.txt";
        Storage.writeToFile(financialReport, filePath);
        FinancialReport financialReportLoaded =Storage.loadFromFile(filePath);
        //ViewCommand viewList = new ViewCommand();
        //viewList.setData(financialReportLoaded);
        //viewList.execute();
        assertEquals(financialReport.getFullStatement(0),
                financialReportLoaded.getFullStatement(0));
    }

    @Test
    public void loadFromFile_emptyFile_iOExceptionThrown() {
        String emptyFilePath = "thisFileDoesNotExist.txt";
        assertThrows(IOException.class, () -> Storage.loadFromFile(emptyFilePath));
    }

    @Test
    public void loadFromFile_invalidFileType_classNotFoundExceptionThrown() {
        String invalidTypeFilePath = "test files/thisFileTypeIsInvalid.txt";
        assertThrows(IOException.class, () -> Storage.loadFromFile(invalidTypeFilePath));
    }
}
