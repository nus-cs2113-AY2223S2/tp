package seedu.mealcompanion.parser.extractor;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;

public abstract class Extractor {
    public abstract void extractFrom(MealCompanionSession session, CommandArguments arguments)
            throws InvalidCommandException;
}
