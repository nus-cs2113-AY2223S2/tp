package seedu.parser;

import seedu.commands.Command;
import seedu.commands.LendExpenditureCommand;
import seedu.commands.BorrowExpenditureCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.ExceptionChecker;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ParseLendBorrow {
    private final String userInput;

    private static final String BLANK = "";
    private static final String DSLASH = "d/";
    private static final String ASLASH = "a/";
    private static final String SSLASH = "s/";
    private static final String BSLASH = "b/";
    private static final String NSLASH = "n/";

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
                return new InvalidCommand("Invalid");
            }
        } catch (NumberFormatException n) {
            return new InvalidCommand("number format problem");
        } catch (DateTimeParseException d) {
            return new InvalidCommand("date time error");
        }
    }
}


