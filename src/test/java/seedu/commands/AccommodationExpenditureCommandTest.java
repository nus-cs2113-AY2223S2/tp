package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.ExpenditureList;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccommodationExpenditureCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_accommodationExpenditureCommand_execute() {
        AccommodationExpenditureCommand testAccommodationExpenditureCommand = new AccommodationExpenditureCommand(
                "sheares hall",
                2300.7,
                LocalDate.parse("2021-08-01"),
                LocalDate.parse("2021-08-01"));
        assertEquals("Added accommodation expenditure: " +
                        "[Accommodation] || [ ] || Date: 1 Aug 2021 || Value: 2300.7 || Description: sheares hall",
                testAccommodationExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Accommodation] || [ ] || Date: 1 Aug 2021 || Value: 2300.7 || " +
                        "Description: sheares hall",
                testExpenditures.toString());
    }

    @Test
    public void test_accommodationExpenditureCommand_executeWithExpendituresAlreadyInList() {
        testExpenditures.addExpenditure(new AcademicExpenditure("laptop",
                1500,
                LocalDate.parse("2021-08-01")));
        AccommodationExpenditureCommand testAccommodationExpenditureCommand = new AccommodationExpenditureCommand(
                "sheares hall",
                2300.7,
                LocalDate.parse("2021-08-01"),
                LocalDate.parse("2021-08-01"));
        assertEquals("Added accommodation expenditure: " +
                        "[Accommodation] || [ ] || Date: 1 Aug 2021 || Value: 2300.7 || Description: sheares hall",
                testAccommodationExpenditureCommand.execute(testExpenditures).getCommandResult());
        assertEquals("1. [Academic] || Date: 1 Aug 2021 || Value: 1500.0 || Description: laptop\n" +
                        "2. [Accommodation] || [ ] || Date: 1 Aug 2021 || Value: 2300.7 || Description: sheares hall",
                testExpenditures.toString());
    }
}
