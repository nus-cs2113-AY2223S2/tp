package seedu.moneymind.command;

import seedu.moneymind.Moneymind;
import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.exceptions.NegativeNumberException;
import seedu.moneymind.ui.Ui;

import static seedu.moneymind.command.DeleteCommand.NON_EXISTENT_EVENT;
import static seedu.moneymind.string.Strings.SUBTLE_BUG_MESSAGE;
import static seedu.moneymind.string.Strings.REMINDING_MESSAGE_TO_GIVE_A_NUMBER;
import static seedu.moneymind.string.Strings.NO_CATEGORY_MESSAGE;
import static seedu.moneymind.string.Strings.GO_BACK_MESSAGE;
import static seedu.moneymind.string.Strings.BACK;

public class EditCommand implements Command {
    private String categoryName;
    private int eventIndex;
    private boolean isReady = false;
    private int categoryIndex;
    public EditCommand (String categoryName, int eventIndex) {
        this.categoryName = categoryName;
        this.eventIndex = eventIndex;
    }

    private void editEvent() {
        if (CategoryCommand.categoryMap.get(categoryName) == null) {
            System.out.println(NO_CATEGORY_MESSAGE);
            return;
        }
        categoryIndex = CategoryCommand.categoryMap.get(categoryName);
        Category category = CategoryList.categories.get(categoryIndex);
        if (eventIndex >= category.getEvents().size()) {
            System.out.println(NON_EXISTENT_EVENT);
            return;
        }
        String eventName = category.getEvents().get(eventIndex).getDescription();
        System.out.println("The current event expense for " + eventName + " is: " +
                category.getEvents().get(eventIndex).getExpense());
        System.out.println("Your new expense would be: ");
        isReady = true;
    }

    private void checkNegative(int newExpense) throws NegativeNumberException {
        if (newExpense < 0) {
            throw new NegativeNumberException();
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
            int newExpense = Integer.parseInt(userInput);
            checkNegative(newExpense);
            return true;
        } catch (NumberFormatException error) {
            System.out.println(REMINDING_MESSAGE_TO_GIVE_A_NUMBER);
        } catch (NegativeNumberException error) {
            System.out.println();
        } catch (Exception error) {
            System.out.println(SUBTLE_BUG_MESSAGE);
        }
        return false;
    }

    @Override
    public void execute(Ui ui) {
        editEvent();
        if (isReady) {
            String userInput;
            userInput = Moneymind.in.nextLine();
            while (!isEditSuccessful(userInput)) {
                System.out.println(GO_BACK_MESSAGE);
                userInput = Moneymind.in.nextLine();
                if (userInput.equals(BACK)) {
                    break;
                }
            }
            System.out.println("Ok, the new expense is now changed to: " + userInput);
            CategoryList.categories.get(categoryIndex).getEvents().
                    get(eventIndex).setExpense(Integer.parseInt(userInput));
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
