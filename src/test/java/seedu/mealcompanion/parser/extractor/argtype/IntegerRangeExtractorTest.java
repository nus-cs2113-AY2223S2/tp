package seedu.mealcompanion.parser.extractor.argtype;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerRangeExtractorTest {
    @Test
    public void extractIntegerRangeValidLowerBound() throws InvalidArgumentException {
        IntegerRangeExtractor extractor = new IntegerRangeExtractor(0, 100);
        MealCompanionSession session = new MealCompanionSession();
        extractor.extractFrom(session, "0");

        assertEquals(extractor.getExtractedValue(),
                0);
    }

    @Test
    public void extractIntegerRangeValidUpperBound() throws InvalidArgumentException {
        IntegerRangeExtractor extractor = new IntegerRangeExtractor(0, 100);
        MealCompanionSession session = new MealCompanionSession();
        extractor.extractFrom(session, "100");

        assertEquals(extractor.getExtractedValue(),
                100);
    }

    @Test
    public void extractIntegerRangeInvalid() {
        IntegerRangeExtractor extractor = new IntegerRangeExtractor(0, 100);
        MealCompanionSession session = new MealCompanionSession();
        assertThrows(InvalidArgumentException.class, () -> extractor.extractFrom(session, "invalid"));
    }

    @Test
    public void extractIntegerRangeInvalidLowerBound() {
        IntegerRangeExtractor extractor = new IntegerRangeExtractor(0, 100);
        MealCompanionSession session = new MealCompanionSession();
        assertThrows(InvalidArgumentException.class, () -> extractor.extractFrom(session, "-1"));
    }

    @Test
    public void extractIntegerRangeInvalidUpperBound() {
        IntegerRangeExtractor extractor = new IntegerRangeExtractor(0, 100);
        MealCompanionSession session = new MealCompanionSession();
        assertThrows(InvalidArgumentException.class, () -> extractor.extractFrom(session, "101"));
    }
}
