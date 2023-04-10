package seedu.parser;

import seedu.commands.Command;
import seedu.commands.FindCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;

public class ParseFind {
    public static final String BLANK = "";
    private final String userInput;

    public ParseFind(String userInput) {
        this.userInput = userInput;
    }

    public Command findExpenditure() {
        try {
            String keyword = ParseIndividualValue.parseIndividualValue(userInput, BLANK, BLANK);
            return new FindCommand(keyword);
        } catch (StringIndexOutOfBoundsException | EmptyStringException s) {
            return new InvalidCommand(s.getMessage());
        }
    }

}
