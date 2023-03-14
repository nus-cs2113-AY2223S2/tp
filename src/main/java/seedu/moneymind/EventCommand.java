package seedu.moneymind;

import java.util.Scanner;

/**
 * A class to add an event
 */
public class EventCommand {
    public static final String SELECTING_CATEGORY_MESSAGE = "Please select the category you want to add the event to: ";
    public static final String GO_BACK_MESSAGE = "Please enter back to go back to the main program";
    public static final String BACK = "back";
    public static final String EVENT_ADDED_MESSAGE = "New event added: ";
    public static final String REMINDING_MESSAGE_TO_GIVE_A_NUMBER = "Please enter a number.";
    public static final String CATEGORY_OUT_OF_RANGE = "The category number you entered is out of range";
    public static final String SUBTLE_BUG_MESSAGE = "Something went wrong, please report to the developer";
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
        addEvent();
    }

    private void addEvent() {
        Event event = new Event(eventName, budget, expense);
        System.out.println(SELECTING_CATEGORY_MESSAGE);
        String userInput;
        Scanner in;
        in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!isChooseCategorySuccessful(userInput)) {
            System.out.println(GO_BACK_MESSAGE);
            userInput = in.nextLine();
            if (userInput.equals(BACK)) {
                break;
            }
        }
        in.close();
    }

    /**
     * Add an event to a category.
     */
    private void addEventToCategory(int categoryPosition, Event event) {
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
}
