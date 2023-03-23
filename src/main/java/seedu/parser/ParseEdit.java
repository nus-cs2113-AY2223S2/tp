package seedu.parser;

import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.ExpenditureList;
import java.time.format.DateTimeParseException;

public class ParseEdit {
    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    public static final String ASLASH = "a/";
    public static final String SSLASH = "s/";
    public static final String BSLASH = "b/";
    public static final String NSLASH = "n/";
    private final String userInput;

    public ParseEdit(String userInput) {
        this.userInput = userInput;
    }

    public Command editItem() {
        try {
            String displayIndexVal = ParseIndividualValue.parseIndividualValue(userInput, BLANK, DSLASH);
            int targetIndex = Integer.parseInt(displayIndexVal) - ExpenditureList.LIST_OFFSET;
            return new EditCommand(targetIndex, userInput);
        } catch (NumberFormatException | DateTimeParseException e) {
            return new InvalidCommand(
                    "Input command cannot be recognised as it is in the wrong format. Please try again");
        } catch (StringIndexOutOfBoundsException | EmptyStringException s) {
            return new InvalidCommand("Some inputs are missing! Please try again");
        }
    }
}
