package seedu.parser;

import seedu.commands.Command;
import seedu.commands.LendExpenditureCommand;
import seedu.commands.BorrowExpenditureCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.ui.ErrorMessages.*;

public class ParseLendBorrow {
    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    public static final String ASLASH = "a/";
    public static final String SSLASH = "s/";
    public static final String BSLASH = "b/";
    public static final String NSLASH = "n/";
    private final String userInput;
    public ParseLendBorrow(String userInput) {
        this.userInput = userInput;
    }

    /**
     * @author itszhixuan
     */
    public Command addItem(String command) throws NotPositiveValueException, InvalidDateException {
        try {
            // Format: category d/date, n/name, a/amount, b/deadline, s/description
            LocalDate date = fetchDate();
            String name = fetchName();
            double amount = fetchDouble();
            LocalDate deadline = fetchDeadline();
            String descriptionVal = fetchDescription();
            ExceptionChecker.checkDate(date, deadline);
            
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
        }  catch (StringIndexOutOfBoundsException n) {
            return new InvalidCommand(ERROR_NUMBER_FORMAT_MESSAGE.toString());
        } catch (EmptyStringException e) {
            return new InvalidCommand(ERROR_EMPTY_STRING_MESSAGE.toString());
        } catch (InvalidDeadlineException | SmallAmountException | InvalidCharacterInAmount e) {
            return new InvalidCommand(e.getMessage());
        } catch (NotPositiveValueException p) {
            return new InvalidCommand(ERROR_NOT_POSITIVE_VALUE_MESSAGE.toString());
        }
    }

    public String fetchDescription() throws EmptyStringException, StringIndexOutOfBoundsException {
        return ParseIndividualValue.parseIndividualValue(userInput, SSLASH, BLANK);
    }

    public String fetchName() throws EmptyStringException, StringIndexOutOfBoundsException {
        return ParseIndividualValue.parseIndividualValue(userInput,NSLASH, ASLASH);
    }

    public double fetchDouble() throws InvalidCharacterInAmount, EmptyStringException,
            StringIndexOutOfBoundsException, SmallAmountException, NotPositiveValueException, NumberFormatException {
        String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH, BSLASH);
        ExceptionChecker.checkValidDoubleInput(amountVal);
        double amount = Double.parseDouble(amountVal);
        ExceptionChecker.checkValidAmount(amount);
        return Double.parseDouble(amountVal);
    }

    public LocalDate fetchDate() throws EmptyStringException, StringIndexOutOfBoundsException,
            DateTimeParseException {
        String dateVal = ParseIndividualValue.parseIndividualValue(userInput,DSLASH, NSLASH);
        return LocalDate.parse(dateVal);
    }

    public LocalDate fetchDeadline() throws EmptyStringException, StringIndexOutOfBoundsException,
            DateTimeParseException {
        String dateVal = ParseIndividualValue.parseIndividualValue(userInput, BSLASH, SSLASH);
        return LocalDate.parse(dateVal);
    }
}


