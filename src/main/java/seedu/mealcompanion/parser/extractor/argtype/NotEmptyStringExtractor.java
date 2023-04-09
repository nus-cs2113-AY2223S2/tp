package seedu.mealcompanion.parser.extractor.argtype;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.InvalidArgumentException;
import seedu.mealcompanion.parser.extractor.ArgumentExtractor;

public class NotEmptyStringExtractor extends ArgumentExtractor {
    private String extracted;

    @Override
    public void extractFrom(MealCompanionSession session, String argument) throws InvalidArgumentException {
        if (argument == null) {
            throw new InvalidArgumentException("is missing");
        }
        if (argument.isEmpty()) {
            throw new InvalidArgumentException("is empty");
        }
        this.extracted = argument;
    }

    @Override
    public String getExtractedValue() {
        return this.extracted;
    }
}
