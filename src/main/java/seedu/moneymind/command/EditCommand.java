package seedu.moneymind.command;

import seedu.moneymind.Moneymind;
import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.exceptions.IntegerOverflowException;
import seedu.moneymind.exceptions.NegativeNumberException;
import seedu.moneymind.ui.Ui;

import static seedu.moneymind.command.DeleteCommand.NON_EXISTENT_EVENT;
import static seedu.moneymind.string.Strings.SUBTLE_BUG_MESSAGE;
import static seedu.moneymind.string.Strings.ENTERING_POSITIVE_NUMBER_MESSAGE;
import static seedu.moneymind.string.Strings.NO_CATEGORY_MESSAGE;
import static seedu.moneymind.string.Strings.BACK;
import static seedu.moneymind.string.Strings.EXPENSE_LIMIT_MESSAGE;
import static seedu.moneymind.string.Strings.BUDGET_LIMIT_MESSAGE;

public class EditCommand implements Command {
    private boolean isEvent;
    private String categoryName;
    private int eventIndex;
    private String userInput;
    private boolean isReady = true;
    private int categoryIndex;

    public EditCommand (String categoryName, int eventIndex) {
        this.isEvent = true;
        this.categoryName = categoryName;
        this.eventIndex = eventIndex;
    }

    public EditCommand (String categoryName) {
        this.isEvent = false;
        this.categoryName = categoryName;
    }

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

    private void checkNegative(int newExpense) throws NegativeNumberException {
        if (newExpense < 0) {
            throw new NegativeNumberException();
        }
    }

    private void checkAmountUnderLimit(String amount) throws IntegerOverflowException {
        if (amount.length() > 9) {
            throw new IntegerOverflowException();
        }
    }

    /**
     * Check if the user input is valid.
     *
     * @param userInput The user input.
     * @return True if the user input is valid.
     */
    private boolean isEditSuccessful(String userInput) {
        try {
            checkAmountUnderLimit(userInput);
            int newAmount = Integer.parseInt(userInput);
            checkNegative(newAmount);
            return true;
        }
        catch (IntegerOverflowException error) {
            if (isEvent) {
                System.out.println(EXPENSE_LIMIT_MESSAGE);
            } else {
                System.out.println(BUDGET_LIMIT_MESSAGE);
            }
        } catch (NumberFormatException | NegativeNumberException error) {
            System.out.println(ENTERING_POSITIVE_NUMBER_MESSAGE);
        } catch (Exception error) {
            System.out.println(SUBTLE_BUG_MESSAGE);
        }
        return false;
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
        } while (userInput.equals(""));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
