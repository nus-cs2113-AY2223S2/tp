package seedu.parser;

import seedu.commands.Command;
import seedu.commands.DeleteCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.ExpenditureList;

import static seedu.ui.ErrorMessages.ERROR_NUMBER_FORMAT_MESSAGE;

public class ParseDelete {
    public static final String BLANK = "";
    private final String userInput;

    public ParseDelete(String userInput) {
        this.userInput = userInput;
    }

    public Command deleteItem() {
        try {
            // Removes empty space from the user input
            String details = ParseIndividualValue.parseIndividualValue(userInput, BLANK, BLANK);

            // Converts from string to int for comparison
            int posToDelete = Integer.parseInt(details) - ExpenditureList.LIST_OFFSET;
            return new DeleteCommand(posToDelete);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand(ERROR_NUMBER_FORMAT_MESSAGE.toString());
        } catch (StringIndexOutOfBoundsException | EmptyStringException s) {
            return new InvalidCommand(s.getMessage());
        }
    }
}
