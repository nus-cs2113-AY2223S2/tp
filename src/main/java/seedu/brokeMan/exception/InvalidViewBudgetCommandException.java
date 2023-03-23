package seedu.brokeMan.exception;

import static seedu.brokeMan.common.Messages.MESSAGE_INVALID_VIEW_BUDGET_COMMAND;

public class InvalidViewBudgetCommandException extends BrokeManException {
    @Override
    public String getMessage() {
        return MESSAGE_INVALID_VIEW_BUDGET_COMMAND;
    }
}
