package seedu.parser;

import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.ExpenditureList;
import java.time.format.DateTimeParseException;

import static seedu.ui.ErrorMessages.ERROR_WRONG_FORMAT_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_NUMBER_FORMAT_MESSAGE;
import static seedu.ui.ErrorMessages.ERROR_EMPTY_STRING_MESSAGE;

public class ParseEdit {
    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    private final String userInput;

    public ParseEdit(String userInput) {
        this.userInput = userInput;
    }

    public Command editItem() {
        try {
            // Removes empty space from the user input
            String displayIndexVal = ParseIndividualValue.parseIndividualValue(userInput, BLANK, DSLASH);
            // Converts from string to int for comparison
            int targetIndex = Integer.parseInt(displayIndexVal) - ExpenditureList.LIST_OFFSET;
            return new EditCommand(targetIndex, userInput);
        } catch (NumberFormatException | DateTimeParseException e) {
            return new InvalidCommand(ERROR_WRONG_FORMAT_MESSAGE.toString());
        } catch (StringIndexOutOfBoundsException s) {
            return new InvalidCommand(ERROR_NUMBER_FORMAT_MESSAGE.toString());
        } catch (EmptyStringException a) {
            return new InvalidCommand(ERROR_EMPTY_STRING_MESSAGE.toString());
        }
    }
}
