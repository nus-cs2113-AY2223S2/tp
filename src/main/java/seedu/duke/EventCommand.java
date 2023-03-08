package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class EventCommand {
    private String eventName;
    private int budget;
    private int expense;

    public EventCommand(String eventName, int budget, int expense) {
        this.eventName = eventName;
        this.budget = budget;
        this.expense = expense;
        addEvent();
    }

    private void addEvent() {
        Event event = new Event(eventName, budget, expense);
        System.out.println("Please select the category you want to add the event to: ");
        String userInput;
        Scanner in;
        in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!isChooseCategorySuccessful(userInput)) {
            userInput = in.nextLine();
            System.out.println("Please try again");
        }
    }

    private void addEventToCategory(int categoryPosition, Event event) {
        Category category = CategoryList.getCategory(categoryPosition - 1);
        category.addEvent(event);
        System.out.println("New event added: " + event.getDescription());
    }

    private boolean isChooseCategorySuccessful(String userInput) {
        try {
            int categoryPosition = Integer.parseInt(userInput);
            testCategoryNumber(categoryPosition);
            addEventToCategory(categoryPosition, new Event(eventName, budget, expense));
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        } catch (InvalidCategoryNumberException e) {
            System.out.println("The category number you entered is out of range");
        } catch (Exception e) {
            System.out.println("Something went wrong, please report to the developer");
        }
        return false;
    }

    private void testCategoryNumber(int categoryPosition) throws InvalidCategoryNumberException {
        if (categoryPosition > CategoryList.categories.size() || categoryPosition <= 0) {
            throw new InvalidCategoryNumberException();
        }
    }
}
