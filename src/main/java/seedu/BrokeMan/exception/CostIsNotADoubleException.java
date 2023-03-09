package seedu.BrokeMan.exception;

import static seedu.BrokeMan.common.Messages.MESSAGE_COST_NOT_DOUBLE;

public class CostIsNotADoubleException extends BrokeManException {
    public String getMessage() {
        return MESSAGE_COST_NOT_DOUBLE;
    }
}
