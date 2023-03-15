package seedu.parser;

import seedu.commands.Command;
import seedu.commands.LendExpenditureCommand;
import seedu.commands.BorrowExpenditureCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
                return new InvalidCommand("Invalid");
            }
        } catch (NumberFormatException n) {
            return new InvalidCommand("number format problem");
        } catch (DateTimeParseException d) {
            return new InvalidCommand("date time error");
        } catch (StringIndexOutOfBoundsException | EmptyStringException s) {
            return new InvalidCommand(s.getMessage());
        }
    }
}


