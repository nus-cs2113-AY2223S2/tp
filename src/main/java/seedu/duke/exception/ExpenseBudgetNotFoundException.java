package seedu.duke.exception;

import seedu.duke.util.ErrorMessages;

//@@author pinyoko573
public class ExpenseBudgetNotFoundException extends BBException {
    @Override
    public String getMessage() {
        return ErrorMessages.ERROR_EXPENSE_BUDGET_NOT_FOUND.toString();
    }
}
