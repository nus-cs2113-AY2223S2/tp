package seedu.parser;

import seedu.commands.Command;
import seedu.commands.InvalidCommand;
import seedu.commands.MarkCommand;
import seedu.commands.UnmarkCommand;
import seedu.exceptions.EmptyStringException;
import seedu.expenditure.ExpenditureList;

public class ParseMark {
    public static final String BLANK = "";
    private final String userInput;

    public ParseMark(String userInput) {
        this.userInput = userInput;
    }

    public Command markExpenditure() {
        try {
            String details = ParseIndividualValue.parseIndividualValue(userInput, BLANK, BLANK);
            int posToMark = Integer.parseInt(details) - ExpenditureList.LIST_OFFSET;
            return new MarkCommand(posToMark);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand("Index to be marked must be an integer and within bounds! Please try again");
        } catch (StringIndexOutOfBoundsException | EmptyStringException s) {
            return new InvalidCommand(s.getMessage());
        }
    }

    public Command unmarkExpenditure() {
        try {
            String details = ParseIndividualValue.parseIndividualValue(userInput, BLANK, BLANK);
            int posToUnMark = Integer.parseInt(details) - ExpenditureList.LIST_OFFSET;
            return new UnmarkCommand(posToUnMark);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand("Index to be unmarked must be an integer and within bounds! Please try again");
        } catch (StringIndexOutOfBoundsException | EmptyStringException s) {
            return new InvalidCommand(s.getMessage());
        }
    }
}
