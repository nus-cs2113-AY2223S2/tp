package seedu.parser;

import seedu.commands.Command;
import seedu.commands.InvalidCommand;
import seedu.commands.SortCommand;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.InvalidSortInputException;

public class ParseSort {
    public static final String BLANK = "";
    private final String userInput;

    public ParseSort(String userInput) {
        this.userInput = userInput;
    }

    public Command sortExpenditures() {
        try {
            String details = ParseIndividualValue.parseIndividualValue(userInput, BLANK, BLANK);
            if (!details.equals(SortCommand.AMOUNT_ASCENDING) && !details.equals(SortCommand.AMOUNT_DESCENDING)) {
                throw new InvalidSortInputException();
            }
            return new SortCommand(details);
        } catch (StringIndexOutOfBoundsException | EmptyStringException | InvalidSortInputException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
