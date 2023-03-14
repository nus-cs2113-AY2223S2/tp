package seedu.bigpp.exceptions.builderexceptions;

import seedu.bigpp.exceptions.PPException;

public class BuilderMissingBudgetException extends PPException {
    
    @Override
    public String getMessage() {
        return "Please enter a budget";
    }
}
