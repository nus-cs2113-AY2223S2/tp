package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.TransportExpenditure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
        EditCommand testEditZeroIndex = new EditCommand(0, "edit 1 d/2023-02-15 a/20 p/Eat Food");
        assertEquals("Edited! Here is the updated list:\n" +
                "1. [Academic] || Date: 15 Feb 2023 || Value: 20.0 || Description: Eat Food\n" +
                "2. [Food] || Date: 21 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                "3. [Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: circle line",
                testEditZeroIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_editCommand_onOneIndex() {
        EditCommand testEditOneIndex = new EditCommand(1, "edit 2 d/2023-02-15 a/20 p/Eat Food");
        assertEquals("Edited! Here is the updated list:\n" +
                "1. [Academic] || Date: 1 Jan 2023 || Value: 2.1 || Description: pen\n" +
                "2. [Food] || Date: 15 Feb 2023 || Value: 20.0 || Description: Eat Food\n"
                + "3. [Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: circle line",
                testEditOneIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_editCommand_onTwoIndex() {
        EditCommand testEditTwoIndex = new EditCommand(2, "edit 3 d/2023-02-15 a/20 p/Eat Food");
        assertEquals("Edited! Here is the updated list:\n" +
                "1. [Academic] || Date: 1 Jan 2023 || Value: 2.1 || Description: pen\n" +
                "2. [Food] || Date: 21 Mar 2023 || Value: 4.5 || Description: chicken rice\n"
                + "3. [Transport] || Date: 15 Feb 2023 || Value: 20.0 || Description: Eat Food",
                testEditTwoIndex.execute(testExpenditures).getCommandResult());
    }

}
