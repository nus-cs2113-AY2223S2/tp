package seedu.mealcompanion.parser.extractor;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;

public class PositionalArgumentExtractor extends Extractor {
    ArgumentExtractor argumentExtractor;

    public PositionalArgumentExtractor(ArgumentExtractor argumentExtractor) {
        this.argumentExtractor = argumentExtractor;
    }

    @Override
    public void extractFrom(MealCompanionSession session, CommandArguments arguments) throws InvalidCommandException {
        try {
            this.argumentExtractor.extractFrom(session, arguments.getPositionalArgument());
        } catch (InvalidArgumentException e) {
            throw new InvalidCommandException("The argument", e);
        }
    }
}
