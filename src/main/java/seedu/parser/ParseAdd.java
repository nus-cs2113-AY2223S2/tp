package seedu.parser;

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
import seedu.exceptions.NotPositiveValueException;
import seedu.exceptions.SmallAmountException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.ui.ErrorMessages.ERROR_COMMAND_NOT_RECOGNISED_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_EMPTY_STRING_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_NUMBER_FORMAT_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_NOT_POSITIVE_VALUE_MESSAGE;

public class ParseAdd {
    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    public static final String ASLASH = "a/";
    public static final String SSLASH = "s/";
    private final String userInput;

    public ParseAdd(String userInput) {
        this.userInput = userInput;
    }

    public Command addItem(String command) throws NotPositiveValueException {

        try {
            // Format: category d/date, a/amount, s/description
            String descriptionVal = ParseIndividualValue.parseIndividualValue(userInput, SSLASH, BLANK);
            String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH, SSLASH);
            double amount = Double.parseDouble(amountVal);
            ExceptionChecker.checkValidAmount(amount);
            String dateVal = ParseIndividualValue.parseIndividualValue(userInput, DSLASH, ASLASH);
            LocalDate date = LocalDate.parse(dateVal);

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
                return new InvalidCommand(ERROR_COMMAND_NOT_RECOGNISED_MESSAGE.toString());
            }

        } catch (NumberFormatException n) {
            return new InvalidCommand(ERROR_NUMBER_FORMAT_MESSAGE.toString());
        } catch (DateTimeParseException | SmallAmountException e) {
            return new InvalidCommand(e.getMessage());
        } catch (StringIndexOutOfBoundsException | EmptyStringException s) {
            return new InvalidCommand(ERROR_EMPTY_STRING_MESSAGE.toString());
        } catch (NotPositiveValueException p) {
            return new InvalidCommand(ERROR_NOT_POSITIVE_VALUE_MESSAGE.toString());
        }
    }
}
