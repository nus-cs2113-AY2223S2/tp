package seedu.parser;

import seedu.commands.Command;
import seedu.commands.LendExpenditureCommand;
import seedu.commands.BorrowExpenditureCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.ui.ErrorMessages.ERROR_COMMAND_NOT_RECOGNISED_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_DATE_TIME_ERROR_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_NUMBER_FORMAT_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_EMPTY_STRING_MESSAGE;

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

    public Command addItem(String command) {
        try {
            // Format: category d/date, n/name, a/amount, b/deadline, s/description
            String descriptionVal = ParseIndividualValue.parseIndividualValue(userInput, SSLASH, BLANK);
            String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH, BSLASH);
            double amount = Double.parseDouble(amountVal);
            String name = ParseIndividualValue.parseIndividualValue(userInput,NSLASH, ASLASH);
            LocalDate lentDate = LocalDate.parse(ParseIndividualValue.parseIndividualValue(userInput,DSLASH, NSLASH));
            LocalDate deadline = LocalDate.parse(ParseIndividualValue.parseIndividualValue(userInput, BSLASH, SSLASH));

            switch (command) {
            case LendExpenditureCommand.COMMAND_WORD:
                return new LendExpenditureCommand(descriptionVal, name, amount, lentDate, deadline);
            case BorrowExpenditureCommand.COMMAND_WORD:
                return new BorrowExpenditureCommand(descriptionVal, name, amount, lentDate, deadline);
            default:
                return new InvalidCommand(ERROR_COMMAND_NOT_RECOGNISED_MESSAGE.toString());
            }
        } catch (DateTimeParseException d) {
            return new InvalidCommand(ERROR_DATE_TIME_ERROR_MESSAGE.toString());
        }  catch (NumberFormatException n) {
            return new InvalidCommand(ERROR_NUMBER_FORMAT_MESSAGE.toString());
        } catch (StringIndexOutOfBoundsException s) {
            return new InvalidCommand(ERROR_NUMBER_FORMAT_MESSAGE.toString());
        } catch (EmptyStringException e) {
            return new InvalidCommand(ERROR_EMPTY_STRING_MESSAGE.toString());
    }
 }

}


