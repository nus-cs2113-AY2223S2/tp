package seedu.brokeMan.exception;

import static seedu.brokeMan.common.Messages.MESSAGE_CONTAIN_DUPLICATED_FLAGS;

public class ContainDuplicatedFlagException extends BrokeManException {
    @Override
    public String getMessage() {
        return MESSAGE_CONTAIN_DUPLICATED_FLAGS;
    }
}
