package seedu.rainyDay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
        String filePath = "rainyDay.txt";
        financialReport.addStatement(new FinancialStatement("noodles", "in", 5));
        Storage.writeToFile(financialReport, filePath);

        FileInputStream readData = new FileInputStream(filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);
        @SuppressWarnings("unchecked")
        ArrayList<FinancialStatement> data = (ArrayList<FinancialStatement>) readStream.readObject();
        FinancialReport financialReportData = new FinancialReport(data);
        readStream.close();

        assertEquals(Command.generateReport(financialReport), Command.generateReport(financialReportData));
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
