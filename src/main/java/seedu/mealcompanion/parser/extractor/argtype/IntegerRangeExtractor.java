package seedu.mealcompanion.parser.extractor.argtype;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;

/**
 * Represents an `ArgumentExtractor` which extracts an integer in a range.
 */
public class IntegerRangeExtractor extends IntegerExtractor {
    int minimum;
    int maximum;

    /**
     * @param minimum the minimum value to accept, inclusive
     * @param maximum the maximum value to accept, inclusive
     */
    public IntegerRangeExtractor(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public void extractFrom(MealCompanionSession session, String argument) throws InvalidArgumentException {
        super.extractFrom(session, argument);

        if (this.getExtractedValue() < minimum || this.getExtractedValue() > maximum) {
            throw new InvalidArgumentException("is not between " + minimum + " and " + maximum);
        }
    }
}
