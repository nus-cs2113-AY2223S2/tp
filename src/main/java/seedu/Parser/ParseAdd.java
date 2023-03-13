package seedu.Parser;

import seedu.commands.Command;
import seedu.commands.AcademicExpenditureCommand;
import seedu.commands.AccommodationExpenditureCommand;
import seedu.commands.EntertainmentExpenditureCommand;
import seedu.commands.FoodExpenditureCommand;
import seedu.commands.OtherExpenditureCommand;
import seedu.commands.TransportExpenditureCommand;
import seedu.commands.TuitionExpenditureCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.ExceptionChecker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ParseAdd {
    private final String userInput;

    public ParseAdd(String userInput) {
        this.userInput = userInput;
    }

    public Command addItem(String command) {

        try {
        // Format: category d/date, a/amount, s/description
        double amount;
        LocalDate date;
        String descriptionVal;

        descriptionVal = fetchDescriptionInput();
        amount = Double.parseDouble(fetchAmountInput());
        date = LocalDate.parse(fetchDateInput());

        switch (command) {
        case AcademicExpenditureCommand.COMMAND_WORD:
            return new AcademicExpenditureCommand(descriptionVal, amount, date);
        case AccommodationExpenditureCommand.COMMAND_WORD:
            return new AccommodationExpenditureCommand(descriptionVal, amount, date);
        case EntertainmentExpenditureCommand.COMMAND_WORD:
            return new EntertainmentExpenditureCommand(descriptionVal, amount, date);
        case FoodExpenditureCommand.COMMAND_WORD:
            return new FoodExpenditureCommand(descriptionVal, amount, date);
        case OtherExpenditureCommand.COMMAND_WORD:
            return new OtherExpenditureCommand(descriptionVal, amount, date);
        case TransportExpenditureCommand.COMMAND_WORD:
            return new TransportExpenditureCommand(descriptionVal, amount, date);
        case TuitionExpenditureCommand.COMMAND_WORD:
            return new TuitionExpenditureCommand(descriptionVal, amount, date);
        default:
            return new InvalidCommand("Invalid");
        }
        } catch (NumberFormatException n) {
            return new InvalidCommand("number format problem");
        } catch (DateTimeParseException d) {
            return new InvalidCommand("date time error");
        }
    }

    public String fetchDateInput() {
        try {
            int positionOfDSlash = userInput.indexOf("d/");
            int positionOfASlash = userInput.indexOf("a/");
            String date = userInput.substring(positionOfDSlash + 2, positionOfASlash).trim();
            ExceptionChecker.checkEmptyString(date);
            return date;
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return e.getMessage();
        }
    }

    public String fetchAmountInput() {
        try {
            int positionOfASlash = userInput.indexOf("a/");
            int positionOfSSlash = userInput.indexOf("s/");
            String amount = userInput.substring(positionOfASlash + 2, positionOfSSlash).trim();
            ExceptionChecker.checkEmptyString(amount);
            return amount;
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return e.getMessage();
        }
    }

    public String fetchDescriptionInput() {
        try {
            int positionOfSSlash = userInput.indexOf("s/");
            String description = userInput.substring(positionOfSSlash + 2).trim();
            ExceptionChecker.checkEmptyString(description);
            return description;
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return e.getMessage();
        }
    }
}
