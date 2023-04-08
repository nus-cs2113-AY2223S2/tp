package seedu.moneymind.category;

import seedu.moneymind.event.Event;

import java.util.ArrayList;
import static seedu.moneymind.string.Strings.NO_EVENTS_IN_THIS_CATEGORY_MESSAGE;
import static seedu.moneymind.string.Strings.DOT;

/**
 * Represents a category.
 */
public class Category {

    public ArrayList<Event> events = new ArrayList<>();
    private String name;
    private int budget;

    /**
     * A constructor with name.
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * A constructor with name and budget.
     */
    public Category(String name, int budget) {
        this.name = name;
        this.budget = budget;
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
     * Gets the list of events.
     */
    public void viewEventList() {
        if (events.size() == 0) {
            System.out.println(NO_EVENTS_IN_THIS_CATEGORY_MESSAGE);
            return;
        }
        for (int i = 0; i < events.size(); i++) {
            System.out.println(i + 1 + DOT + events.get(i).toString());
        }
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
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

}
