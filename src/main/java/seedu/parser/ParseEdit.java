package seedu.parser;

import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.InvalidCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ParseEdit {
    private final String userInput;
    private static final String BLANK = "";
    private static final String DSLASH = "d/";
    private static final String ASLASH = "a/";
    private static final String SSLASH = "s/";
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
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand("Input command in wrong format!");
        }
    }
}
