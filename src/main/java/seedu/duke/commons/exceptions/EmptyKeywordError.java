package seedu.duke.exceptions;

import seedu.duke.ui.ErrorMessages;

public class EmptyKeywordError extends DukeError{
    public EmptyKeywordError(){
        super(ErrorMessages.ERROR_EMPTY_KEYWORD.toString());
    }

}
