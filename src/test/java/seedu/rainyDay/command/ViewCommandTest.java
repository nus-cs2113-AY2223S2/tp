package seedu.rainyDay.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.MonthlyExpenditures;
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.data.UserData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewCommandTest {

    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);
    SavedData savedData = new SavedData(financialReport);
    UserData userData = new UserData(savedData, monthlyExpenditures);

    @Test
    public void viewAllEmptyReport() {
        ViewCommand viewList = new ViewCommand(LocalDate.now(), LocalDate.now(), false, true);
        viewList.setData(userData);
        String expectedReport = "Your financial report is completely empty!";
        assertEquals(expectedReport, viewList.execute().output);
    }

    @Test
    public void viewEmptyReport() {
        ViewCommand viewList = new ViewCommand(LocalDate.now(), LocalDate.now(), false, false);
        viewList.setData(userData);
        String expectedReport = String.format("Your financial report is empty for %s till %s",
                LocalDate.now(), LocalDate.now());
        assertEquals(expectedReport, viewList.execute().output);
    }

    @Test
    public void sortingByValueTest() {
        financialReport.clearReport();
        financialReport.addStatement(new FinancialStatement(
                "Big Outflow (3)", "out", 100, "test", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Big Inflow (1)", "in", 100, "test", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Small Outflow (4)", "out", 1, "test", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Small Inflow (2)", "in", 1, "test", LocalDate.now()));

        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < financialReport.getStatementCount(); i++) {
            indexes.add(i);
        }

        ViewCommand.sortByValue sortingClass = new ViewCommand.sortByValue();
        sortingClass.setReport(savedData.getFinancialReport());
        indexes.sort(sortingClass);

        for (int i = 0; i < financialReport.getStatementCount(); i++) {
            for (int j = 0; j < i; j++) {
                FinancialStatement smaller = financialReport.getFinancialStatement(indexes.get(j));
                FinancialStatement bigger = financialReport.getFinancialStatement(indexes.get(i));
                Assertions.assertTrue(smaller.getValue() < bigger.getValue() ||
                        (smaller.getFlowSymbol().equals("+")) && (bigger.getFlowSymbol().equals("-")));
            }
        }
    }

    @Test
    public void sortingByDateTest() {
        financialReport.clearReport();
        LocalDate currentDay = LocalDate.now();
        financialReport.addStatement(new FinancialStatement(
                "Future (5)", "in", 1, "test", currentDay.plusDays(1)));
        financialReport.addStatement(new FinancialStatement(
                "Past (1)", "in", 1, "test", currentDay.minusDays(1)));
        financialReport.addStatement(new FinancialStatement(
                "Present (3)", "in", 1, "test", currentDay));
        financialReport.addStatement(new FinancialStatement(
                "Present (4)", "in", 1, "test", currentDay));
        financialReport.addStatement(new FinancialStatement(
                "Past (2)", "in", 1, "test", currentDay.minusDays(1)));
        financialReport.addStatement(new FinancialStatement(
                "Future (6)", "in", 1, "test", currentDay.plusDays(1)));

        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < financialReport.getStatementCount(); i++) {
            indexes.add(i);
        }

        ViewCommand.sortByDate sortingClass = new ViewCommand.sortByDate();
        sortingClass.setReport(savedData.getFinancialReport());
        indexes.sort(sortingClass);

        for (int i = 0; i < financialReport.getStatementCount(); i++) {
            for (int j = 0; j < i; j++) {
                FinancialStatement smaller = financialReport.getFinancialStatement(indexes.get(j));
                FinancialStatement bigger = financialReport.getFinancialStatement(indexes.get(i));
                Assertions.assertFalse(smaller.getDate().isAfter(bigger.getDate()));
            }
        }
    }
}
