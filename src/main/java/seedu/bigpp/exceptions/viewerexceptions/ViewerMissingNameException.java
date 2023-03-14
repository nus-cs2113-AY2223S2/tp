package seedu.bigpp.exceptions.viewerexceptions;

import seedu.bigpp.exceptions.PPException;

public class ViewerMissingNameException extends PPException {

    /**
     * Return a message to inform the user that the name is missing from the input command
     */
    @Override
    public String getMessage() {
        return "Please enter a name";
    }
}
