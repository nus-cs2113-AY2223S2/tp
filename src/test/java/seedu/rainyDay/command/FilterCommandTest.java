package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.RainyDay;
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

public class FilterCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);
    UserData userData = new UserData(financialReport);
    AllData allData = new AllData(userData, monthlyExpenditures);
    ArrayList<String> filterFlagAndField = new ArrayList<>();
    String found = "Here are the list of matching items!";
    String notFound = "We could not find any matches for your description in your report";

    @Test
    public void filterSingleFlag() {
        RainyDay.userData = allData.getUserData();

        financialReport.addStatement(new FinancialStatement(
                "Chicken rice", "out", 5, "Food",
                LocalDate.parse("01/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        financialReport.addStatement(new FinancialStatement(
                "Fried chicken KFC meal", "out", 20, "Food and Drinks", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Allowance", "in", 5000, "Allowance", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Hongpao", "in", 2000, "Chinese New Year",
                LocalDate.parse("01/02/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        financialReport.addStatement(new FinancialStatement(
                "Mixed rice", "out", 6, "Food", LocalDate.now()));

        filterFlagAndField.add("-d");
        filterFlagAndField.add("chicken");
        FilterCommand filterCommand = new FilterCommand(filterFlagAndField);
        filterCommand.setData(allData);
        assertEquals("We found 1 matching item!", filterCommand.execute().output);

        filterFlagAndField.clear();
        filterFlagAndField.add("-c");
        filterFlagAndField.add("Food");
        filterCommand = new FilterCommand(filterFlagAndField);
        filterCommand.setData(allData);
        assertEquals("We found 3 matching items!", filterCommand.execute().output);

        filterFlagAndField.clear();
        filterFlagAndField.add("-date");
        filterFlagAndField.add("01/02/2023");
        filterCommand = new FilterCommand(filterFlagAndField);
        assertEquals("We found 1 matching item!", filterCommand.execute().output);

        filterFlagAndField.clear();
        filterFlagAndField.add("-in");
        filterCommand = new FilterCommand(filterFlagAndField);
        assertEquals("We found 2 matching items!", filterCommand.execute().output);

        filterFlagAndField.clear();
        filterFlagAndField.add("-out");
        filterCommand = new FilterCommand(filterFlagAndField);
        assertEquals("We found 3 matching items!", filterCommand.execute().output);
    }

    @Test
    public void filterMultipleFlags() {
        RainyDay.userData = allData.getUserData();

        financialReport.addStatement(new FinancialStatement(
                "Chicken rice", "out", 5, "Food",
                LocalDate.parse("01/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        financialReport.addStatement(new FinancialStatement(
                "Fried chicken KFC meal", "out", 20, "Food and Drinks", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Allowance", "in", 5000, "Allowance", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Hongpao", "in", 2000, "Chinese New Year",
                LocalDate.parse("01/02/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        financialReport.addStatement(new FinancialStatement(
                "Mixed rice", "out", 6, "Food",
                LocalDate.parse("01/04/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        filterFlagAndField.add("-out");
        filterFlagAndField.add("-d");
        filterFlagAndField.add("rice");
        filterFlagAndField.add("-c");
        filterFlagAndField.add("Food");
        filterFlagAndField.add("-date");
        filterFlagAndField.add("01/04/2023");
        FilterCommand filterCommand = new FilterCommand(filterFlagAndField);
        filterCommand.setData(allData);
        assertEquals("We found 2 matching items!", filterCommand.execute().output);

        filterFlagAndField.clear();
        filterFlagAndField.add("-in");
        filterFlagAndField.add("-date");
        filterFlagAndField.add("01/02/2023");
        assertEquals("We found 1 matching item!", filterCommand.execute().output);

        filterFlagAndField.clear();
        filterFlagAndField.add("-out");
        filterFlagAndField.add("-date");
        filterFlagAndField.add("01/02/2023");
        assertEquals("We could not find any matches for your description in your report",
                filterCommand.execute().output);
    }
}
