package seedu.mealcompanion.parser.extractor.argtype;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;
import seedu.mealcompanion.parser.extractor.ArgumentExtractor;
import seedu.mealcompanion.recipe.Recipe;

/**
 * Represents an `RecipeIndexExtractor` which extracts a validated recipe number.
 */
public class RecipeIndexExtractor extends ArgumentExtractor {
    private Recipe extracted;

    @Override
    public void extractFrom(MealCompanionSession session, String argument) throws InvalidArgumentException {
        IntegerRangeExtractor internal = new IntegerRangeExtractor(1, session.getRecipes().getRecipes().size());

        try {
            internal.extractFrom(session, argument);
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException("is not a valid recipe number");
        }

        this.extracted = session.getRecipes().getRecipe(internal.getExtractedValue() - 1);
    }

    @Override
    public Recipe getExtractedValue() {
        return this.extracted;
    }
}
