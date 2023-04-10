package seedu.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import seedu.commands.Command;
import seedu.commands.LendExpenditureCommand;
import seedu.commands.BorrowExpenditureCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.ExceptionChecker;
import seedu.exceptions.SmallAmountException;
import seedu.exceptions.InvalidCharacterInAmount;
import seedu.exceptions.NotPositiveValueException;
import seedu.exceptions.DateLimitException;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.InvalidDeadlineException;
import seedu.exceptions.InvalidDateException;
import seedu.exceptions.WrongPrecisionException;
import seedu.exceptions.LargeValueException;
import static seedu.ui.ErrorMessages.ERROR_DATE_TIME_ERROR_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_COMMAND_NOT_RECOGNISED_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_AMOUNT_FORMAT_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_NUMBER_FORMAT_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_EMPTY_STRING_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_NOT_POSITIVE_VALUE_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_INVALID_AMOUNT_PRECISION;
import static seedu.ui.ErrorMessages.ERROR_INVALID_AMOUNT_TOO_LARGE;

public class ParseLendBorrow {
    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    public static final String ASLASH = "a/";
    public static final String PSLASH = "p/";
    public static final String BSLASH = "b/";
    public static final String NSLASH = "n/";
    public static final String DOT = ".";
    private final String userInput;

    public ParseLendBorrow(String userInput) {
        this.userInput = userInput;
    }

    /**
     * @author itszhixuan
     * @throws DateLimitException
     */
    public Command addItem(String command) throws NotPositiveValueException, InvalidDateException, DateLimitException {
        try {
            // Format: category d/date, n/name, a/amount, b/deadline, s/description

            LocalDate date = fetchDate();
            String name = fetchName();
            double amount = fetchDouble();
            LocalDate deadline = fetchDeadline();
            String descriptionVal = fetchDescription();
            ExceptionChecker.checkDate(date, deadline);
            ExceptionChecker.checkDateLimit(date);

            // Differentiates between lend and borrow
            switch (command) {
            case LendExpenditureCommand.COMMAND_WORD:
                return new LendExpenditureCommand(descriptionVal, name, amount, date, deadline);
            case BorrowExpenditureCommand.COMMAND_WORD:
                return new BorrowExpenditureCommand(descriptionVal, name, amount, date, deadline);
            default:
                return new InvalidCommand(ERROR_COMMAND_NOT_RECOGNISED_MESSAGE.toString());
            }

        } catch (DateTimeParseException d) {
            return new InvalidCommand(ERROR_DATE_TIME_ERROR_MESSAGE.toString());
        } catch (NumberFormatException n) {
            return new InvalidCommand(ERROR_AMOUNT_FORMAT_MESSAGE.toString());
        } catch (StringIndexOutOfBoundsException n) {
            return new InvalidCommand(ERROR_NUMBER_FORMAT_MESSAGE.toString());
        } catch (EmptyStringException e) {
            return new InvalidCommand(ERROR_EMPTY_STRING_MESSAGE.toString());
        } catch (InvalidDeadlineException | SmallAmountException | InvalidCharacterInAmount | InvalidDateException e) {
            return new InvalidCommand(e.getMessage());
        } catch (NotPositiveValueException p) {
            return new InvalidCommand(ERROR_NOT_POSITIVE_VALUE_MESSAGE.toString());
        } catch (LargeValueException l) {
            return new InvalidCommand(ERROR_INVALID_AMOUNT_TOO_LARGE.toString());
        } catch (WrongPrecisionException e) {
            return new InvalidCommand(ERROR_INVALID_AMOUNT_PRECISION.toString());
        }
    }

    public String fetchDescription() throws EmptyStringException, StringIndexOutOfBoundsException {
        // Extracts the fields from user input
        return ParseIndividualValue.parseIndividualValue(userInput, PSLASH, BLANK);
    }

    public String fetchName() throws EmptyStringException, StringIndexOutOfBoundsException {
        // Extracts the fields from user input
        return ParseIndividualValue.parseIndividualValue(userInput, NSLASH, ASLASH);
    }

    public double fetchDouble() throws InvalidCharacterInAmount, EmptyStringException,
            StringIndexOutOfBoundsException, SmallAmountException, NotPositiveValueException, NumberFormatException,
            WrongPrecisionException, LargeValueException {
        // Converts from string to double for numerical addition functionalities
        String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH, BSLASH);
        ExceptionChecker.checkIfMoreThanTwoDecimalPlaces(amountVal, DOT, BLANK);
        ExceptionChecker.checkValidDoubleInput(amountVal);
        double amount = Double.parseDouble(amountVal);
        ExceptionChecker.checkValidAmount(amount);
        ExceptionChecker.checkLargeValue(amount);
        return Double.parseDouble(amountVal);
    }

    public LocalDate fetchDate() throws EmptyStringException, StringIndexOutOfBoundsException,
            DateTimeParseException {
        // Converts from string to date to fit Command class
        String dateVal = ParseIndividualValue.parseIndividualValue(userInput, DSLASH, NSLASH);
        return LocalDate.parse(dateVal);
    }

    public LocalDate fetchDeadline() throws EmptyStringException, StringIndexOutOfBoundsException,
            DateTimeParseException {
        // Converts from string to date to fit Command class
        String dateVal = ParseIndividualValue.parseIndividualValue(userInput, BSLASH, PSLASH);
        return LocalDate.parse(dateVal);
    }
}
