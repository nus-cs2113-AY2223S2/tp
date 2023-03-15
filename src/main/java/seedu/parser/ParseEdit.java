package seedu.parser;

import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.ExpenditureList;

import java.time.format.DateTimeParseException;

import java.time.LocalDate;

public class ParseEdit {
    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    public static final String ASLASH = "a/";
    public static final String SSLASH = "s/";
    private final String userInput;

    public ParseEdit(String userInput) {
        this.userInput = userInput;
    }

    public Command editItem() {
        // Format: edit, index, d/date, a/amount, s/description
        try {
            String displayIndexVal = ParseIndividualValue.parseIndividualValue(userInput, BLANK, DSLASH);
            String dateVal = ParseIndividualValue.parseIndividualValue(userInput, DSLASH, ASLASH);
            String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH, SSLASH);
            String descriptionVal = ParseIndividualValue.parseIndividualValue(userInput, SSLASH, BLANK);
            int targetIndex = Integer.parseInt(displayIndexVal) - ExpenditureList.LIST_OFFSET;
            LocalDate date = LocalDate.parse(dateVal);
            return new EditCommand(targetIndex, date, amountVal, descriptionVal);
        } catch (NumberFormatException | DateTimeParseException e) {
            return new InvalidCommand(
                    "Input command cannot be recognised as it is in the wrong format. Please try again");
        } catch (StringIndexOutOfBoundsException | EmptyStringException s) {
            return new InvalidCommand(s.getMessage());
        }
    }
}
