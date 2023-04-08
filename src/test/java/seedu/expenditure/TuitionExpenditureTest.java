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
    public void testTuitionExpenditure_inPast() {
        TuitionExpenditure pastRepeatingTuitionExpenditure = new TuitionExpenditure(
                "This has been marked in 2010",
                1000,
                LocalDate.parse("2010-04-09"),
                LocalDate.parse("2010-04-09"));
        pastRepeatingTuitionExpenditure.setPaid();
        // Simulate the checkMark() method
        LocalDate currentDate = LocalDate.parse("2023-04-09");
        pastRepeatingTuitionExpenditure.checkNextRepeatDate();
        pastRepeatingTuitionExpenditure.handleNextRepeat();
        assertEquals("[Tuition] || [ ] || Date: 9 Apr 2010 || Value: 1000.0 || " +
                        "Description: This has been marked in 2010",
                pastRepeatingTuitionExpenditure.toString());
        LocalDate repeatDate = pastRepeatingTuitionExpenditure.getRepeatDate();
        assertEquals("2024-04-09", pastRepeatingTuitionExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testTuitionExpenditure_inNextFewDays() {
        TuitionExpenditure inNextFewDaysTuitionExpenditure = new TuitionExpenditure(
                "Today is 9 April 2023, but repeat date is set in the future of the same year",
                1000,
                LocalDate.parse("2023-04-20"),
                LocalDate.parse("2023-04-20"));
        inNextFewDaysTuitionExpenditure.setPaid();
        inNextFewDaysTuitionExpenditure.checkMark();
        assertEquals("[Tuition] || [X] || Date: 20 Apr 2023 || Value: 1000.0 || " +
                        "Description: Today is 9 April 2023, but repeat date is set in the future of the same year",
                inNextFewDaysTuitionExpenditure.toString());
        assertEquals("2024-04-20", inNextFewDaysTuitionExpenditure.getRepeatDate().toString());
    }
}
