package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LendExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_lendExpenditureCommand_execute() {
        LendExpenditureCommand testLendExpenditureCommand = new LendExpenditureCommand(
                "for a friend",
                "bombino",
                200.5,
                LocalDate.parse("2021-08-01"),
                LocalDate.parse("2025-05-05"));
        assertEquals("Added lend expenditure: [Lend] || Lent to: bombino || Date: 2021-08-01 || Value: 200.5 " +
                        "|| Description: for a friend || by: 2025-05-05",
                testLendExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Lend] || Lent to: bombino || Date: 2021-08-01 || Value: 200.5 || " +
                "Description: for a friend || by: 2025-05-05", testExpenditures.toString());
    }

    @Test
    public void test_lendExpenditureCommand_execute_withExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new AcademicExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        LendExpenditureCommand testLendExpenditureCommand = new LendExpenditureCommand(
                "for a friend",
                "bombino",
                200.5,
                LocalDate.parse("2021-08-01"),
                LocalDate.parse("2025-05-05"));
        assertEquals("Added lend expenditure: [Lend] || Lent to: bombino || Date: 2021-08-01 || Value: 200.5 " +
                        "|| Description: for a friend || by: 2025-05-05",
                testLendExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 2021-08-01 || Value: 1500.0 || Description: laptop\n" +
                "2. [Lend] || Lent to: bombino || Date: 2021-08-01 || Value: 200.5 || Description: for a friend " +
                "|| by: 2025-05-05", testExpenditures.toString());
    }
}