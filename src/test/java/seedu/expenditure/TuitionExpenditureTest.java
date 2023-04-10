package seedu.expenditure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class TuitionExpenditureTest {
    TuitionExpenditure repeatingTuitionExpenditure;
    TuitionExpenditure beforeRepeatingTuitionExpenditure;
    @BeforeEach
    public void setupTuitionExpenditures() {
        repeatingTuitionExpenditure = new TuitionExpenditure(
                "This will produce a repeat date on 2024-03-08",
                3000,
                LocalDate.parse("2023-03-08"),
                LocalDate.parse("2023-03-08"));
        beforeRepeatingTuitionExpenditure = new TuitionExpenditure(
                "This will yet to produce a repeat date on 2024-03-08, it should display 2023-03-08",
                3000,
                LocalDate.parse("2023-03-08"),
                LocalDate.parse("2023-03-08"));
    }

    @Test
    public void testTuitionExpenditure_yearIncrement() {
        repeatingTuitionExpenditure.checkMark();
        assertEquals("2023-03-08", beforeRepeatingTuitionExpenditure.getRepeatDate().toString());
        assertEquals("2024-03-08", repeatingTuitionExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testTuitionExpenditure_markBeforeRepeatDate() {
        repeatingTuitionExpenditure.checkMark();
        repeatingTuitionExpenditure.setPaid();
        assertEquals("[Tuition] || [X] || Date: 8 Mar 2023 || Value: 3000.0 || " +
                        "Description: This will produce a repeat date on 2024-03-08",
                repeatingTuitionExpenditure.toString());
    }

    @Test
    public void testTuitionExpenditure_onRepeatDate() {
        repeatingTuitionExpenditure.checkMark();
        repeatingTuitionExpenditure.setPaid();
        LocalDate currentDate = LocalDate.parse("2024-03-08");
        LocalDate repeatDate = repeatingTuitionExpenditure.getRepeatDate();
        boolean isRepeatDate = (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate));
        // Simulating the handleNextRepeat() method
        if (isRepeatDate) {
            repeatingTuitionExpenditure.resetPaid();
            repeatingTuitionExpenditure.setNextRepeatDate();
        }
        assertEquals("[Tuition] || [ ] || Date: 8 Mar 2023 || Value: 3000.0 || " +
                        "Description: This will produce a repeat date on 2024-03-08",
                repeatingTuitionExpenditure.toString());
    }

    @Test
    public void testNextRepeatDate_onRepeatDate() {
        repeatingTuitionExpenditure.checkMark();
        repeatingTuitionExpenditure.setPaid();
        LocalDate currentDate = LocalDate.parse("2024-03-08");
        LocalDate repeatDate = repeatingTuitionExpenditure.getRepeatDate();
        boolean isRepeatDate = (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate));
        // Simulating the handleNextRepeat() method
        if (isRepeatDate) {
            repeatingTuitionExpenditure.resetPaid();
            repeatingTuitionExpenditure.setRepeatDate(repeatingTuitionExpenditure.setNextRepeatDate());
        }
        assertEquals("2025-03-08", repeatingTuitionExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testTuitionExpenditure_inPast_onRepeatDay() {
        // Equivalence Partition in the past: Past marking of this expenditure should unmark in current date of 2023,
        // and set the next repeat date to 2024
        TuitionExpenditure pastRepeatingTuitionExpenditure = new TuitionExpenditure(
                "This has been marked in 2010",
                1000,
                LocalDate.parse("2010-04-09"),
                LocalDate.parse("2010-04-09"));
        pastRepeatingTuitionExpenditure.setPaid();
        // Simulate the checkMark() method
        LocalDate firstDate = pastRepeatingTuitionExpenditure.getDate();
        LocalDate currentDate = LocalDate.parse("2023-04-09");
        LocalDate repeatDate = pastRepeatingTuitionExpenditure.getRepeatDate();
        while (repeatDate.isBefore(currentDate) || repeatDate.equals(firstDate)) {
            pastRepeatingTuitionExpenditure.setRepeatDate(
                    pastRepeatingTuitionExpenditure.setNextRepeatDate());
            repeatDate = pastRepeatingTuitionExpenditure.getRepeatDate();
        }
        if (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate)) {
            pastRepeatingTuitionExpenditure.resetPaid();
            pastRepeatingTuitionExpenditure.setRepeatDate(
                    pastRepeatingTuitionExpenditure.setNextRepeatDate());
        }
        // Since it was set in the past, it should unmark on the repeat day and month in 2023
        assertEquals("[Tuition] || [ ] || Date: 9 Apr 2010 || Value: 1000.0 || " +
                        "Description: This has been marked in 2010",
                pastRepeatingTuitionExpenditure.toString());
        // Next repeat date is set in 2024
        assertEquals("2024-04-09", pastRepeatingTuitionExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testTuitionExpenditure_inNextFewDays() {
        // Boundary Value Analysis: Since user is creating the expenditure for the year,
        // next repeat date will be set to the next year
        TuitionExpenditure inNextFewDaysTuitionExpenditure = new TuitionExpenditure(
                "Today is 9 April 2023, but repeat date is set in the future of the same year",
                1000,
                LocalDate.parse("2023-04-20"),
                LocalDate.parse("2023-04-20"));
        inNextFewDaysTuitionExpenditure.setPaid();
        LocalDate firstDate = inNextFewDaysTuitionExpenditure.getDate();
        LocalDate currentDate = LocalDate.parse("2023-04-09");
        LocalDate repeatDate = inNextFewDaysTuitionExpenditure.getRepeatDate();
        while (repeatDate.isBefore(currentDate) || repeatDate.equals(firstDate)) {
            inNextFewDaysTuitionExpenditure.setRepeatDate(
                    inNextFewDaysTuitionExpenditure.setNextRepeatDate());
            repeatDate = inNextFewDaysTuitionExpenditure.getRepeatDate();
        }
        if (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate)) {
            inNextFewDaysTuitionExpenditure.resetPaid();
            inNextFewDaysTuitionExpenditure.setRepeatDate(
                    inNextFewDaysTuitionExpenditure.setNextRepeatDate());
        }
        assertEquals("[Tuition] || [X] || Date: 20 Apr 2023 || Value: 1000.0 || " +
                        "Description: Today is 9 April 2023, but repeat date is set in the future of the same year",
                inNextFewDaysTuitionExpenditure.toString());
        assertEquals("2024-04-20", inNextFewDaysTuitionExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testTuitionExpenditure_inFuture() {
        // Equivalence Partition in the future: Since user is creating the expenditure for the future year,
        // next repeat date will be set to the next year similarly
        TuitionExpenditure futureTuitionExpenditure = new TuitionExpenditure(
                "Today is 9 April 2023, but expenditure is set to future",
                1000,
                LocalDate.parse("2025-04-20"),
                LocalDate.parse("2025-04-20"));
        futureTuitionExpenditure.setPaid();
        LocalDate firstDate = futureTuitionExpenditure.getDate();
        LocalDate currentDate = LocalDate.parse("2023-04-09");
        LocalDate repeatDate = futureTuitionExpenditure.getRepeatDate();
        while (repeatDate.isBefore(currentDate) || repeatDate.equals(firstDate)) {
            futureTuitionExpenditure.setRepeatDate(
                    futureTuitionExpenditure.setNextRepeatDate());
            repeatDate = futureTuitionExpenditure.getRepeatDate();
        }
        if (currentDate.equals(repeatDate) || currentDate.isAfter(repeatDate)) {
            futureTuitionExpenditure.resetPaid();
            futureTuitionExpenditure.setRepeatDate(
                    futureTuitionExpenditure.setNextRepeatDate());
        }
        assertEquals("[Tuition] || [X] || Date: 20 Apr 2025 || Value: 1000.0 || " +
                        "Description: Today is 9 April 2023, but expenditure is set to future",
                futureTuitionExpenditure.toString());
        assertEquals("2026-04-20", futureTuitionExpenditure.getRepeatDate().toString());
    }
}
