package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.MonthlyExpenditures;
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.data.UserData;
import seedu.rainyDay.exceptions.RainyDayException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class ExportCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);
    SavedData savedData = new SavedData(financialReport);
    UserData userData = new UserData(savedData, monthlyExpenditures);
    @Test
    public void ExportToCsvTest_contentMatch() throws IOException, RainyDayException {

        LocalDate date = LocalDate.of(2023, 03, 02);
        financialReport.addStatement(
                new FinancialStatement("noodles", "out", 5, "food", date));
        financialReport.addStatement(
                new FinancialStatement("free money", "in", 10, "miscellaneous", date));
        financialReport.addStatement(
                new FinancialStatement("rice", "out", 7, "food", date));


        ExportCommand exportCommand = new ExportCommand();
        exportCommand.setData(userData);
        exportCommand.execute();

        Path exportedCsv = Paths.get("./data/report.csv");
        Path testCsv = Paths.get("./test files/testcsv.csv");

        List<String> exportedCsvData = Files.readAllLines(exportedCsv);
        List<String> testCsvData = Files.readAllLines(testCsv);

        assertEquals(exportedCsvData, testCsvData);

    }
}
