package seedu.mealcompanion.parser.extractor.argtype;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;
import seedu.mealcompanion.exception.InvalidIngredientNameException;
import seedu.mealcompanion.recipe.IngredientDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IngredientNameExtractorTest {
    @Test
    public void extractIngredientNameValid() throws InvalidArgumentException, InvalidIngredientNameException {
        IngredientNameExtractor extractor = new IngredientNameExtractor();
        MealCompanionSession session = new MealCompanionSession();
        extractor.extractFrom(session, "water");

        assertEquals(extractor.getExtractedValue(),
                IngredientDatabase.getDbInstance().getKnownIngredient("water"));
    }

    @Test
    public void extractIngredientNameInvalid() throws InvalidArgumentException, InvalidIngredientNameException {
        IngredientNameExtractor extractor = new IngredientNameExtractor();
        MealCompanionSession session = new MealCompanionSession();
        assertThrows(InvalidArgumentException.class, () -> extractor.extractFrom(session, "invalid"));
    }
}
