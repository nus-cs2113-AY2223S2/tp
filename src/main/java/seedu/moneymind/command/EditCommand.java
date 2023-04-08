package seedu.moneymind.command;

import seedu.moneymind.Moneymind;
import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.exceptions.IntegerOverflowException;
import seedu.moneymind.exceptions.NegativeNumberException;
import seedu.moneymind.ui.Ui;

import static seedu.moneymind.string.Strings.EDIT_EXPENSE_LIMIT_MESSAGE;
import static seedu.moneymind.string.Strings.EDIT_BUDGET_LIMIT_MESSAGE;
import static seedu.moneymind.string.Strings.SUBTLE_BUG_MESSAGE;
import static seedu.moneymind.string.Strings.BACK;
import static seedu.moneymind.string.Strings.ENTERING_NON_NEGATIVE_NUMBER_MESSAGE;
import static seedu.moneymind.string.Strings.NEGATIVE_INTEGER_DETECTING_REGEX;
import static seedu.moneymind.string.Strings.INTEGER_DETECTING_REGEX;
import static seedu.moneymind.string.Strings.NON_EXISTENT_EVENT;
import static seedu.moneymind.string.Strings.NO_CATEGORY_MESSAGE;
import static seedu.moneymind.string.Strings.EMPTY_STRING;

/**
 * Edits the budget of a category or expense of an event.
 */
public class EditCommand implements Command {
    private boolean isEvent;
    private String categoryName;
    private int eventIndex;
    private String userInput;
    private boolean isReady = true;
    private int categoryIndex;

    /**
     * Constructs a new EditCommand object for editing an event.
     *
     * @param categoryName The name of the category.
     * @param eventIndex The index of the event.
     */
    public EditCommand (String categoryName, int eventIndex) {
        this.isEvent = true;
        this.categoryName = categoryName;
        this.eventIndex = eventIndex;
    }

    /**
     * Constructs a new EditCommand object for editing a category.
     *
     * @param categoryName The name of the category.
     */
    public EditCommand (String categoryName) {
        this.isEvent = false;
        this.categoryName = categoryName;
    }

    /**
     * Checks whether the category and event to be edited exist.
     */
    private void prepareEditEvent() {
        if (CategoryCommand.categoryMap.get(categoryName) == null) {
            System.out.println(NO_CATEGORY_MESSAGE);
            isReady = false;
            return;
        }
        categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        Category category = CategoryList.categories.get(categoryIndex);
        if (eventIndex >= category.getEvents().size()) {
            System.out.println(NON_EXISTENT_EVENT);
            isReady = false;
            return;
        }
        if (isReady) {
            String eventName = category.getEvents().get(eventIndex).getDescription();
            System.out.println("The current event expense for " + eventName + " is: " +
                    category.getEvents().get(eventIndex).getExpense());
            System.out.println("Your new expense would be:");
        }
    }

    /**
     * Checks whether the category to be edited exists.
     */
    private void prepareEditCategory() {
        if (CategoryCommand.categoryMap.get(categoryName) == null) {
            System.out.println(NO_CATEGORY_MESSAGE);
            isReady = false;
            return;
        }
        categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        Category category = CategoryList.categories.get(categoryIndex);
        if (isReady) {
            System.out.println("The current budget for " + categoryName + " is: " +
                    category.getBudget());
            System.out.println("Your new budget would be:");
        }
    }

    /**
     * Check if the user input is valid for editing an expense or a budget.
     *
     * @param userInput The user input.
     * @return True if the user input is valid, false otherwise.
     */
    private boolean isEditSuccessful(String userInput) {
        try {
            checkNegative(userInput);
            checkAmountUnderLimit(userInput);
            checkNumber(userInput);
            return true;
        } catch (IntegerOverflowException error) {
            if (isEvent) {
                System.out.println(EDIT_EXPENSE_LIMIT_MESSAGE);
            } else {
                System.out.println(EDIT_BUDGET_LIMIT_MESSAGE);
            }
        } catch (NumberFormatException | NegativeNumberException error) {
            System.out.println(ENTERING_NON_NEGATIVE_NUMBER_MESSAGE);
        } catch (Exception error) {
            System.out.println(SUBTLE_BUG_MESSAGE);
        }
        return false;
    }

    private void checkNumber(String newAmount) throws NumberFormatException {
        Integer.parseInt(newAmount);
    }

    private void checkNegative(String newAmount) throws NegativeNumberException {
        if (newAmount.matches(NEGATIVE_INTEGER_DETECTING_REGEX)) {
            throw new NegativeNumberException();
        }
    }

    private void checkAmountUnderLimit(String newAmount) throws IntegerOverflowException {
        if (newAmount.matches(INTEGER_DETECTING_REGEX) && newAmount.length() > 9) {
            throw new IntegerOverflowException();
        }
    }

    @Override
    public void execute(Ui ui) {
        if (isEvent) {
            prepareEditEvent();
        } else {
            prepareEditCategory();
        }
        if (!isReady) {
            return;
        }
        getUserInputUntilNonEmpty();
        while (!isEditSuccessful(userInput)) {
            getUserInputUntilNonEmpty();
            if (userInput.equals(BACK)) {
                break;
            }
        }
        if (!userInput.equals(BACK)) {
            if (isEvent) {
                System.out.println("Ok, the new expense is now changed to: " + userInput);
                CategoryList.categories.get(categoryIndex).getEvents().
                        get(eventIndex).setExpense(Integer.parseInt(userInput));
            } else {
                System.out.println("Ok, the new budget is now changed to: " + userInput);
                CategoryList.categories.get(categoryIndex).setBudget(Integer.parseInt(userInput));
            }
        }

    }

    private void getUserInputUntilNonEmpty() {
        do {
            userInput = Moneymind.in.nextLine();
        } while (userInput.equals(EMPTY_STRING));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
