package seedu.moneymind.category;

import seedu.moneymind.event.Event;

import java.util.ArrayList;
import static seedu.moneymind.string.Strings.NO_EVENTS_IN_THIS_CATEGORY_MESSAGE;

public class Category {
    public ArrayList<Event> events = new ArrayList<>();

    private String name;

    /**
     * A constructor with name.
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the category.
     *
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Add the event to the list.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Delete the event from the list.
     */
    public void deleteEvent(int index) {
        events.remove(index);
    }

    /**
     * Gets the list of events.
     */
    public void viewEventList() {
        if (events.size() == 0) {
            System.out.println(NO_EVENTS_IN_THIS_CATEGORY_MESSAGE);
            return;
        }
        for (int i = 0; i < events.size(); i++) {
            System.out.println(i + 1 + ". " + events.get(i).toString());
        }
    }

    /**
     * to edit the parameters of the event.
     *
     * @param index the index of the event in the list
     * @param description the description of the event
     * @param budget the budget of the event
     */
    public void editEvent(int index, String description, int budget) {
        events.get(index).setDescription(description);
        events.get(index).setBudget(budget);
    }

    /**
     * to edit the parameters of the event.
     *
     * @param index the index of the event in the list
     * @param description the description of the event
     * @param budget the budget of the event
     * @param expense the expense of the event
     */
    public void editEvent(int index, String description, int budget, int expense) {
        events.get(index).setDescription(description);
        events.get(index).setBudget(budget);
        events.get(index).setExpense(expense);
    }

    /**
     * Gets the total expense of the category.
     *
     * @return the total expense of the category
     */
    public int getTotalBudget() {
        int totalBudget = 0;
        for (int i = 0; i < events.size(); i++) {
            totalBudget += events.get(i).getBudget();
        }
        return totalBudget;
    }

    /**
     * Gets the total expense of the category.
     *
     * @return the total expense of the category
     */
    public int getTotalExpense() {
        int totalExpense = 0;
        for (int i = 0; i < events.size(); i++) {
            totalExpense += events.get(i).getExpense();
        }
        return totalExpense;
    }

    /**
     * Gets the remaining budget of the category.
     *
     * @return the remaining budget of the category
     */
    public int getRemainingBudget() {
        return getTotalBudget() - getTotalExpense();
    }
}
