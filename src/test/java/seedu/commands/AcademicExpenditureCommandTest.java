package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcademicExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_academicExpenditureCommand_execute() {
        AcademicExpenditureCommand testAcademicExpenditureCommand = new AcademicExpenditureCommand(
                "new laptop",
                1500.5,
                LocalDate.parse("2023-02-01"));
        assertEquals("Added academic expenditure: " +
                        "[Academic] || Date: 2023-02-01 || Value: 1500.5 || Description: new laptop",
                testAcademicExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 2023-02-01 || Value: 1500.5 || Description: new laptop",
                testExpenditures.toString());
    }

    @Test
    public void test_academicExpenditureCommand_executeWithExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new AcademicExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        AcademicExpenditureCommand testAcademicExpenditureCommand = new AcademicExpenditureCommand(
                "new laptop",
                1500.5,
                LocalDate.parse("2023-02-01"));
        assertEquals("Added academic expenditure: " +
                        "[Academic] || Date: 2023-02-01 || Value: 1500.5 || Description: new laptop",
                testAcademicExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 2021-08-01 || Value: 1500.0 || Description: laptop\n" +
                        "2. [Academic] || Date: 2023-02-01 || Value: 1500.5 || Description: new laptop",
                testExpenditures.toString());
    }
}
