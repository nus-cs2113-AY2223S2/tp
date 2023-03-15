package seedu.parser;

import seedu.commands.Command;
import seedu.commands.DeleteCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.ExpenditureList;

public class ParseDelete {
    public static final String BLANK = "";
    private final String userInput;

    public ParseDelete(String userInput) {
        this.userInput = userInput;
    }

    public Command deleteItem() {
        try {
            String details = ParseIndividualValue.parseIndividualValue(userInput, BLANK, BLANK);
            int posToDelete = Integer.parseInt(details) - ExpenditureList.LIST_OFFSET;
            return new DeleteCommand(posToDelete);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand("Invalid");
        } catch (StringIndexOutOfBoundsException | EmptyStringException s) {
            return new InvalidCommand(s.getMessage());
        }
    }
}
