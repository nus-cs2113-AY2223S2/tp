package seedu.brokeMan.exception;

public class hasNotSetBudgetException extends BrokeManException {
    public String getMessage() {
        return "You have not set your budget for this month.";
    }
}
