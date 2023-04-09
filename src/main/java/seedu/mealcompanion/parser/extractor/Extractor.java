package seedu.mealcompanion.parser.extractor;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a helper class which takes a list of arguments
 * and the session state, and performs some validated extraction.
 */
public abstract class Extractor {
    public abstract void extractFrom(MealCompanionSession session, CommandArguments arguments)
            throws InvalidCommandException;
}
