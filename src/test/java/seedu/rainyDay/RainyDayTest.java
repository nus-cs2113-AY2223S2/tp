package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.rainyDay.RainyDay.loadFromFile;

import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

class RainyDayTest {

    @Test
    public void addFinancialStatement() {
        String actualAddStatement = RainyDay.addFinancialStatement("Ipad", "out", 120);
        String expectedAddStatement = "Done, added: out for Ipad, $120";
        assertEquals(expectedAddStatement, actualAddStatement);

        actualAddStatement = RainyDay.addFinancialStatement("angpao", "in", 3000);
        expectedAddStatement = "Done, added: in for angpao, $3000";
        assertEquals(expectedAddStatement, actualAddStatement);
    }

    @Test
    public void deleteFinancialStatement() {
        RainyDay.clearFinancialReport();
        RainyDay.addFinancialStatement("Ipad", "out", 120);
        RainyDay.addFinancialStatement("angpao", "in", 3000);
        String actualDeleteStatement = RainyDay.deleteFinancialStatement(1);
        String expectedDeleteStatement = "Done, deleted \"Ipad\" from the financial report";
        assertEquals(expectedDeleteStatement, actualDeleteStatement);

        actualDeleteStatement = RainyDay.deleteFinancialStatement(1);
        expectedDeleteStatement = "Done, deleted \"angpao\" from the financial report";
        assertEquals(expectedDeleteStatement, actualDeleteStatement);
    }

    @Test
    public void generateReport() {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport financialReport = new FinancialReport(statements);
        String actualReport = RainyDay.generateReport(financialReport);
        String expectedReport = "Your financial report is empty";
        assertEquals(expectedReport, actualReport);

        financialReport.addStatement(new FinancialStatement("Ipad", "out", 120));
        financialReport.addStatement(new FinancialStatement("pork", "out", 5));
        financialReport.addStatement(new FinancialStatement("angpao", "in", 3000));
        actualReport = RainyDay.generateReport(financialReport);
        expectedReport = String.join(System.lineSeparator(), "1. Ipad -$120 (out)", "2. pork -$5 (out)",
                "3. angpao +$3000 (in)" + System.lineSeparator(), "Inflow: $3000", "Outflow: $125",
                "Remaining value: $2875");
        assertEquals(expectedReport, actualReport);
    }

    public void writeToFileTest_fileExists() {

        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport financialReport = new FinancialReport(statements);
        String filePath = "rainyDay.txt";
        RainyDay.writeToFile(financialReport, filePath);
        Assertions.assertTrue(new File(filePath).exists());
    }

    @Test
    public void writeToFileTest_contentMatch() throws IOException, ClassNotFoundException {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport financialReport = new FinancialReport(statements);
        String filePath = "rainyDay.txt";
        financialReport.addStatement(new FinancialStatement("noodles", "in", 5));
        RainyDay.writeToFile(financialReport, filePath);

        FileInputStream readData = new FileInputStream(filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);
        @SuppressWarnings("unchecked")
        ArrayList<FinancialStatement> data = (ArrayList<FinancialStatement>) readStream.readObject();
        FinancialReport financialReportData = new FinancialReport(data);
        readStream.close();

        assertEquals(RainyDay.generateReport(financialReport), RainyDay.generateReport(financialReportData));
    }

    @Test
    public void loadFromFile_emptyFile_iOExceptionThrown() {
        String emptyFilePath = "thisFileDoesNotExist.txt";
        assertThrows(IOException.class, () -> loadFromFile(emptyFilePath));
    }

    @Test
    public void loadFromFile_invalidFileType_classNotFoundExceptionThrown() {
        String invalidTypeFilePath = "test files/thisFileTypeIsInvalid.txt";
        assertThrows(IOException.class, () -> loadFromFile(invalidTypeFilePath));
    }

}
