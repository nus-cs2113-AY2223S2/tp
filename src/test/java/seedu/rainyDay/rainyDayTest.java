package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rainyDay.rainyDay.addFinancialStatement;
import static seedu.rainyDay.rainyDay.generateReport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialStatement;

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
        ArrayList<FinancialStatement> financialReport = new ArrayList<FinancialStatement>();
        String actualAddStatement = addFinancialStatement("Ipad", "out", 120);
        String expectedAddStatement = "Done, added: out for Ipad, $120";
        assertEquals(expectedAddStatement, actualAddStatement);
    }

    @Test
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
}
