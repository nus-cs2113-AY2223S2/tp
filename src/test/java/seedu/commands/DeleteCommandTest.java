package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.TransportExpenditure;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @BeforeEach
    public void setUpExpenditureList() {
        testExpenditures.addExpenditure(
                new AcademicExpenditure("pen", 2.10, LocalDate.parse("2023-01-01")));
        testExpenditures.addExpenditure(
                new FoodExpenditure("chicken rice", 4.50, LocalDate.parse("2023-03-21")));
        testExpenditures.addExpenditure(
                new TransportExpenditure("circle line", 2.10, LocalDate.parse("2023-03-21")));
    }

    @Test
    public void test_deleteCommand_onZeroIndex() {
        DeleteCommand testDeleteZeroIndex = new DeleteCommand(0);
        assertEquals("Entry has been deleted\n" +
                "Here is your updated list: \n" +
                "1. [Food] || Date: 2023-03-21 || Value: 4.5 || Description: chicken rice\n" +
                "2. [Transport] || Date: 2023-03-21 || Value: 2.1 || Description: circle line",
                testDeleteZeroIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_deleteCommand_onOneIndex() {
        DeleteCommand testDeleteOneIndex = new DeleteCommand(1);
        assertEquals("Entry has been deleted\n" +
                        "Here is your updated list: \n" +
                        "1. [Academic] || Date: 2023-01-01 || Value: 2.1 || Description: pen\n" +
                        "2. [Transport] || Date: 2023-03-21 || Value: 2.1 || Description: circle line",
                testDeleteOneIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_deleteCommand_onTwoIndex() {
        DeleteCommand testDeleteTwoIndex = new DeleteCommand(2);
        assertEquals("Entry has been deleted\n" +
                        "Here is your updated list: \n" +
                        "1. [Academic] || Date: 2023-01-01 || Value: 2.1 || Description: pen\n" +
                        "2. [Food] || Date: 2023-03-21 || Value: 4.5 || Description: chicken rice",
                testDeleteTwoIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_deleteCommand_onAllExpenditures() {
        DeleteCommand testDeleteZeroIndex = new DeleteCommand(0);
        testDeleteZeroIndex.execute(testExpenditures);
        testDeleteZeroIndex.execute(testExpenditures);
        testDeleteZeroIndex.execute(testExpenditures);
        assertEquals("", testExpenditures.toString());
    }

    @Test
    public void test_deleteCommand_afterDeletingAllExpenditures() {
        DeleteCommand testDeleteZeroIndex = new DeleteCommand(0);
        testDeleteZeroIndex.execute(testExpenditures);
        testDeleteZeroIndex.execute(testExpenditures);
        testDeleteZeroIndex.execute(testExpenditures);
        assertEquals("Index is out of bounds or negative",
                testDeleteZeroIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_deleteCommand_onOutOfBoundsIndex() {
        DeleteCommand testDeleteOutOfBoundsIndex = new DeleteCommand(5);
        assertEquals("Index is out of bounds or negative",
                testDeleteOutOfBoundsIndex.execute(testExpenditures).getCommandResult());
    }

}
