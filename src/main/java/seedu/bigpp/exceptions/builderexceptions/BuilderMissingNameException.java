package seedu.bigpp.exceptions.builderexceptions;

import seedu.bigpp.exceptions.PPException;

public class BuilderMissingNameException extends PPException {

    @Override
    public String getMessage() {
        return "Please enter a name";
    }
}
