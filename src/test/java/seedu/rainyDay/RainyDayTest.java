package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.rainyDay.RainyDay.loadFromFile;

import seedu.rainyDay.command.command;
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
    public void parseAddInCommand() {
        try {
            ArrayList<FinancialStatement> statements = new ArrayList<>();
            FinancialReport testReport = new FinancialReport(statements);
            testReport.addStatement(new FinancialStatement("noodles", "in", 5));
            RainyDay.parseUserInput("add -in noodles $5");
            assertEquals(RainyDay.financialReport.getFullStatement(0), testReport.getFullStatement(0));
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }

    @Test
    public void parseAddOutCommand() {
        try {
            ArrayList<FinancialStatement> statements = new ArrayList<>();
            FinancialReport testReport = new FinancialReport(statements);
            testReport.addStatement(new FinancialStatement("noodles", "out", 5));
            RainyDay.parseUserInput("add -out noodles $5");
            assertEquals(RainyDay.financialReport.getFullStatement(0), testReport.getFullStatement(0));
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }

    @Test
    public void parseDeleteCommand() {
        RainyDay.clearFinancialReport();
        command.addFinancialStatement("Ipad", "out", 120);
        command.addFinancialStatement("angpao", "in", 3000);
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport testReport = new FinancialReport(statements);
        testReport.addStatement(new FinancialStatement("Ipad", "out", 120));
        try {
            RainyDay.parseUserInput("delete 2");
            assertEquals(RainyDay.financialReport.getFullStatement(0), testReport.getFullStatement(0));
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
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

        assertEquals(command.generateReport(financialReport), command.generateReport(financialReportData));
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
