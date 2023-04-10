package seedu.expenditure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class AccommodationExpenditureTest {
    AccommodationExpenditure repeatingAccommodationExpenditure;
    AccommodationExpenditure beforeRepeatingAccommodationExpenditure;
    @BeforeEach
    public void setupAccommodationExpenditures() {
        repeatingAccommodationExpenditure = new AccommodationExpenditure(
                "This will produce a repeat date on 2024-03-08",
                3000,
                LocalDate.parse("2023-03-08"),
                LocalDate.parse("2023-03-08"));
        beforeRepeatingAccommodationExpenditure = new AccommodationExpenditure(
                "This will yet to produce a repeat date on 2024-03-08, it should display 2023-03-08",
                3000,
                LocalDate.parse("2023-03-08"),
                LocalDate.parse("2023-03-08"));
    }

    @Test
    public void testAccommodationExpenditure_yearIncrement() {
        repeatingAccommodationExpenditure.checkMark();
        assertEquals("2023-03-08", beforeRepeatingAccommodationExpenditure.getRepeatDate().toString());
        assertEquals("2024-03-08", repeatingAccommodationExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testAccommodationExpenditure_markBeforeRepeatDate() {
        repeatingAccommodationExpenditure.checkMark();
        repeatingAccommodationExpenditure.setPaid();
        assertEquals("[Accommodation] || [X] || Date: 8 Mar 2023 || Value: 3000.0 || " +
                "Description: This will produce a repeat date on 2024-03-08",
                repeatingAccommodationExpenditure.toString());
    }

    @Test
    public void testAccommodationExpenditure_onRepeatDate() {
        repeatingAccommodationExpenditure.checkMark();
        repeatingAccommodationExpenditure.setPaid();
        LocalDate currentDate = LocalDate.parse("2024-03-08");
        LocalDate repeatDate = repeatingAccommodationExpenditure.getRepeatDate();
        boolean isRepeatDate = (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate));
        // Simulating the handleNextRepeat() method
        if (isRepeatDate) {
            repeatingAccommodationExpenditure.resetPaid();
            repeatingAccommodationExpenditure.setNextRepeatDate();
        }
        assertEquals("[Accommodation] || [ ] || Date: 8 Mar 2023 || Value: 3000.0 || " +
                        "Description: This will produce a repeat date on 2024-03-08",
                repeatingAccommodationExpenditure.toString());
    }

    @Test
    public void testNextRepeatDate_onFutureRepeatDate() {
        repeatingAccommodationExpenditure.checkMark();
        repeatingAccommodationExpenditure.setPaid();
        LocalDate currentDate = LocalDate.parse("2024-03-08");
        LocalDate repeatDate = repeatingAccommodationExpenditure.getRepeatDate();
        boolean isRepeatDate = (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate));
        // Simulating the handleNextRepeat() method
        if (isRepeatDate) {
            repeatingAccommodationExpenditure.resetPaid();
            repeatingAccommodationExpenditure.setRepeatDate(repeatingAccommodationExpenditure.setNextRepeatDate());
        }
        assertEquals("2025-03-08", repeatingAccommodationExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testAccommodationExpenditure_inPast_onRepeatDay() {
        // Equivalence Partition in the past: Past marking of this expenditure should unmark in current date of 2023,
        // and set the next repeat date to 2024
        AccommodationExpenditure pastRepeatingAccommodationExpenditure = new AccommodationExpenditure(
                "This has been marked in 2010",
                1000,
                LocalDate.parse("2010-04-09"),
                LocalDate.parse("2010-04-09"));
        pastRepeatingAccommodationExpenditure.setPaid();
        // Simulate the checkMark() method
        LocalDate firstDate = pastRepeatingAccommodationExpenditure.getDate();
        LocalDate currentDate = LocalDate.parse("2023-04-09");
        LocalDate repeatDate = pastRepeatingAccommodationExpenditure.getRepeatDate();
        while (repeatDate.isBefore(currentDate) || repeatDate.equals(firstDate)) {
            pastRepeatingAccommodationExpenditure.setRepeatDate(
                    pastRepeatingAccommodationExpenditure.setNextRepeatDate());
            repeatDate = pastRepeatingAccommodationExpenditure.getRepeatDate();
        }
        if (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate)) {
            pastRepeatingAccommodationExpenditure.resetPaid();
            pastRepeatingAccommodationExpenditure.setRepeatDate(
                    pastRepeatingAccommodationExpenditure.setNextRepeatDate());
        }
        // Since it was set in the past, it should unmark on the repeat day and month in 2023
        assertEquals("[Accommodation] || [ ] || Date: 9 Apr 2010 || Value: 1000.0 || " +
                        "Description: This has been marked in 2010",
                pastRepeatingAccommodationExpenditure.toString());
        // Next repeat date is set in 2024
        assertEquals("2024-04-09", pastRepeatingAccommodationExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testAccommodationExpenditure_inNextFewDays() {
        // Boundary Value Analysis: Since user is creating the expenditure for the year,
        // next repeat date will be set to the next year
        AccommodationExpenditure inNextFewDaysAccommodationExpenditure = new AccommodationExpenditure(
                "Today is 9 April 2023, but repeat date is set in the future of the same year",
                1000,
                LocalDate.parse("2023-04-20"),
                LocalDate.parse("2023-04-20"));
        inNextFewDaysAccommodationExpenditure.setPaid();
        LocalDate firstDate = inNextFewDaysAccommodationExpenditure.getDate();
        LocalDate currentDate = LocalDate.parse("2023-04-09");
        LocalDate repeatDate = inNextFewDaysAccommodationExpenditure.getRepeatDate();
        while (repeatDate.isBefore(currentDate) || repeatDate.equals(firstDate)) {
            inNextFewDaysAccommodationExpenditure.setRepeatDate(
                    inNextFewDaysAccommodationExpenditure.setNextRepeatDate());
            repeatDate = inNextFewDaysAccommodationExpenditure.getRepeatDate();
        }
        if (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate)) {
            inNextFewDaysAccommodationExpenditure.resetPaid();
            inNextFewDaysAccommodationExpenditure.setRepeatDate(
                    inNextFewDaysAccommodationExpenditure.setNextRepeatDate());
        }
        assertEquals("[Accommodation] || [X] || Date: 20 Apr 2023 || Value: 1000.0 || " +
                        "Description: Today is 9 April 2023, but repeat date is set in the future of the same year",
                inNextFewDaysAccommodationExpenditure.toString());
        assertEquals("2024-04-20", inNextFewDaysAccommodationExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testAccommodationExpenditure_inFuture() {
        // Equivalence Partition in the future: Since user is creating the expenditure for the future year,
        // next repeat date will be set to the next year similarly
        AccommodationExpenditure futureAccommodationExpenditure = new AccommodationExpenditure(
                "Today is 9 April 2023, but expenditure is set to future",
                1000,
                LocalDate.parse("2025-04-20"),
                LocalDate.parse("2025-04-20"));
        futureAccommodationExpenditure.setPaid();
        LocalDate firstDate = futureAccommodationExpenditure.getDate();
        LocalDate currentDate = LocalDate.parse("2023-04-09");
        LocalDate repeatDate = futureAccommodationExpenditure.getRepeatDate();
        while (repeatDate.isBefore(currentDate) || repeatDate.equals(firstDate)) {
            futureAccommodationExpenditure.setRepeatDate(
                    futureAccommodationExpenditure.setNextRepeatDate());
            repeatDate = futureAccommodationExpenditure.getRepeatDate();
        }
        if (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate)) {
            futureAccommodationExpenditure.resetPaid();
            futureAccommodationExpenditure.setRepeatDate(
                    futureAccommodationExpenditure.setNextRepeatDate());
        }
        assertEquals("[Accommodation] || [X] || Date: 20 Apr 2025 || Value: 1000.0 || " +
                        "Description: Today is 9 April 2023, but expenditure is set to future",
                futureAccommodationExpenditure.toString());
        assertEquals("2026-04-20", futureAccommodationExpenditure.getRepeatDate().toString());
    }
}
