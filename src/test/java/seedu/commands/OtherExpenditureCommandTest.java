package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OtherExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_otherExpenditureCommand_execute() {
        OtherExpenditureCommand testOtherExpenditureCommand = new OtherExpenditureCommand(
                "special floor wipes",
                2,
                LocalDate.parse("2023-03-21"));
        assertEquals("Added other expenditure [Other] || Date: 2023-03-21 || Value: 2.0 || " +
                        "Description: special floor wipes",
                testOtherExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Other] || Date: 2023-03-21 || Value: 2.0 || Description: special floor wipes",
                testExpenditures.toString());
    }

    @Test
    public void test_otherExpenditureCommand_execute_withExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new AcademicExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        OtherExpenditureCommand testOtherExpenditureCommand = new OtherExpenditureCommand(
                "special floor wipes",
                2,
                LocalDate.parse("2023-03-21"));
        assertEquals("Added other expenditure [Other] || Date: 2023-03-21 || Value: 2.0 || " +
                        "Description: special floor wipes",
                testOtherExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 2021-08-01 || Value: 1500.0 || Description: laptop\n" +
                        "2. [Other] || Date: 2023-03-21 || Value: 2.0 || Description: special floor wipes",
                testExpenditures.toString());
    }
}