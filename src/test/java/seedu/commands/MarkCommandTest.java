package seedu.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.TransportExpenditure;
import seedu.expenditure.TuitionExpenditure;
import seedu.expenditure.AccommodationExpenditure;
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
                new TuitionExpenditure("school", 8100, LocalDate.parse("2023-01-27"),
                        LocalDate.parse("2023-01-27")));
        testExpenditures.addExpenditure(
                new AccommodationExpenditure("rc", 3000, LocalDate.parse("2023-01-28"),
                        LocalDate.parse("2023-01-28")));
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
                        "[Tuition] || [X] || Date: 27 Jan 2023 || Value: 8100.0 || Description: school",
                testMarkThreeIndex.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 1 Jan 2023 || Value: 2.1 || Description: pen\n" +
                "2. [Food] || Date: 21 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                "3. [Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: circle line\n" +
                "4. [Tuition] || [X] || Date: 27 Jan 2023 || Value: 8100.0 || Description: school\n" +
                "5. [Accommodation] || [ ] || Date: 28 Jan 2023 || Value: 3000.0 || Description: rc",
                testExpenditures.toString());
    }

    @Test
    public void test_markCommand_onFourIndex() {
        MarkCommand testMarkFourIndex = new MarkCommand(4);
        assertEquals("Marked your expenditure!\n" +
                        "[Accommodation] || [X] || Date: 28 Jan 2023 || Value: 3000.0 || Description: rc",
                testMarkFourIndex.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 1 Jan 2023 || Value: 2.1 || Description: pen\n" +
                        "2. [Food] || Date: 21 Mar 2023 || Value: 4.5 || Description: chicken rice\n" +
                        "3. [Transport] || Date: 21 Mar 2023 || Value: 2.1 || Description: circle line\n" +
                        "4. [Tuition] || [ ] || Date: 27 Jan 2023 || Value: 8100.0 || Description: school\n" +
                        "5. [Accommodation] || [X] || Date: 28 Jan 2023 || Value: 3000.0 || Description: rc",
                testExpenditures.toString());
    }

    @Test
    public void test_markCommand_onFourIndexAgain() {
        MarkCommand testMarkFourIndex = new MarkCommand(4);
        testMarkFourIndex.execute(testExpenditures);
        MarkCommand testMarkFourIndexAgain = new MarkCommand(4);
        assertEquals("Sorry! This expenditure is already marked!",
                testMarkFourIndexAgain.execute(testExpenditures).getCommandResult());
    }

    @Test
    public void test_markCommand_onOutOfBoundsIndex() {
        DeleteCommand testDeleteOutOfBoundsIndex = new DeleteCommand(-10);
        assertEquals("Index is out of bounds or negative",
                testDeleteOutOfBoundsIndex.execute(testExpenditures).getCommandResult());
    }
}
