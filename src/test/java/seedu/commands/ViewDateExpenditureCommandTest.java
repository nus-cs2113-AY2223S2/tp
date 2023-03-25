package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exceptions.NoPaidFieldException;
import seedu.expenditure.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewDateExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @BeforeEach
    public void setUpExpenditureList() {
        testExpenditures.addExpenditure(
                new AcademicExpenditure("pen", 2.10, LocalDate.parse("2023-01-01")));
        testExpenditures.addExpenditure(
                new FoodExpenditure("chicken rice", 4.50, LocalDate.parse("2023-03-21")));
        testExpenditures.addExpenditure(
                new TransportExpenditure("circle line", 2.10, LocalDate.parse("2023-03-21")));
        testExpenditures.addExpenditure(
                new TuitionExpenditure("school", 8100, LocalDate.parse("2023-01-27")));
        testExpenditures.addExpenditure(
                new AccommodationExpenditure("rc", 3000, LocalDate.parse("2023-02-02")));
        testExpenditures.addExpenditure(
                new AcademicExpenditure("NUS", 2.2, LocalDate.parse("2023-02-02")));
    }

    @Test
    public void test_viewDateExpenditureCommand_onNoDate() {
        ViewDateExpenditureCommand testViewDateNoDate = new ViewDateExpenditureCommand("2023-05-01");
        assertEquals("Here are the specified expenditures: \n",
                testViewDateNoDate.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_viewDateExpenditureCommand_onOneDate() {
        ViewDateExpenditureCommand testViewDateOneDate = new ViewDateExpenditureCommand("2023-01-01");
        assertEquals("Here are the specified expenditures: \n" +
                        "1. [Academic] || Date: 2023-01-01 || Value: 2.1 || Description: pen",
                testViewDateOneDate.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_viewDateExpenditureCommand_onTwoDate() {
        ViewDateExpenditureCommand testViewDateTwoDate = new ViewDateExpenditureCommand("2023-02-02");
        assertEquals("Here are the specified expenditures: \n" +
                        "1. [Accommodation] || [ ] || Date: 2023-02-02 || Value: 3000.0 || Description: rc\n" +
                        "2. [Academic] || Date: 2023-02-02 || Value: 2.2 || Description: NUS",
                testViewDateTwoDate.execute(testExpenditures).getCommandResult());
    }
}
