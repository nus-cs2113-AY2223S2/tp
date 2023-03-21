package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.expenditure.*;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @BeforeEach
    public void setUpExpenditureList() {
        testExpenditures.addExpenditure(
                new AcademicExpenditure("pen", 2.10, LocalDate.parse("2023-01-01")));
        testExpenditures.addExpenditure(
                new FoodExpenditure("chicken rice", 4.50, LocalDate.parse("2023-03-21")));
        testExpenditures.addExpenditure(
                new TransportExpenditure("circle line", 2.10, LocalDate.parse("2023-03-21")));
        testExpenditures.addExpenditure(
                new TuitionExpenditure("school", 8100, LocalDate.parse("2023-01-27")));
        testExpenditures.addExpenditure(
                new AccommodationExpenditure("rc", 3000, LocalDate.parse("2023-01-28")));
    }

    @Test
    public void test_markCommand_onZeroIndex() {
        MarkCommand testMarkZeroIndex = new MarkCommand(0);
        assertEquals("No paid field for this expenditure!",
                testMarkZeroIndex.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_markCommand_onThreeIndex() {
        MarkCommand testMarkThreeIndex = new MarkCommand(3);
        assertEquals("Marked your expenditure!\n" +
                        "[Tuition] || [X] || Date: 2023-01-27 || Value: 8100.0 || Description: school",
                testMarkThreeIndex.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 2023-01-01 || Value: 2.1 || Description: pen\n" +
                "2. [Food] || Date: 2023-03-21 || Value: 4.5 || Description: chicken rice\n" +
                "3. [Transport] || Date: 2023-03-21 || Value: 2.1 || Description: circle line\n" +
                "4. [Tuition] || [X] || Date: 2023-01-27 || Value: 8100.0 || Description: school\n" +
                "5. [Accommodation] || [ ] || Date: 2023-01-28 || Value: 3000.0 || Description: rc",
                testExpenditures.toString());
    }

    @Test
    public void test_markCommand_onFourIndex() {
        MarkCommand testMarkFourIndex = new MarkCommand(4);
        assertEquals("Marked your expenditure!\n" +
                        "[Accommodation] || [X] || Date: 2023-01-28 || Value: 3000.0 || Description: rc",
                testMarkFourIndex.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 2023-01-01 || Value: 2.1 || Description: pen\n" +
                        "2. [Food] || Date: 2023-03-21 || Value: 4.5 || Description: chicken rice\n" +
                        "3. [Transport] || Date: 2023-03-21 || Value: 2.1 || Description: circle line\n" +
                        "4. [Tuition] || [ ] || Date: 2023-01-27 || Value: 8100.0 || Description: school\n" +
                        "5. [Accommodation] || [X] || Date: 2023-01-28 || Value: 3000.0 || Description: rc",
                testExpenditures.toString());
    }

    @Test
    public void test_markCommand_onOutOfBoundsIndex() {
        DeleteCommand testDeleteOutOfBoundsIndex = new DeleteCommand(-10);
        assertEquals("Index is out of bounds or negative",
                testDeleteOutOfBoundsIndex.execute(testExpenditures).getCommandResult());
    }
}