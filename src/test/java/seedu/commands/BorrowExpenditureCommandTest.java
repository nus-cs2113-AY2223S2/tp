package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_borrowExpenditureCommand_execute() {
        BorrowExpenditureCommand testBorrowExpenditureCommand = new BorrowExpenditureCommand(
                "loan",
                "DBS",
                20000,
                LocalDate.parse("2021-08-01"),
                LocalDate.parse("2025-05-05"));
        assertEquals("Added borrow expenditure: [Borrow] || Borrowed from: DBS || Date: 1 Aug 2021 " +
                        "|| Value: 20000.0 || Description: loan || By: 5 May 2025",
                testBorrowExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Borrow] || Borrowed from: DBS || Date: 1 Aug 2021 || Value: 20000.0 " +
                        "|| Description: loan || By: 5 May 2025", testExpenditures.toString());
    }

    @Test
    public void test_borrowExpenditureCommand_executeWithExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new AcademicExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        BorrowExpenditureCommand testBorrowExpenditureCommand = new BorrowExpenditureCommand(
                "loan",
                "DBS",
                20000,
                LocalDate.parse("2021-08-01"),
                LocalDate.parse("2025-05-05"));
        assertEquals("Added borrow expenditure: [Borrow] || Borrowed from: DBS || Date: 1 Aug 2021 " +
                        "|| Value: 20000.0 || Description: loan || By: 5 May 2025",
                testBorrowExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 1 Aug 2021 || Value: 1500.0 || Description: laptop\n" +
                        "2. [Borrow] || Borrowed from: DBS || Date: 1 Aug 2021 || Value: 20000.0 || " +
                        "Description: loan || By: 5 May 2025",
                testExpenditures.toString());
    }
}
