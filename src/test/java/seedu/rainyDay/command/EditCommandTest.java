package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.AllData;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.MonthlyExpenditures;
import seedu.rainyDay.data.UserData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);
    UserData userData = new UserData(financialReport);
    AllData allData = new AllData(userData, monthlyExpenditures);
    ArrayList<String> editFlagAndField = new ArrayList<>();

    @Test
    public void editSingleFlag() {
        financialReport.addStatement(new FinancialStatement(
                "Chicken rice", "out", 5, "Food", LocalDate.now()));
        editFlagAndField.add("-in");
        EditCommand editCommand = new EditCommand(1, editFlagAndField);
        editCommand.setData(allData);
        editCommand.execute();
        assertEquals(new FinancialStatement("Chicken rice", "in", 5, "Food",
                LocalDate.now()).getFullStatement(), financialReport.getFinancialStatement(0).getFullStatement());

        editFlagAndField.clear();
        editFlagAndField.add("-d");
        editFlagAndField.add("Fried Chicken");
        editCommand = new EditCommand(1, editFlagAndField);
        editCommand.setData(allData);
        editCommand.execute();
        assertEquals(new FinancialStatement("Fried Chicken", "in", 5, "Food",
                LocalDate.now()).getFullStatement(), financialReport.getFinancialStatement(0).getFullStatement());

        editFlagAndField.clear();
        editFlagAndField.add("-v");
        editFlagAndField.add("15");
        editCommand = new EditCommand(1, editFlagAndField);
        editCommand.setData(allData);
        editCommand.execute();
        assertEquals(new FinancialStatement("Fried Chicken", "in", 15, "Food",
                LocalDate.now()).getFullStatement(), financialReport.getFinancialStatement(0).getFullStatement());

        editFlagAndField.clear();
        editFlagAndField.add("-c");
        editFlagAndField.add("Fast Food");
        editCommand = new EditCommand(1, editFlagAndField);
        editCommand.setData(allData);
        editCommand.execute();
        assertEquals(new FinancialStatement("Fried Chicken", "in", 15, "Fast Food",
                LocalDate.now()).getFullStatement(), financialReport.getFinancialStatement(0).getFullStatement());

        editFlagAndField.clear();
        editFlagAndField.add("-date");
        editFlagAndField.add("01/04/2023");
        editCommand = new EditCommand(1, editFlagAndField);
        editCommand.setData(allData);
        editCommand.execute();
        assertEquals(new FinancialStatement("Fried Chicken", "in", 15, "Fast Food",
                        LocalDate.parse("01/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))).getFullStatement(),
                financialReport.getFinancialStatement(0).getFullStatement());

        editFlagAndField.clear();
        editFlagAndField.add("-date");
        editFlagAndField.add("01/04/2023");
        editCommand = new EditCommand(1, editFlagAndField);
        editCommand.setData(allData);
        editCommand.execute();
        assertEquals(new FinancialStatement("Fried Chicken", "in", 15, "Fast Food",
                        LocalDate.parse("01/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))).getFullStatement(),
                financialReport.getFinancialStatement(0).getFullStatement());

        editFlagAndField.clear();
        editFlagAndField.add("-out");
        editFlagAndField.add("-d");
        editFlagAndField.add("Fried Chicken Arnold");
        editFlagAndField.add("-v");
        editFlagAndField.add("12.50");
        editFlagAndField.add("-date");
        editFlagAndField.add("01/04/2023");
        editCommand = new EditCommand(1, editFlagAndField);
        editCommand.setData(allData);
        editCommand.execute();
        assertEquals(new FinancialStatement("Fried Chicken Arnold", "out", 12.50, "Fast Food",
                        LocalDate.parse("01/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))).getFullStatement(),
                financialReport.getFinancialStatement(0).getFullStatement());
    }
}
