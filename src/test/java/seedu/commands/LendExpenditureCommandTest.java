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
        assertEquals("Added lend expenditure: [Lend] || Lent to: bombino || Date: 1 Aug 2021 || Value: 200.5 " +
                        "|| Description: for a friend || by: 5 May 2025",
                testLendExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Lend] || Lent to: bombino || Date: 1 Aug 2021 || Value: 200.5 || " +
                "Description: for a friend || by: 5 May 2025", testExpenditures.toString());
    }

    @Test
    public void test_lendExpenditureCommand_executeWithExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new AcademicExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        LendExpenditureCommand testLendExpenditureCommand = new LendExpenditureCommand(
                "for a friend",
                "bombino",
                200.5,
                LocalDate.parse("2021-08-01"),
                LocalDate.parse("2025-05-05"));
        assertEquals("Added lend expenditure: [Lend] || Lent to: bombino || Date: 1 Aug 2021 || Value: 200.5 " +
                        "|| Description: for a friend || by: 5 May 2025",
                testLendExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 1 Aug 2021 || Value: 1500.0 || Description: laptop\n" +
                "2. [Lend] || Lent to: bombino || Date: 1 Aug 2021 || Value: 200.5 || Description: for a friend " +
                "|| by: 5 May 2025", testExpenditures.toString());
    }
}
