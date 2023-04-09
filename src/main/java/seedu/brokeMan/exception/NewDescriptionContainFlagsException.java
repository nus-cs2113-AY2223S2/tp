package seedu.brokeMan.exception;

import static seedu.brokeMan.common.Messages.MESSAGE_NEW_DESCRIPTION_CONTAIN_FLAGS;

public class NewDescriptionContainFlagsException extends BrokeManException {
    @Override
    public String getMessage() {
        return MESSAGE_NEW_DESCRIPTION_CONTAIN_FLAGS;
    }
}
