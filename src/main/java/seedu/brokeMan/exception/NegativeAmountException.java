package seedu.brokeMan.exception;

import static seedu.brokeMan.common.Messages.MESSAGE_AMOUNT_LESS_THAN_OR_EQUALS_ZERO;

public class NegativeAmountException extends BrokeManException {
    @Override
    public String getMessage() {
        return MESSAGE_AMOUNT_LESS_THAN_OR_EQUALS_ZERO;
    }
}
