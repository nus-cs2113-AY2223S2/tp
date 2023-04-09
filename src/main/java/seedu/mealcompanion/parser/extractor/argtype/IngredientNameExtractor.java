package seedu.mealcompanion.parser.extractor.argtype;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;
import seedu.mealcompanion.exception.InvalidIngredientNameException;
import seedu.mealcompanion.parser.extractor.ArgumentExtractor;
import seedu.mealcompanion.recipe.IngredientDatabase;
import seedu.mealcompanion.recipe.IngredientMetadata;

public class IngredientNameExtractor extends ArgumentExtractor {
    private IngredientMetadata extracted;

    @Override
    public void extractFrom(MealCompanionSession session, String argument) throws InvalidArgumentException {
        if (argument == null) {
            throw new InvalidArgumentException("is missing");
        }
        if (argument.isEmpty()) {
            throw new InvalidArgumentException("is empty");
        }
        IngredientDatabase db = IngredientDatabase.getDbInstance();
        try {
            this.extracted = db.getKnownIngredient(argument);
        } catch (InvalidIngredientNameException e) {
            throw new InvalidArgumentException("is not a valid ingredient");
        }
    }

    @Override
    public IngredientMetadata getExtractedValue() {
        return this.extracted;
    }
}
