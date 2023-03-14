package seedu.parser;

import seedu.commands.Command;
import seedu.commands.DeleteCommand;
import seedu.commands.InvalidCommand;

public class ParseDelete {
    private final String userInput;

    public static final String BLANK = "";

    public ParseDelete(String userInput) {
        this.userInput = userInput;
    }
    public Command deleteItem() {
        try {
            String details = ParseIndividualValue.parseIndividualValue(userInput, BLANK , BLANK);
            System.out.println(userInput);
            int posToDelete = Integer.parseInt(details);
            System.out.println(posToDelete);
            return new DeleteCommand(posToDelete);
        } catch (NumberFormatException numberFormatException) {
            // posToDelete is not a proper int
            return new InvalidCommand("Invalid");
        }
    }
}
