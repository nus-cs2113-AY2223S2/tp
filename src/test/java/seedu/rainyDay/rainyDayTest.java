package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.rainyDay.rainyDay.loadFromFile;

import seedu.rainyDay.data.FinancialReport;
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
    public void addFinancialStatement() {
        String actualAddStatement = rainyDay.addFinancialStatement("Ipad", "out", 120);
        String expectedAddStatement = "Done, added: out for Ipad, $120";
        assertEquals(expectedAddStatement, actualAddStatement);

        actualAddStatement = rainyDay.addFinancialStatement("angpao", "in", 3000);
        expectedAddStatement = "Done, added: in for angpao, $3000";
        assertEquals(expectedAddStatement, actualAddStatement);
    }

    @Test
    public void deleteFinancialStatement() {
        rainyDay.clearFinancialReport();
        rainyDay.addFinancialStatement("Ipad", "out", 120);
        rainyDay.addFinancialStatement("angpao", "in", 3000);
        String actualDeleteStatement = rainyDay.deleteFinancialStatement(1);
        String expectedDeleteStatement = "Done, deleted \"Ipad\" from the financial report";
        assertEquals(expectedDeleteStatement, actualDeleteStatement);

        actualDeleteStatement = rainyDay.deleteFinancialStatement(1);
        expectedDeleteStatement = "Done, deleted \"angpao\" from the financial report";
        assertEquals(expectedDeleteStatement, actualDeleteStatement);
    }

    @Test
    public void generateReport() {
        ArrayList<FinancialStatement> statements = new ArrayList<FinancialStatement>();
        FinancialReport financialReport = new FinancialReport(statements);
        String actualReport = rainyDay.generateReport(financialReport);
        String expectedReport = "Your financial report is empty";
        assertEquals(expectedReport, actualReport);

        financialReport.addStatement(new FinancialStatement("Ipad", "out", 120));
        financialReport.addStatement(new FinancialStatement("pork", "out", 5));
        financialReport.addStatement(new FinancialStatement("angpao", "in", 3000));
        actualReport = rainyDay.generateReport(financialReport);
        expectedReport = String.join(System.lineSeparator(), "1. Ipad -$120 (out)", "2. pork -$5 (out)",
                "3. angpao +$3000 (in)" + System.lineSeparator(), "Inflow: $3000", "Outflow: $125",
                "Remaining value: $2875");
        assertEquals(expectedReport, actualReport);
    }

    void writeToFileTest_fileExists() {

        ArrayList<FinancialStatement> statements = new ArrayList<FinancialStatement>();
        FinancialReport financialReport = new FinancialReport(statements);
        String filePath = "rainyDay.txt";
        rainyDay.writeToFile(financialReport, filePath);
        Assertions.assertTrue(new File(filePath).exists());
    }

    @Test
    void writeToFileTest_contentMatch() throws IOException, ClassNotFoundException {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport financialReport = new FinancialReport(statements);
        String filePath = "rainyDay.txt";
        financialReport.addStatement(new FinancialStatement("noodles", "in", 5));
        rainyDay.writeToFile(financialReport, filePath);

        FileInputStream readData = new FileInputStream(filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);
        @SuppressWarnings("unchecked")
        ArrayList<FinancialStatement> data = (ArrayList<FinancialStatement>) readStream.readObject();
        FinancialReport financialReportData = new FinancialReport(data);
        readStream.close();

        assertEquals(rainyDay.generateReport(financialReport), rainyDay.generateReport(financialReportData));
    }

    @Test
    void loadFromFile_emptyFile_IOExceptionThrown() {
        String emptyFilePath = "thisFileDoesNotExist.txt";
        assertThrows(IOException.class, () -> loadFromFile(emptyFilePath));
    }

    @Test
    void loadFromFile_invalidFileType_ClassNotFoundExceptionThrown() {
        String invalidTypeFilePath = "test files/thisFileTypeIsInvalid.txt";
        assertThrows(IOException.class, () -> loadFromFile(invalidTypeFilePath));
    }

}
