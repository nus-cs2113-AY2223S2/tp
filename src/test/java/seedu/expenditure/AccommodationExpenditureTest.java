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
        AccommodationExpenditure pastRepeatingAccommodationExpenditure = new AccommodationExpenditure(
                "This has been marked in 2010",
                1000,
                LocalDate.parse("2010-04-09"),
                LocalDate.parse("2010-04-09"));
        pastRepeatingAccommodationExpenditure.setPaid();
        // Simulate the checkMark() method
        LocalDate currentDate = LocalDate.parse("2023-04-09");
        pastRepeatingAccommodationExpenditure.checkNextRepeatDate();
        pastRepeatingAccommodationExpenditure.handleNextRepeat();
        assertEquals("[Accommodation] || [ ] || Date: 9 Apr 2010 || Value: 1000.0 || " +
                        "Description: This has been marked in 2010",
                pastRepeatingAccommodationExpenditure.toString());
        LocalDate repeatDate = pastRepeatingAccommodationExpenditure.getRepeatDate();
        assertEquals("2024-04-09", pastRepeatingAccommodationExpenditure.getRepeatDate().toString());
    }

    @Test
    public void testAccommodationExpenditure_inNextFewDays() {
        AccommodationExpenditure inNextFewDaysAccommodationExpenditure = new AccommodationExpenditure(
                "Today is 9 April 2023, but repeat date is set in the future of the same year",
                1000,
                LocalDate.parse("2023-04-20"),
                LocalDate.parse("2023-04-20"));
        inNextFewDaysAccommodationExpenditure.setPaid();
        inNextFewDaysAccommodationExpenditure.checkMark();
        assertEquals("[Accommodation] || [X] || Date: 20 Apr 2023 || Value: 1000.0 || " +
                        "Description: Today is 9 April 2023, but repeat date is set in the future of the same year",
                inNextFewDaysAccommodationExpenditure.toString());
        assertEquals("2024-04-20", inNextFewDaysAccommodationExpenditure.getRepeatDate().toString());
    }
}
