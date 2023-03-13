package seedu.Parser;

import seedu.commands.*;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.ExceptionChecker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ParseLendBorrow {
    private final int OFFSET_DELIMITER = 2;
    private final String userInput;

    public ParseLendBorrow(String userInput) {
        this.userInput = userInput;
    }

    public Command addItem(String command) {
        try {
            // Format: category d/date, a/amount, s/description
            String descriptionVal = fetchLendBorrowDescriptionInput();
            double amount = Double.parseDouble(fetchLendBorrowAmountInput());
            String name = fetchLendBorrowNameInput();
            LocalDate lentDate = LocalDate.parse(fetchLendBorrowDateInput());
            LocalDate deadline = LocalDate.parse(fetchLendBorrowDeadlineInput());

            switch (command) {
            case LendExpenditureCommand.COMMAND_WORD:
                return new LendExpenditureCommand(descriptionVal, name, amount, lentDate, deadline);
            case BorrowExpenditureCommand.COMMAND_WORD:
                return new BorrowExpenditureCommand(descriptionVal, name, amount, lentDate, deadline);
            default:
                return new InvalidCommand("Invalid");
            }
        } catch (NumberFormatException n) {
            return new InvalidCommand("number format problem");
        } catch (DateTimeParseException d) {
            return new InvalidCommand("date time error");
        }
    }

    public String fetchLendBorrowDateInput() {
        try {
            int positionOfDSlash = userInput.indexOf("d/");
            int positionOfNSlash = userInput.indexOf("n/");
            String date = userInput.substring(positionOfDSlash + OFFSET_DELIMITER, positionOfNSlash).trim();
            ExceptionChecker.checkEmptyString(date);
            return date;
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return e.getMessage();
        }
    }

    public String fetchLendBorrowNameInput() {
        try {
            int positionOfNSlash = userInput.indexOf("n/");
            int positionOfASlash = userInput.indexOf("a/");
            String name = userInput.substring(positionOfNSlash + OFFSET_DELIMITER, positionOfASlash).trim();
            ExceptionChecker.checkEmptyString(name);
            return name;
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return e.getMessage();
        }
    }

    public String fetchLendBorrowAmountInput() {
        try {
            int positionOfASlash = userInput.indexOf("a/");
            int positionOfBSlash = userInput.indexOf("b/");
            String amount = userInput.substring(positionOfASlash + OFFSET_DELIMITER, positionOfBSlash).trim();
            ExceptionChecker.checkEmptyString(amount);
            return amount;
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return e.getMessage();
        }
    }

    public String fetchLendBorrowDeadlineInput() {
        try {
            int positionOfBSlash = userInput.indexOf("b/");
            int positionOfSSlash = userInput.indexOf("s/");
            String amount = userInput.substring(positionOfBSlash + OFFSET_DELIMITER, positionOfSSlash).trim();
            ExceptionChecker.checkEmptyString(amount);
            return amount;
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return e.getMessage();
        }
    }

    public String fetchLendBorrowDescriptionInput() {
        try {
            int positionOfSSlash = userInput.indexOf("s/");
            String description = userInput.substring(positionOfSSlash + OFFSET_DELIMITER).trim();
            ExceptionChecker.checkEmptyString(description);
            return description;
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return e.getMessage();
        }
    }
}


