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
}