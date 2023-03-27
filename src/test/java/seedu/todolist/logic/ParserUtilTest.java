package seedu.todolist.logic;

import org.junit.jupiter.api.Test;
import seedu.todolist.constants.Formats;
import seedu.todolist.exception.InvalidDateException;
import seedu.todolist.exception.InvalidDurationException;
import seedu.todolist.exception.InvalidEmailFormatException;
import seedu.todolist.exception.InvalidIdException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class ParserUtilTest {
    @Test
    void parseIdTest() {
        // Invalid ids should throw an exception
        final String[] INVALID_IDS = {"2.5", "", "abc"};
        for (String invalidId : INVALID_IDS) {
            assertThrows(InvalidIdException.class, () -> ParserUtil.parseId(invalidId));
        }

        // Valid ids get parsed successfully
        final int[] VALID_IDS = {3, 17, 84725};
        try {
            for (int validId : VALID_IDS) {
                assertEquals(ParserUtil.parseId(Integer.toString(validId)), validId);
            }
        } catch (InvalidIdException e) {
            fail("Valid id was not successfully parsed.");
        }
    }

    @Test
    void parseDeadlineTest() {
        // Invalid dates should throw an exception
        final String[] INVALID_DEADLINES = {"30/02/2023 16:40", "lalala", "10-50-2032 22:06", ""};
        for (String invalidDeadline : INVALID_DEADLINES) {
            assertThrows(InvalidDateException.class, () -> ParserUtil.parseDeadline(invalidDeadline));
        }

        // Valid dates get parsed successfully
        final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        final LocalDateTime[] VALID_DEADLINES = {now, now.plusDays(1), now.minusWeeks(1), now.plusYears(10)};
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(Formats.TIME_IN_1.getFormat());
        try {
            for (LocalDateTime validDeadline : VALID_DEADLINES) {
                assertEquals(ParserUtil.parseDeadline(validDeadline.format(inputFormatter)), validDeadline);
            }
        } catch (InvalidDateException e) {
            fail("Valid deadline was not successfully parsed.");
        }
    }

    @Test
    void parseEmailTest() {
        // Invalid emails should throw an exception
        final String[] INVALID_EMAILS = {"a@b@c@d.com", "example.com", "hello", "", "where@how"};
        for (String invalidEmail : INVALID_EMAILS) {
            assertThrows(InvalidEmailFormatException.class, () -> ParserUtil.parseEmail(invalidEmail));
        }

        // Valid emails get parsed successfully
        final String[] VALID_EMAILS = {"xyz@xyz.com", "Idk@lalala.com", "legit@gmail.com"};
        try {
            for (String validEmail : VALID_EMAILS) {
                assertEquals(ParserUtil.parseEmail(validEmail), validEmail);
            }
        } catch (InvalidEmailFormatException e) {
            fail("Valid email address was not successfully parsed.");
        }
    }

    @Test
    void parseRepeatDurationTest() {
        // Invalid repeat durations should throw an exception
        final String[] INVALID_DURATIONS = {"$^8g", "1.36", "vb 2s"};
        for (String invalidDuration : INVALID_DURATIONS) {
            assertThrows(InvalidDurationException.class,
                    () -> ParserUtil.parseRepeatDuration(invalidDuration, LocalDateTime.now()));
        }

        // If repeat duration is not provided, it should default to 0
        try {
            assertEquals(ParserUtil.parseRepeatDuration(null, LocalDateTime.now()), 0);
        } catch (InvalidDateException | InvalidDurationException e) {
            fail("Missing repeat duration was not automatically parsed as 0.");
        }

        // Valid repeat durations get parsed successfully
        final int[] VALID_DURATIONS = {3, 17, 84725};
        try {
            for (int validDuration : VALID_DURATIONS) {
                assertEquals(ParserUtil.parseRepeatDuration(Integer.toString(validDuration),
                        LocalDateTime.now()), validDuration);
            }
        } catch (InvalidDateException | InvalidDurationException e) {
            fail("Valid repeat duration was not successfully parsed.");
        }

        // Missing deadline should throw an exception
        for (int validDuration : VALID_DURATIONS) {
            assertThrows(InvalidDateException.class,
                    () -> ParserUtil.parseRepeatDuration(Integer.toString(validDuration), null));
        }
    }
}