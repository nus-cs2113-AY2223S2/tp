package seedu.BrokeMan.exception;

import static seedu.BrokeMan.common.Messages.MESSAGE_INDEX_NOT_INTEGER;

public class IndexNotAnIntegerException extends BrokeManException {
    public String getMessage() {
        return MESSAGE_INDEX_NOT_INTEGER;
    }
}
