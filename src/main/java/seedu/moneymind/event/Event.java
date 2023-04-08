package seedu.moneymind.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static seedu.moneymind.UserDate.isValidDate;
import static seedu.moneymind.UserDate.getSystemDate;

public class Event {
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    private boolean isOneTimeExpense;
    private String description;
    private String time;
    private int expense;


    /**
     * A constructor with both description and budget.
     */
    public Event(String description, int expense) {
        this.description = description;
        this.expense = expense;
        LocalDateTime myDateObject = LocalDateTime.now();
        DateTimeFormatter myFormatObject = DateTimeFormatter.ofPattern(DATE_FORMAT);
        this.time = myDateObject.format(myFormatObject);
        this.isOneTimeExpense = true;
    }

    /**
     * A constructor with all parameters.
     */
    public Event(String description, int expense, String time) {
        this.description = description;
        this.expense = expense;
        this.time = time;
        this.isOneTimeExpense = false;
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
     * Gets the boolean value of isOneTimeExpense.
     * 
     * @return the boolean value of isOneTimeExpense
     */
    public boolean isOneTimeExpense() {
        return isOneTimeExpense;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event
     */
    public String toString() {
        if (isOneTimeExpense) {
            return description + " (expense: " + expense + ") (time added: " + time + ")";
        }
        return description + " (expense: " + expense + ") (start time: " + time + ")";
    }
}

