package seedu.apollo.calendar;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemesterUtilsTest {

    @Test
    void testGetWeekNumber_dateBeforeSemester_expectValue() {
        assertEquals(0, SemesterUtils
                .getWeekNumber(LocalDate.of(2023,1,1)));
    }

    @Test
    void testGetWeekNumber_dateAfterSemester_expectValue() {
        assertEquals(0, SemesterUtils
                .getWeekNumber(LocalDate.of(2023, 6, 30)));
    }

    @Test
    void testGetWeekNumber_dateDuringRecessWeek_expectValue() {
        assertEquals(-1, SemesterUtils
                .getWeekNumber(LocalDate.of(2023, 2, 23)));
    }

    @Test
    void testGetWeekNumber_dateDuringSemester_expectValue() {
        assertEquals(12, SemesterUtils
                .getWeekNumber(LocalDate.of(2023, 4, 5)));
    }

}
