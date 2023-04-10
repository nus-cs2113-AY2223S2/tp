package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransportExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_transportExpenditureCommand_execute() {
        TransportExpenditureCommand testTransportExpenditureCommand = new TransportExpenditureCommand(
                "MRT",
                2.1,
                LocalDate.parse("2023-03-21"));
        assertEquals("Added transport expenditure: " +
                        "[Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: MRT",
                testTransportExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: MRT",
                testExpenditures.toString());
    }

    @Test
    public void test_transportExpenditureCommand_executeWithExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new AcademicExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        TransportExpenditureCommand testTransportExpenditureCommand = new TransportExpenditureCommand(
                "MRT",
                2.1,
                LocalDate.parse("2023-03-21"));
        assertEquals("Added transport expenditure: " +
                        "[Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: MRT",
                testTransportExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 1 Aug 2021 || Value: 1500.0 || Description: laptop\n" +
                        "2. [Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: MRT",
                testExpenditures.toString());
    }

}
