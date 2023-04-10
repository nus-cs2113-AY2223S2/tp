package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_foodExpenditureCommand_execute() {
        FoodExpenditureCommand testFoodExpenditureCommand = new FoodExpenditureCommand(
                "chicken rice",
                4.5,
                LocalDate.parse("2023-03-21"));
        assertEquals("Added food expenditure: [Food] || Date: 21 Mar 2023 || Value: 4.5 || " +
                        "Description: chicken rice",
                testFoodExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Food] || Date: 21 Mar 2023 || Value: 4.5 || Description: chicken rice",
                testExpenditures.toString());
    }

    @Test
    public void test_foodExpenditureCommand_executeWithExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new AcademicExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        FoodExpenditureCommand testFoodExpenditureCommand = new FoodExpenditureCommand(
                "chicken rice",
                4.5,
                LocalDate.parse("2023-03-21"));
        assertEquals("Added food expenditure: [Food] || Date: 21 Mar 2023 || Value: 4.5 || " +
                        "Description: chicken rice",
                testFoodExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 1 Aug 2021 || Value: 1500.0 || Description: laptop\n" +
                        "2. [Food] || Date: 21 Mar 2023 || Value: 4.5 || Description: chicken rice",
                testExpenditures.toString());
    }
}
