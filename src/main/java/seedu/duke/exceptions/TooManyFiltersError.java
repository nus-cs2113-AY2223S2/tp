package seedu.duke.exceptions;

import seedu.duke.ui.ErrorMessages;

public class TooManyFiltersError extends DukeError {
    public TooManyFiltersError(){
        super(ErrorMessages.ERROR_EXCESSIVE_FILTERS.toString());
    }

}
