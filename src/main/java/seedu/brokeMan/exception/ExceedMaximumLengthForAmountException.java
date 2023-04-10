package seedu.brokeMan.exception;

import static seedu.brokeMan.common.Messages.MESSAGE_EXCEED_MAXIMUM_LENGTH_FOR_AMOUNT;

public class ExceedMaximumLengthForAmountException extends BrokeManException {
    @Override
    public String getMessage() {
        return MESSAGE_EXCEED_MAXIMUM_LENGTH_FOR_AMOUNT;
    }
}
