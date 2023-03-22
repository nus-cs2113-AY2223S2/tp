package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.exceptions.EmptyStringException;

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
    void ExceedsBoundForAddAndEdit() throws StringIndexOutOfBoundsException {
        String exampleString = "d/2020-10-20 a/1.90 s/Coffee";
        // Out of index
        assertThrows(StringIndexOutOfBoundsException.class,
                () -> ParseIndividualValue.parseIndividualValue(exampleString, "a/", "d/"));
        assertThrows(StringIndexOutOfBoundsException.class,
                () -> ParseIndividualValue.parseIndividualValue(exampleString, "s/", " "));
    }

    @Test
    void AmountNotValueForAddAndEdit() throws NumberFormatException, StringIndexOutOfBoundsException, EmptyStringException{
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
    void AmountNotValueForDeleteAndMark() throws EmptyStringException {
        String exampleString = "ONE";
        String amount = ParseIndividualValue.parseIndividualValue(exampleString,"", "");
        assertThrows(NumberFormatException.class,
                () -> Double.parseDouble(amount));
    }
}
