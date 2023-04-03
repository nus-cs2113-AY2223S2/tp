package seedu.parser;

import seedu.commands.Command;
import seedu.commands.DuplicateCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.ExpenditureList;

import static seedu.ui.ErrorMessages.ERROR_NUMBER_FORMAT_MESSAGE;

public class ParseDuplicate {
    public static final String BLANK = "";
    private final String userInput;

    public ParseDuplicate(String userInput) {
        this.userInput = userInput;
    }

    public Command duplicateItem() {
        try {
            // Removes empty space from the user input
            String details = ParseIndividualValue.parseIndividualValue(userInput, BLANK, BLANK);
            // Converts from string to int for comparison
            int posToDuplicate = Integer.parseInt(details) - ExpenditureList.LIST_OFFSET;
            return new DuplicateCommand(posToDuplicate);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand(ERROR_NUMBER_FORMAT_MESSAGE.toString());
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
