package seedu.mealcompanion.parser.extractor.argtype;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecipeIndexExtractorTest {
    @Test
    public void extractRecipeValid() throws InvalidArgumentException {
        RecipeIndexExtractor extractor = new RecipeIndexExtractor();
        MealCompanionSession session = new MealCompanionSession();
        extractor.extractFrom(session, "1");

        assertEquals(extractor.getExtractedValue(),
                session.getRecipes().getRecipe(0));
    }

    @Test
    public void extractRecipeInvalidNumber() {
        RecipeIndexExtractor extractor = new RecipeIndexExtractor();
        MealCompanionSession session = new MealCompanionSession();
        assertThrows(InvalidArgumentException.class, () -> extractor.extractFrom(session, "invalid"));
    }

    @Test
    public void extractRecipeInvalidIndex() {
        RecipeIndexExtractor extractor = new RecipeIndexExtractor();
        MealCompanionSession session = new MealCompanionSession();
        assertThrows(InvalidArgumentException.class, () -> extractor.extractFrom(session, "-1"));
    }
}
