package seedu.parser;

import seedu.commands.Command;
import seedu.commands.DuplicateCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.ExpenditureList;

public class ParseDuplicate {
    public static final String BLANK = "";
    private final String userInput;

    public ParseDuplicate(String userInput) {
        this.userInput = userInput;
    }

    public Command duplicateItem() {
        try {
            String details = ParseIndividualValue.parseIndividualValue(userInput, BLANK, BLANK);
            int posToDuplicate = Integer.parseInt(details) - ExpenditureList.LIST_OFFSET;
            return new DuplicateCommand(posToDuplicate);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand("Input parameter for duplicate is not in correct number format!");
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
