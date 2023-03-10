package seedu.brokeMan.exception;

import static seedu.brokeMan.common.Messages.MESSAGE_INDEX_NOT_INTEGER;

public class IndexNotAnIntegerException extends BrokeManException {
    public String getMessage() {
        return MESSAGE_INDEX_NOT_INTEGER;
    }
}
