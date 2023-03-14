package seedu.bigpp.exceptions.builderexceptions;

import seedu.bigpp.exceptions.PPException;

public class BuilderInvalidTypeBudgetException extends PPException {
    @Override
    public String getMessage() {
        return "Please enter a budget that an integer";
    }
}
