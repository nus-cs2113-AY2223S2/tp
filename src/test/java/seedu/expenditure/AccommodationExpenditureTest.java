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
    public void testNextRepeatDate_onRepeatDate() {
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
}
