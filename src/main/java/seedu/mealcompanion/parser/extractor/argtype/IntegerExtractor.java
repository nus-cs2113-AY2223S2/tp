package seedu.mealcompanion.parser.extractor.argtype;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;
import seedu.mealcompanion.parser.extractor.ArgumentExtractor;

/**
 * Represents an `ArgumentExtractor` which extracts an integer.
 */
public class IntegerExtractor extends ArgumentExtractor {
    private int extracted;

    private boolean isNum(String argument) {
        return argument.matches("-?[0-9]+");
    }

    @Override
    public void extractFrom(MealCompanionSession session, String argument) throws InvalidArgumentException {
        if (argument == null) {
            throw new InvalidArgumentException("is missing");
        }
        if (argument.isEmpty()) {
            throw new InvalidArgumentException("is empty");
        }
        if (!isNum(argument)) {
            throw new InvalidArgumentException("is not a number");
        }
        try {
            this.extracted = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("is too big or small to represent");
        }
    }

    @Override
    public Integer getExtractedValue() {
        return this.extracted;
    }
}
