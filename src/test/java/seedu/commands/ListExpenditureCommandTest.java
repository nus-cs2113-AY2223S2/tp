package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.exceptions.WrongInputException;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.TransportExpenditure;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @BeforeEach
    public void setUpExpenditureList() {
        testExpenditures.addExpenditure(
                new AcademicExpenditure("pen", 2.10, LocalDate.parse("2023-01-01")));
        testExpenditures.addExpenditure(
                new FoodExpenditure("chicken rice", 4.50, LocalDate.parse("2023-03-21")));
        testExpenditures.addExpenditure(
                new TransportExpenditure("circle line", 2.10,
                        LocalDate.parse("2023-03-21")));
    }

    @Test
    public void testViewExpenditureCommand_execute() throws WrongInputException {

        ListExpenditureCommand listExpenditure = new ListExpenditureCommand("SGD");
        assertEquals("Here is your list of expenditures in SGD: \n" +
                "1. [Academic] || Date: 1 Jan 2023 || Value: 2.10 || Description: pen\n" +
                "2. [Food] || Date: 21 Mar 2023 || Value: 4.50 || Description: chicken rice\n"
                +
                "3. [Transport] || Date: 21 Mar 2023 || Value: 2.10 || Description: circle line",
                listExpenditure.execute(testExpenditures).getCommandResult());
    }

}
