package seedu.bigpp.exceptions.builderexceptions;

import seedu.bigpp.exceptions.PPException;

public class BuilderMissingSelectException extends PPException {
    @Override
    public String getMessage() {
        return "Please select a component";
    }
}
