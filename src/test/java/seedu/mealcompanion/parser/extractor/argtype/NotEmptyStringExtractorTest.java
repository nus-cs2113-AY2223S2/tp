package seedu.mealcompanion.parser.extractor.argtype;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;
import seedu.mealcompanion.exception.InvalidIngredientNameException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotEmptyStringExtractorTest {
    @Test
    public void extractStringValid() throws InvalidArgumentException {
        NotEmptyStringExtractor extractor = new NotEmptyStringExtractor();
        MealCompanionSession session = new MealCompanionSession();
        extractor.extractFrom(session, "test");

        assertEquals(extractor.getExtractedValue(),
                "test");
    }

    @Test
    public void extractStringInvalid() throws InvalidArgumentException, InvalidIngredientNameException {
        NotEmptyStringExtractor extractor = new NotEmptyStringExtractor();
        MealCompanionSession session = new MealCompanionSession();
        assertThrows(InvalidArgumentException.class, () -> extractor.extractFrom(session, ""));
    }
}
