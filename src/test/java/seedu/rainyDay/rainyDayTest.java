package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.rainyDay.rainyDay.addFinancialStatement;
import static seedu.rainyDay.rainyDay.generateReport;
import static seedu.rainyDay.rainyDay.deleteFinancialStatement;
import static seedu.rainyDay.rainyDay.loadFromFile;
import static seedu.rainyDay.rainyDay.writeToFile;

import seedu.rainyDay.data.FinancialStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

class rainyDayTest {
    @Test
    public void sampleTest() {
        Assertions.assertTrue(true);
    }

    @Test
    public void verifyAddStatement() {
        String actualAddStatement = addFinancialStatement("Ipad", "out", 120);
        String expectedAddStatement = "Done, added: out for Ipad, $120";
        assertEquals(expectedAddStatement, actualAddStatement);
    }

    @Test
    public void verifyDeleteStatement() {
        addFinancialStatement("Ipad", "out", 120);
        String actualDeleteStatement = deleteFinancialStatement(1);
        String expectedDeleteStatement = "Done, deleted \"Ipad\" from the financial report";
        assertEquals(expectedDeleteStatement, actualDeleteStatement);
    }

    @Test
    public void verifyReport() {
        ArrayList<FinancialStatement> financialReport = new ArrayList<>();
        String actualReport = generateReport(financialReport);
        String expectedReport = "Your financial report is empty";
        assertEquals(expectedReport, actualReport);

        financialReport.add(new FinancialStatement("Ipad", "out", 120));
        financialReport.add(new FinancialStatement("pork", "out", 5));
        financialReport.add(new FinancialStatement("angpao", "in", 3000));
        actualReport = generateReport(financialReport);
        expectedReport = String.join(System.lineSeparator(), "1. Ipad -$120 (out)", "2. pork -$5 (out)",
                "3. angpao +$3000 (in)" + System.lineSeparator(), "Inflow: $3000", "Outflow: $125",
                "Remaining value: $2875");
        assertEquals(expectedReport, actualReport);
    }

    void writeToFileTest_fileExists() {
        ArrayList<FinancialStatement> financialReport = new ArrayList<>();
        String filePath = "rainyDay.txt";
        rainyDay.writeToFile(financialReport, filePath);
        Assertions.assertTrue(new File(filePath).exists());
    }

    @Test
    void writeToFileTest_contentMatch() throws IOException, ClassNotFoundException {
        ArrayList<FinancialStatement> financialReport = new ArrayList<>();
        String filePath = "rainyDay.txt";
        financialReport.add(new FinancialStatement("noodles", "in", 5));
        rainyDay.writeToFile(financialReport, filePath);

        FileInputStream readData = new FileInputStream(filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);
        @SuppressWarnings("unchecked")
        ArrayList<FinancialStatement> data = (ArrayList<FinancialStatement>) readStream.readObject();
        readStream.close();

        assertEquals(generateReport(financialReport), generateReport(data));
    }

    @Test
    void loadFromFile_emptyFile_IOExceptionThrown() {
        String emptyFilePath = "thisFileDoesNotExist.txt";
        assertThrows(IOException.class, ()->loadFromFile(emptyFilePath));
    }

    @Test
    void loadFromFile_invalidFileType_ClassNotFoundExceptionThrown()  {
        String invalidTypeFilePath = "test files/thisFileTypeIsInvalid.txt";
        assertThrows(IOException.class, ()->loadFromFile(invalidTypeFilePath));
    }



}
