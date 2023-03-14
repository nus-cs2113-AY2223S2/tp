package seedu.parser;

import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.InvalidCommand;
import seedu.exceptions.EmptyStringException;
import seedu.exceptions.ExceptionChecker;

public class ParseEdit {
    private final String userInput;
    private static final int OFFSET_DELIMITER = 2;
    public ParseEdit(String userInput) {
        this.userInput = userInput;
    }

    public Command editItem() {
        // Format: edit, index, d/date, a/amount, s/description
        System.out.println(userInput);
        String indexVal = fetchInput("", "d/");
        System.out.println(indexVal);
        String dateVal = fetchInput("d/", "a/");
        String amountVal = fetchInput("a/", "s/");
        String descriptionVal = fetchInput("s/", "");
        System.out.println(dateVal);
        System.out.println(amountVal);
        System.out.println(descriptionVal);

        try {
            int indexIntVal = Integer.parseInt(indexVal);
            return new EditCommand(indexIntVal, dateVal, amountVal, descriptionVal);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand("Invalid");
        }
    }

    public String fetchInput(String front, String back) {
        try {
            int positionOfFirstSlash;
            if (!front.equals("")) {
                positionOfFirstSlash = userInput.indexOf(front);
            } else {
                positionOfFirstSlash = -OFFSET_DELIMITER;
            }
            int positionOfSecondSlash;
            if (!back.equals("")) {
                positionOfSecondSlash = userInput.indexOf(back);
            } else {
                positionOfSecondSlash = userInput.length();
            }
            String value = userInput.substring(positionOfFirstSlash + OFFSET_DELIMITER, positionOfSecondSlash).trim();
            ExceptionChecker.checkEmptyString(value);
            return value;
        } catch (StringIndexOutOfBoundsException | EmptyStringException e) {
            return e.getMessage();
        }
    }
}
