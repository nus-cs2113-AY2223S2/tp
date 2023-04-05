package seedu.moneymind.command;

import seedu.moneymind.Moneymind;
import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.event.Event;
import seedu.moneymind.ui.Ui;
import static seedu.moneymind.string.Strings.NULL_EVENT_ASSERTION;
import static seedu.moneymind.string.Strings.NON_NEGATIVE_EXPENSE_ASSERTION;
import static seedu.moneymind.string.Strings.EVENT_ADDED_MESSAGE;
import static seedu.moneymind.string.Strings.SUBTLE_BUG_MESSAGE;
import static seedu.moneymind.string.Strings.SELECTING_CATEGORY_MESSAGE;
import static seedu.moneymind.string.Strings.GO_BACK_MESSAGE;
import static seedu.moneymind.string.Strings.BACK;
import static seedu.moneymind.string.Strings.CATEGORY_DOES_NOT_EXIST_MESSAGE;

/**
 * A class to add an event
 */
public class EventCommand implements Command {

    private String userInput;
    private String eventName;
    private String time;
    private int expense;

    /**
     * Constructor for EventCommand.
     *
     * @param eventName The name of the event.
     * @param expense The expense of the event.
     * @param time The time of the event.
     */
    public EventCommand(String eventName, int expense, String time) {
        this.eventName = eventName;
        this.expense = expense;
        this.time = time;
        assert eventName != null : NULL_EVENT_ASSERTION;
        assert expense >= 0 : NON_NEGATIVE_EXPENSE_ASSERTION;
    }

    /**
     * Constructor for EventCommand.
     *
     * @param eventName The name of the event.
     * @param expense The expense of the event.
     */
    public EventCommand(String eventName, int expense) {
        this.eventName = eventName;
        this.expense = expense;
        this.time = "";
        assert eventName != null : NULL_EVENT_ASSERTION;
        assert expense >= 0 : NON_NEGATIVE_EXPENSE_ASSERTION;
    }

    /**
     * Add an event to a category.
     */
    private void addEventToCategory(String categoryName, Event event) {
        int categoryPosition = CategoryCommand.categoryMap.get(categoryName);
        Category category = CategoryList.getCategory(categoryPosition);
        category.addEvent(event);
        System.out.println(EVENT_ADDED_MESSAGE + event.getDescription());
    }

    /**
     * Check if the user input is valid.
     *
     * @param userInput The user input.
     * @return True if the user input is valid.
     */
    private boolean isAddEventSuccessful(String userInput) {
        try {
            if (time == "") {
                addEventToCategory(userInput, new Event(eventName, expense));
            } else {
                addEventToCategory(userInput, new Event(eventName, expense, time));
            }
            return true;
        } catch (NullPointerException error) {
            System.out.println(CATEGORY_DOES_NOT_EXIST_MESSAGE);
        } catch (Exception error) {
            System.out.println(SUBTLE_BUG_MESSAGE);
        }
        return false;
    }

    @Override
    public void execute(Ui ui) {
        System.out.println(SELECTING_CATEGORY_MESSAGE);
        getUserInputUntilNonEmpty();
        while (!isAddEventSuccessful(userInput)) {
            System.out.println(GO_BACK_MESSAGE);
            getUserInputUntilNonEmpty();
            if (userInput.equals(BACK)) {
                break;
            }
        }
    }

    private void getUserInputUntilNonEmpty() {
        do {
            userInput = Moneymind.in.nextLine();
        } while (userInput.equals(""));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
