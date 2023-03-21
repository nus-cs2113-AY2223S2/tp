package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.TransportExpenditure;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ViewExpenditureCommandTest {
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

    @Test public void testViewExpenditureCommand_execute() {
        ViewExpenditureCommand testViewExpenditure = new ViewExpenditureCommand();
        assertEquals("List shown ...\n" +
                "1. [Academic] || Date: 2023-01-01 || Value: 2.1 || Description: pen\n" +
                "2. [Food] || Date: 2023-03-21 || Value: 4.5 || Description: chicken rice\n" +
                "3. [Transport] || Date: 2023-03-21 || Value: 2.1 || Description: circle line",
                testViewExpenditure.execute(testExpenditures).getCommandResult());
    }

}