package seedu.moneymind.Command;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.moneymind.Strings.*;

/**
 * A class to parse the user input.
 */
public class Parser {
    /**
     * Parses the next line and returns a command object, or throws.
     */
    public Command parseNextCommand(String input) throws Exception {
        String[] separatedKeywordAndDescription = input.split(WHITE_SPACE, 2);
        String keyword = separatedKeywordAndDescription[0];

        switch (keyword) {
        case BYE:
            return createByeCommand();
        case VIEW:
            return createViewCommand(separatedKeywordAndDescription);
        case DELETE:
            return createDeleteCommand(separatedKeywordAndDescription);
        case EVENT:
            return createEventCommand(separatedKeywordAndDescription);
        case CATEGORY:
            return createCategoryCommand(separatedKeywordAndDescription);
        default:
            throw new InvalidCommandException(INVALID_INPUT);
        }
    }

    private Command createByeCommand() {
        return new ByeCommand();
    }

    private Command createViewCommand(String[] separatedKeywordAndDescription) {
        if (separatedKeywordAndDescription.length == 1) {
            return new ViewCommand();
        } else {
            return new ViewCommand(separatedKeywordAndDescription[1]);
        }
    }

    private Command createDeleteCommand(String[] separatedKeywordAndDescription) throws Exception {
        if (separatedKeywordAndDescription[1].startsWith("c/")) {
            try {
                String afterCategorySpecifier = separatedKeywordAndDescription[1].substring(2);
                Pattern pattern = Pattern.compile("(.+) e/(.+)");
                Matcher matcher = pattern.matcher(afterCategorySpecifier);
                if (matcher.find()) {
                    String categoryName = matcher.group(1);
                    String eventName = matcher.group(2);
                    return new DeleteCommand(categoryName, eventName);
                } else {
                    System.out.println(afterCategorySpecifier);
                    return new DeleteCommand(afterCategorySpecifier);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidCommandException(DELETE_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
            } catch (Exception e) {
                throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
            }
        } else {
            throw new InvalidCommandException(DELETE_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        }
    }

    private Command createEventCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        Pattern pattern = Pattern.compile(EVENT_REGEX);
        try {
            Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);
            if (matcher.find()) {
                String eventName = matcher.group(1);
                String budgetNumber = matcher.group(2);
                String expenseNumber = matcher.group(3);
                return new EventCommand(eventName, Integer.parseInt(budgetNumber), Integer.parseInt(expenseNumber));
            } else {
                throw new InvalidCommandException(EVENT_FORMAT + "\n" + REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(EVENT_EMPTY);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(REMINDING_MESSAGE_ABOUT_GIVING_BUDGET_A_NUMBER);
        } catch (Exception e) {
            throw new InvalidCommandException(SUBTLE_BUG_MESSAGE);
        }
    }

    private Command createCategoryCommand(String[] separatedKeywordAndDescription) throws InvalidCommandException {
        try {
            return new CategoryCommand(separatedKeywordAndDescription[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException(CATEGORY_EMPTY);
        }
    }
}
