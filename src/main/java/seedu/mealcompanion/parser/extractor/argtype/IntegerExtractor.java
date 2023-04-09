package seedu.mealcompanion.parser.extractor.argtype;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;
import seedu.mealcompanion.parser.extractor.ArgumentExtractor;

public class IntegerExtractor extends ArgumentExtractor {
    private int extracted;

    @Override
    public void extractFrom(MealCompanionSession session, String argument) throws InvalidArgumentException {
        if (argument == null) {
            throw new InvalidArgumentException("is missing");
        }
        if (argument.isEmpty()) {
            throw new InvalidArgumentException("is empty");
        }
        try {
            this.extracted = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("is not a number");
        }
    }

    @Override
    public Integer getExtractedValue() {
        return this.extracted;
    }
}
