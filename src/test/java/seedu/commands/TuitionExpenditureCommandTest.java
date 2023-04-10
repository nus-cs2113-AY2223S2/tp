package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TuitionExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_tuitionExpenditureCommand_execute() {
        TuitionExpenditureCommand testTuitionExpenditureCommand = new TuitionExpenditureCommand(
                "NUS Y2S2",
                8500.5,
                LocalDate.parse("2021-01-27"),
                LocalDate.parse("2021-01-27"));
        assertEquals("Added tuition expenditure: " +
                        "[Tuition] || [ ] || Date: 27 Jan 2021 || Value: 8500.5 || Description: NUS Y2S2",
                testTuitionExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Tuition] || [ ] || Date: 27 Jan 2021 || Value: 8500.5 || Description: NUS Y2S2",
                testExpenditures.toString());
    }

    @Test
    public void test_tuitionExpenditureCommand_executeWithExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new AcademicExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        TuitionExpenditureCommand testTuitionExpenditureCommand = new TuitionExpenditureCommand(
                "NUS Y2S2",
                8500.5,
                LocalDate.parse("2021-01-27"),
                LocalDate.parse("2021-01-27"));
        assertEquals("Added tuition expenditure: " +
                        "[Tuition] || [ ] || Date: 27 Jan 2021 || Value: 8500.5 || Description: NUS Y2S2",
                testTuitionExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 1 Aug 2021 || Value: 1500.0 || Description: laptop\n" +
                        "2. [Tuition] || [ ] || Date: 27 Jan 2021 || Value: 8500.5 || Description: NUS Y2S2",
                testExpenditures.toString());
    }
}
