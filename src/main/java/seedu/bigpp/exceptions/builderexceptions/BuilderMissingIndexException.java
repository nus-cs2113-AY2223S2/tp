package seedu.bigpp.exceptions.builderexceptions;

import seedu.bigpp.exceptions.PPException;

public class BuilderMissingIndexException extends PPException {
    @Override
    public String getMessage() {
        return "Please input an index";
    }
}
