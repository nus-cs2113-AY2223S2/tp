package seedu.bigpp.exceptions.builderexceptions;

import seedu.bigpp.exceptions.PPException;

public class BuilderInvalidNumberBudgetException extends PPException {
    @Override
    public String getMessage() {
        return "Please enter a budget that is greater than 0";
    }
}
