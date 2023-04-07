package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exceptions.AlreadyMarkException;
import seedu.exceptions.NoPaidFieldException;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.TransportExpenditure;
import seedu.expenditure.TuitionExpenditure;
import seedu.expenditure.AccommodationExpenditure;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UnmarkCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @BeforeEach
    public void setUpExpenditureList() throws NoPaidFieldException, AlreadyMarkException {
        testExpenditures.addExpenditure(
                new AcademicExpenditure("pen", 2.10, LocalDate.parse("2023-01-01")));
        testExpenditures.addExpenditure(
                new FoodExpenditure("chicken rice", 4.50, LocalDate.parse("2023-03-21")));
        testExpenditures.addExpenditure(
                new TransportExpenditure("circle line", 2.10, LocalDate.parse("2023-03-21")));
        testExpenditures.addExpenditure(
                new TuitionExpenditure("school", 8100, LocalDate.parse("2023-01-27"),
                        LocalDate.parse("2023-01-27")));
        testExpenditures.addExpenditure(
                new AccommodationExpenditure("rc", 3000, LocalDate.parse("2023-01-28"),
                        LocalDate.parse("2023-01-28")));
        testExpenditures.markExpenditure(3);
        testExpenditures.markExpenditure(4);
    }

    @Test
    public void test_unmarkCommand_onZeroIndex() {
        UnmarkCommand testUnmarkZeroIndex = new UnmarkCommand(0);
        assertEquals("No paid field for this expenditure!",
                testUnmarkZeroIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_unmarkCommand_onThreeIndex() {
        UnmarkCommand testUnmarkThreeIndex = new UnmarkCommand(3);
        assertEquals("Unmarked your expenditure!\n" +
                        "[Tuition] || [ ] || Date: 27 Jan 2023 || Value: 8100.0 || Description: school",
                testUnmarkThreeIndex.execute(testExpenditures).getCommandResult());
        testUnmarkThreeIndex.execute(testExpenditures);
        assertEquals("1. [Academic] || Date: 1 Jan 2023 || Value: 2.1 || Description: pen\n" +
                        "2. [Food] || Date: 21 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                        "3. [Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: circle line\n" +
                        "4. [Tuition] || [ ] || Date: 27 Jan 2023 || Value: 8100.0 || Description: school\n" +
                        "5. [Accommodation] || [X] || Date: 28 Jan 2023 || Value: 3000.0 || Description: rc",
                testExpenditures.toString());
    }

    @Test
    public void test_unmarkCommand_onFourIndex() {
        UnmarkCommand testUnmarkFourIndex = new UnmarkCommand(4);
        assertEquals("Unmarked your expenditure!\n" +
                        "[Accommodation] || [ ] || Date: 28 Jan 2023 || Value: 3000.0 || Description: rc",
                testUnmarkFourIndex.execute(testExpenditures).getCommandResult());
        testUnmarkFourIndex.execute(testExpenditures);
        assertEquals("1. [Academic] || Date: 1 Jan 2023 || Value: 2.1 || Description: pen\n" +
                        "2. [Food] || Date: 21 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                        "3. [Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: circle line\n" +
                        "4. [Tuition] || [X] || Date: 27 Jan 2023 || Value: 8100.0 || Description: school\n" +
                        "5. [Accommodation] || [ ] || Date: 28 Jan 2023 || Value: 3000.0 || Description: rc",
                testExpenditures.toString());
    }

    @Test
    public void test_unmarkCommand_onFourIndexAgain() {
        UnmarkCommand testMarkFourIndex = new UnmarkCommand(4);
        testMarkFourIndex.execute(testExpenditures);
        UnmarkCommand testMarkFourIndexAgain = new UnmarkCommand(4);
        assertEquals("Sorry! This expenditure is already unmarked!",
                testMarkFourIndexAgain.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_markCommand_onOutOfBoundsIndex() {
        DeleteCommand testDeleteOutOfBoundsIndex = new DeleteCommand(-10);
        assertEquals("Index is out of bounds or negative",
                testDeleteOutOfBoundsIndex.execute(testExpenditures).getCommandResult());
    }
}
