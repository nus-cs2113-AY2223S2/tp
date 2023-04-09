package seedu.mealcompanion.parser.extractor.argtype;

import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.parser.extractor.ArgumentExtractor;

/**
 * Represents an `ArgumentExtractor` which extracts a non-empty String
 */
public class StringExtractor extends ArgumentExtractor {
    private String extracted;

    @Override
    public void extractFrom(MealCompanionSession session, String argument) {
        this.extracted = argument;
    }

    @Override
    public String getExtractedValue() {
        return this.extracted;
    }
}
