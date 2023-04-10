package seedu.brokeMan.exception;

import static seedu.brokeMan.common.Messages.MESSAGE_AMOUNT_NOT_DOUBLE;

public class AmountIsNotADoubleException extends BrokeManException {
    public String getMessage() {
        return MESSAGE_AMOUNT_NOT_DOUBLE;
    }
}
