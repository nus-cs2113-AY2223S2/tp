package seedu.mealcompanion.parser.extractor;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.parser.CommandArguments;

/**
 * Represents a helper class which adapts an `ArgumentExtractor`
 * to run on a single flag argument
 */
public class FlagArgumentExtractor extends Extractor {
    String flag;
    ArgumentExtractor argumentExtractor;

    public FlagArgumentExtractor(String flag, ArgumentExtractor argumentExtractor) {
        this.flag = flag;
        this.argumentExtractor = argumentExtractor;
    }

    @Override
    public void extractFrom(MealCompanionSession session, CommandArguments arguments)
            throws InvalidCommandException {
        try {
            this.argumentExtractor.extractFrom(session, arguments.getFlagArgument(flag));
        } catch (InvalidArgumentException e) {
            throw new InvalidCommandException("The flag /" + this.flag, e);
        }
    }
}
