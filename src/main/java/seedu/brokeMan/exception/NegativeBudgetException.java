package seedu.brokeMan.exception;

public class NegativeBudgetException extends BrokeManException {
    @Override
    public String getMessage() {
        return "You cannot set negative budget.";
    }
}
