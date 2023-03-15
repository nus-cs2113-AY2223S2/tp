package seedu.parser;

import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.InvalidCommand;

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
        String indexVal = ParseIndividualValue.parseIndividualValue(userInput, BLANK, DSLASH);
        String dateVal = ParseIndividualValue.parseIndividualValue(userInput, DSLASH, ASLASH);
        String amountVal = ParseIndividualValue.parseIndividualValue(userInput, ASLASH, SSLASH);
        String descriptionVal = ParseIndividualValue.parseIndividualValue(userInput, SSLASH, BLANK);
        try {
            int indexIntVal = Integer.parseInt(indexVal);
            LocalDate date = LocalDate.parse(dateVal);
            return new EditCommand(indexIntVal, date, amountVal, descriptionVal);
        } catch (NumberFormatException | DateTimeParseException e) {
            return new InvalidCommand(
                    "Input command cannot be recognised as it is in the wrong format. Please try again");
        }
    }
}
