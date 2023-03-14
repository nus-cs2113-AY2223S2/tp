package seedu.moneymind;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class to parse the user input.
 */
public class Parser {
    public static final String WHITE_SPACE = " ";
    public static final String BYE = "bye";
    public static final String VIEW = "view";
    public static final String DELETE = "delete";
    public static final String EVENT = "event";
    public static final String CATEGORY = "category";
    public static final String INVALID_INPUT = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String DELETE_FORMAT = "Please following the correct format: " +
            "delete c/<category name> e/<event name>";
    public static final String REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY = "Remember do not leave any things " +
            "inside the brackets empty!";
    public static final String EMPTY_DELETION = "☹ OOPS!!! The description of a delete cannot be empty.";
    public static final String SUBTLE_BUG_MESSAGE = "☹ OOPS!!! Something went wrong, please report to the developer.";
    public static final String EVENT_REGEX = "(.+) b/(-?\\d+) e/(-?\\d+)";
    public static final String EVENT_FORMAT = "Please following the correct format: " +
            "event <event name> b/<budget> e/<expense>";
    public static final String EVENT_EMPTY = "☹ OOPS!!! The description of an event cannot be empty.";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_BUDGET_A_NUMBER =
            "☹ OOPS!!! The budget and expense must be a number.";
    public static final String CATEGORY_EMPTY = "☹ OOPS!!! The description of a category cannot be empty.";
    public static final String DELETE_REGEX = "^c/(?=\\S)(.*?)(?:\\s+e/(.*))?\\s*$";
    public static final String NULL_INPUT_ASSERTION = "Input cannot be null";
    public static final String NULL_DESCRIPTION = "Separated keyword and description cannot be null";
    public static final String REMINDING_MESSAGE_ABOUT_GIVING_POSITIVE_NUMBER =
            "Please enter a positive number for budget and expense";
    private String[] separatedKeywordAndDescription;
    private String keyword;

    /**
     * Splits the user input into keyword and description.
     */
    public void splitKeywordAndDescription(String input) {
        assert input != null : NULL_INPUT_ASSERTION;
        separatedKeywordAndDescription = input.split(WHITE_SPACE, 2);
        keyword = separatedKeywordAndDescription[0];
    }

    /**
     * Executes the user input.
     */
    public void executeUserInput() {
        assert separatedKeywordAndDescription != null : NULL_DESCRIPTION;
        switch (keyword) {
        case BYE:
            executeByeCommand();
            break;
        case VIEW:
            executeViewCommand();
            break;
        case DELETE:
            executeDeleteCommand();
            break;
        case EVENT:
            executeEventCommand();
            break;
        case CATEGORY:
            executeCategoryCommand();
            break;
        default:
            System.out.println(INVALID_INPUT);
            break;
        }
    }

    private void executeByeCommand() {
        new ByeCommand();
    }

    private void executeViewCommand() {
        if (separatedKeywordAndDescription.length == 1) {
            new ViewCommand();
        } else {
            new ViewCommand(separatedKeywordAndDescription[1]);
        }
    }

    private void executeDeleteCommand() {
        Pattern pattern = Pattern.compile(DELETE_REGEX);
        if (separatedKeywordAndDescription.length == 1) {
            System.out.println(EMPTY_DELETION);
            return;
        }
        Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);
        if (matcher.find()) {
            String categoryName = matcher.group(1);
            String eventName = matcher.group(2);
            if (eventName == null) {
                new DeleteCommand(categoryName);
                return;
            }
            new DeleteCommand(categoryName, eventName);
        } else {
            System.out.println(DELETE_FORMAT);
            System.out.println(REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
        }
    }

    private void executeEventCommand() {
        Pattern pattern = Pattern.compile(EVENT_REGEX);
        try {
            Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);
            if (matcher.find()) {
                String eventName = matcher.group(1);
                String budgetNumber = matcher.group(2);
                String expenseNumber = matcher.group(3);
                checkNegativeBudgetAndExpense(Integer.parseInt(budgetNumber), Integer.parseInt(expenseNumber));
                new EventCommand(eventName, Integer.parseInt(budgetNumber), Integer.parseInt(expenseNumber));
            } else {
                System.out.println(EVENT_FORMAT);
                System.out.println(REMINDING_MESSAGE_ABOUT_NOT_LETTING_EMPTY);
            }
        } catch (IndexOutOfBoundsException error) {
            System.out.println(EVENT_EMPTY);
        } catch (NumberFormatException error) {
            System.out.println(REMINDING_MESSAGE_ABOUT_GIVING_BUDGET_A_NUMBER);
        } catch (NegativeNumberException error) {
            System.out.println(REMINDING_MESSAGE_ABOUT_GIVING_POSITIVE_NUMBER);
        } catch (Exception error) {
            System.out.println(SUBTLE_BUG_MESSAGE);
        }
    }

    private void checkNegativeBudgetAndExpense(int budget, int expense) throws NegativeNumberException {
        if (budget < 0 || expense < 0) {
            throw new NegativeNumberException();
        }
    }

    private void executeCategoryCommand() {
        try {
            new CategoryCommand(separatedKeywordAndDescription[1]);
        } catch (IndexOutOfBoundsException error) {
            System.out.println(CATEGORY_EMPTY);
        }
    }

}
