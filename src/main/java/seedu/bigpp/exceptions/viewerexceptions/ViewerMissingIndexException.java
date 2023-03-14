package seedu.bigpp.exceptions.viewerexceptions;

import seedu.bigpp.exceptions.PPException;

public class ViewerMissingIndexException extends PPException {
    @Override
    public String getMessage() {
        return "Please input an index";
    }
}
