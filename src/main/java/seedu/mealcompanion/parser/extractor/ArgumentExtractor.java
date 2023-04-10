package seedu.mealcompanion.parser.extractor;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;

/**
 *
 */
public abstract class ArgumentExtractor {
    public abstract void extractFrom(MealCompanionSession session, String argument) throws InvalidArgumentException;

    public abstract Object getExtractedValue();
}
