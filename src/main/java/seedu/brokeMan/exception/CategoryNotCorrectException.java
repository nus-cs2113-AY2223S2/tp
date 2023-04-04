package seedu.brokeMan.exception;
import static seedu.brokeMan.common.Messages.MESSAGE_INVALID_CATEGORY;
public class CategoryNotCorrectException extends BrokeManException{
    @Override
    public String getMessage() {
        return MESSAGE_INVALID_CATEGORY;
    }
}
