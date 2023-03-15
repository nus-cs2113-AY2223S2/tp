package seedu.moneymind.command;

import seedu.moneymind.Moneymind;
import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.event.Event;
import seedu.moneymind.exceptions.InvalidCategoryNumberException;
import seedu.moneymind.ui.Ui;
import static seedu.moneymind.string.Strings.NULL_EVENT_ASSERTION;
import static seedu.moneymind.string.Strings.NON_NEGATIVE_BUDGET_ASSERTION;
import static seedu.moneymind.string.Strings.NON_NEGATIVE_EXPENSE_ASSERTION;
import static seedu.moneymind.string.Strings.NON_NEGATIVE_POSITION_ASSERTION;
import static seedu.moneymind.string.Strings.EVENT_ADDED_MESSAGE;
import static seedu.moneymind.string.Strings.REMINDING_MESSAGE_TO_GIVE_A_NUMBER;
import static seedu.moneymind.string.Strings.CATEGORY_OUT_OF_RANGE;
import static seedu.moneymind.string.Strings.SUBTLE_BUG_MESSAGE;
import static seedu.moneymind.string.Strings.SELECTING_CATEGORY_MESSAGE;
import static seedu.moneymind.string.Strings.GO_BACK_MESSAGE;
import static seedu.moneymind.string.Strings.BACK;

/**
 * A class to add an event
 */
public class EventCommand implements Command {

    private String eventName;
    private int budget;
    private int expense;

    /**
     * Constructor for EventCommand.
     *
     * @param eventName The name of the event.
     * @param budget The budget of the event.
     * @param expense The expense of the event.
     */
    public EventCommand(String eventName, int budget, int expense) {
        this.eventName = eventName;
        this.budget = budget;
        this.expense = expense;
        assert eventName != null : NULL_EVENT_ASSERTION;
        assert budget >= 0 : NON_NEGATIVE_BUDGET_ASSERTION;
        assert expense >= 0 : NON_NEGATIVE_EXPENSE_ASSERTION;
    }

    /**
     * Add an event to a category.
     */
    private void addEventToCategory(int categoryPosition, Event event) {
        assert categoryPosition > 0 : NON_NEGATIVE_POSITION_ASSERTION;
        Category category = CategoryList.getCategory(categoryPosition - 1);
        category.addEvent(event);
        System.out.println(EVENT_ADDED_MESSAGE + event.getDescription());
    }

    /**
     * Check if the user input is valid.
     *
     * @param userInput The user input.
     * @return True if the user input is valid.
     */
    private boolean isChooseCategorySuccessful(String userInput) {
        try {
            int categoryPosition = Integer.parseInt(userInput);
            testCategoryNumber(categoryPosition);
            addEventToCategory(categoryPosition, new Event(eventName, budget, expense));
            return true;
        } catch (NumberFormatException e) {
            System.out.println(REMINDING_MESSAGE_TO_GIVE_A_NUMBER);
        } catch (InvalidCategoryNumberException e) {
            System.out.println(CATEGORY_OUT_OF_RANGE);
        } catch (Exception e) {
            System.out.println(SUBTLE_BUG_MESSAGE);
        }
        return false;
    }

    /**
     * Check if the category number is valid.
     *
     * @param categoryPosition The category number.
     * @throws InvalidCategoryNumberException If the category number is invalid.
     */
    private void testCategoryNumber(int categoryPosition) throws InvalidCategoryNumberException {
        if (categoryPosition > CategoryList.categories.size() || categoryPosition <= 0) {
            throw new InvalidCategoryNumberException();
        }
    }

    @Override
    public void execute(Ui ui) {
        Event event = new Event(eventName, budget, expense);
        System.out.println(SELECTING_CATEGORY_MESSAGE);
        String userInput;
        userInput = Moneymind.in.nextLine();
        while (!isChooseCategorySuccessful(userInput)) {
            System.out.println(GO_BACK_MESSAGE);
            userInput = Moneymind.in.nextLine();
            if (userInput.equals(BACK)) {
                break;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
