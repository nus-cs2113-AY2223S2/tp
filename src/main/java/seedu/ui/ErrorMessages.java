package seedu.ui;

import java.time.LocalDate;

public enum ErrorMessages {

    ERROR_MARK_MESSAGE("Sorry! This expenditure is already marked!"),
    ERROR_UNMARK_MESSAGE("Sorry! This expenditure is already unmarked!"),
    ERROR_EMPTY_STRING_MESSAGE("One of your inputs are empty! Please enter help for the proper format"),
    ERROR_INVALID_SORT_INPUT_MESSAGE("Invalid sort message!\n " +
            "<sort ascend> to sort amount in ascending order. " +
            "<sort descend> to sort amount in descending order.\n" +
            "<sort earliest> to sort amount from earliest date added. " +
            "<sort latest> to sort amount from latest date added."),
    ERROR_NO_PAID_FIELD_MESSAGE("No paid field for this expenditure!"),
    ERROR_NOT_POSITIVE_VALUE_MESSAGE("Amount entered must be positive! Please try again"),
    ERROR_COMMAND_NOT_RECOGNISED_MESSAGE("Command not recognised. Please try again"),
    ERROR_LACK_OF_PARAMETERS_MESSAGE("Input command does not have required parameters! Please try again"),
    ERROR_DATE_TIME_ERROR_MESSAGE("Date error! Please enter a single date in yyyy-mm-dd format!"),
    ERROR_INVALID_INPUT_MESSAGE("Invalid input! Please try again"),
    ERROR_NUMBER_FORMAT_MESSAGE("Index must be an integer and within bounds! Please try again"),
    ERROR_AMOUNT_FORMAT_MESSAGE("The amount you provided is not in the right format! " +
            "Please enter a single number value"),
    ERROR_WRONG_FORMAT_MESSAGE("Input command cannot be recognised as it is in the wrong format. Please try again"),
    ERROR_BUDGET_NOT_NUMERICAL_MESSAGE("Budget amount is not a numerical value!"),
    ERROR_INVALID_DATE_MESSAGE("Borrow date must be before the return date! Please try again"),
    ERROR_INVALID_DEADLINE_MESSAGE(
            "Return date should be equal to or later than today's date!" + "Today's date is " + LocalDate.now()),
    ERROR_INVALID_AMOUNT_PRECISION("Precision of amount is more than 2 decimal places. " +
            "Please re-enter a valid amount value"),
    ERROR_DATE_LIMIT_MESSAGE("Only dates from the year 2000 and onwards are accepted. Earlier dates are not supported"),
    ERROR_YEAR_LIMIT_MESSAGE("Only years 2000 and onwards are accepted. Earlier years are not supported"),
    ERROR_INVALID_AMOUNT_TOO_LARGE("The amount you have entered is too large please input a smaller value!");

    public final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }

}
