package seedu.moneymind.event;

import static seedu.moneymind.UserDate.isValidDate;
import static seedu.moneymind.UserDate.getSystemDate;

public class Event {
    private static final String INVALID_DATE_MESSAGE = "Invalid date format. Using system date instead.";
    private static final int DEFAULT_EXPENSE = 0;
    private String description;
    private String time;
    private int expense = DEFAULT_EXPENSE;


    /**
     * A constructor with both description and budget.
     */
    public Event(String description, int expense) {
        this.description = description;
        this.expense = expense;
    }

    /**
     * A constructor with all parameters. 
     * If the time is not valid, the system date will be used.
     */
    public Event(String description, int expense, String time) {
        this.description = description;
        this.expense = expense;
        if (isValidDate(time)) {
            this.time = time;
        } else {
            System.out.println(INVALID_DATE_MESSAGE);
            this.time = getSystemDate();
        }
    }

    /**
     * Gets the description of the event.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the expense of the event.
     *
     * @return the expense of the event
     */
    public int getExpense() {
        return expense;
    }


    /**
     * Sets the expense of the event.
     *
     * @param expense the expense of the event
     */
    public void setExpense(int expense) {
        this.expense = expense;
    }

    /**
     * Sets the time of the event.
     * If the time is not valid, the system date will be used.
     *
     * @param time the time of the event
     */
    public String setTime(String time) {
        if (isValidDate(time)) {
            this.time = time;
        } else {
            this.time = getSystemDate();
        }
        return this.time;
    }

    /**
     * Gets the time of the event.
     *
     * @return the time of the event
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event
     */
    public String toString() {
        if (time == null) {
            return description + " (expense: " + expense + ")";
        }
        return description + " (expense: " + expense + ") (time: " + time + ")";
    }
}
