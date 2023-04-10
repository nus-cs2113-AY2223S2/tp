package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.exceptions.EmptyStringException;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserIndividualValueTest {
    // Only relevant checks are done as it is assumed that first check on user input is completed.
    @Test
    void validOutputsForAddAndEdit() throws StringIndexOutOfBoundsException, EmptyStringException{
        String exampleString = "d/2020-10-20 a/50.00 s/Udemy Online Lesson";
        String date = ParseIndividualValue.parseIndividualValue(exampleString,"d/","a/");
        assertEquals(date,"2020-10-20");
        String amount = ParseIndividualValue.parseIndividualValue(exampleString,"a/", "s/");
        assertEquals(amount, "50.00");
        String description = ParseIndividualValue.parseIndividualValue(exampleString, "s/", "");
        assertEquals(description, "Udemy Online Lesson");
    }

    @Test
    void exceedsBoundForAddAndEdit() throws StringIndexOutOfBoundsException {
        String exampleString = "d/2020-10-20 a/1.90 s/Coffee";
        // Out of index
        assertThrows(StringIndexOutOfBoundsException.class,
                () -> ParseIndividualValue.parseIndividualValue(exampleString, "a/", "d/"));
        assertThrows(StringIndexOutOfBoundsException.class,
                () -> ParseIndividualValue.parseIndividualValue(exampleString, "s/", " "));
    }

    @Test
    void amountNotValueForAddAndEdit() throws NumberFormatException, StringIndexOutOfBoundsException
            , EmptyStringException{
        String exampleString = "d/2020-10-20 a/TWO DOLLARS s/Coffee";
        // Checks if input amount is a valid amount
        String amount = ParseIndividualValue.parseIndividualValue(exampleString,"a/", "s/");
        assertThrows(NumberFormatException.class,
                () -> Double.parseDouble(amount));
    }

    @Test
    void validOutputsForDeleteAndMark() throws StringIndexOutOfBoundsException, EmptyStringException{
        String exampleString = "1";
        String position = ParseIndividualValue.parseIndividualValue(exampleString,"","");
        assertEquals(position,"1");
    }
    @Test
    void amountNotValueForDeleteAndMark() throws EmptyStringException {
        String exampleString = "ONE";
        String amount = ParseIndividualValue.parseIndividualValue(exampleString,"", "");
        assertThrows(NumberFormatException.class,
                () -> Double.parseDouble(amount));
    }

    @Test
    void validOutputsForLendBorrow() throws StringIndexOutOfBoundsException, EmptyStringException{
        // Format: category d/date, n/name, a/amount, b/deadline, s/description
        String exampleString = "d/2022-09-01 n/Shark a/1000 b/2023-01-01 s/Feeling rich";
        String date = ParseIndividualValue.parseIndividualValue(exampleString,"d/","n/");
        assertEquals(date,"2022-09-01");
        String name = ParseIndividualValue.parseIndividualValue(exampleString,"n/","a/");
        assertEquals(name,"Shark");
        String amount = ParseIndividualValue.parseIndividualValue(exampleString,"a/", "b/");
        assertEquals(amount, "1000");
        String returnDate = ParseIndividualValue.parseIndividualValue(exampleString,"b/", "s/");
        assertEquals(returnDate, "2023-01-01");
        String description = ParseIndividualValue.parseIndividualValue(exampleString, "s/", "");
        assertEquals(description, "Feeling rich");
    }
    @Test
    void invalidDateTime() throws StringIndexOutOfBoundsException{
        // Format: category d/date, n/name, a/amount, b/deadline, s/description
        String exampleString = "d/2022-09-010 n/Shark a/1000 b/01-01-2023 s/Feeling rich";
        assertThrows(DateTimeException.class,
                () -> LocalDate.parse(ParseIndividualValue.parseIndividualValue(exampleString,"d/", "n/")));
        assertThrows(DateTimeException.class,
                () -> LocalDate.parse(ParseIndividualValue.parseIndividualValue(exampleString,"b/", "s/")));
    }
}
