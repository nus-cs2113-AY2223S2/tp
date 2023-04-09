package seedu.mealcompanion.parser.extractor.argtype;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerExtractorTest {
    @Test
    public void extractIngredientNameValid() throws InvalidArgumentException {
        IntegerExtractor extractor = new IntegerExtractor();
        MealCompanionSession session = new MealCompanionSession();
        extractor.extractFrom(session, "4");

        assertEquals(extractor.getExtractedValue(),
                4);
    }

    @Test
    public void extractIntegerInvalid() {
        IntegerExtractor extractor = new IntegerExtractor();
        MealCompanionSession session = new MealCompanionSession();
        assertThrows(InvalidArgumentException.class, () -> extractor.extractFrom(session, "invalid"));
    }
}
