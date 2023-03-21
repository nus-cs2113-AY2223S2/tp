package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.TransportExpenditure;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EditCommandTest {
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
    public void test_editCommand_onZeroIndex() {
        EditCommand testEditZeroIndex = new EditCommand(0,
                LocalDate.parse("2023-02-02"),
                "2.3",
                "g2 black pen");
        assertEquals("Edited! Here is the updated list:\n" +
                        "1. [Academic] || Date: 2023-02-02 || Value: 2.3 || Description: g2 black pen\n" +
                        "2. [Food] || Date: 2023-03-21 || Value: 4.5 || Description: chicken rice\n" +
                        "3. [Transport] || Date: 2023-03-21 || Value: 2.1 || Description: circle line",
                testEditZeroIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_editCommand_onOneIndex() {
        EditCommand testEditOneIndex = new EditCommand(1,
                LocalDate.parse("2023-03-20"),
                "5",
                "char siew roasted pork rice");
        assertEquals("Edited! Here is the updated list:\n" +
                        "1. [Academic] || Date: 2023-01-01 || Value: 2.1 || Description: pen\n" +
                        "2. [Food] || Date: 2023-03-20 || Value: 5.0 || Description: char siew roasted pork rice\n" +
                        "3. [Transport] || Date: 2023-03-21 || Value: 2.1 || Description: circle line",
                testEditOneIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_editCommand_onTwoIndex() {
        EditCommand testEditTwoIndex = new EditCommand(2,
                LocalDate.parse("2023-03-20"),
                "2.8",
                "east west line");
        assertEquals("Edited! Here is the updated list:\n" +
                        "1. [Academic] || Date: 2023-01-01 || Value: 2.1 || Description: pen\n" +
                        "2. [Food] || Date: 2023-03-21 || Value: 4.5 || Description: chicken rice\n" +
                        "3. [Transport] || Date: 2023-03-20 || Value: 2.8 || Description: east west line",
                testEditTwoIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_deleteCommand_onOutOfBoundsIndex() {
        EditCommand testEditOutOfBoundsIndex = new EditCommand(5,
                LocalDate.parse("2023-01-01"),
                "2",
                "out of bounds");
        assertEquals("Failed to edit! Please check the format and try again!",
                testEditOutOfBoundsIndex.execute(testExpenditures).getCommandResult());
    }
}