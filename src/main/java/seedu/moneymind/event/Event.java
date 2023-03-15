package seedu.moneymind.event;

public class Event {
    private static final int DEFAULT_EXPENSE = 0;
    private String description;
    private int budget;
    private int expense = DEFAULT_EXPENSE;

    /**
     * A constructor with both description and budget.
     */
    public Event(String description, int budget) {
        this.description = description;
        this.budget = budget;
    }

    /**
     * A constructor with all parameters.
     */
    public Event(String description, int budget, int expense) {
        this.description = description;
        this.budget = budget;
        this.expense = expense;
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
     * Gets the budget of the event.
     *
     * @return the budget of the event
     */
    public int getBudget() {
        return budget;
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
     * Sets the description of the event.
     *
     * @param description the description of the event
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the budget of the event.
     *
     * @param budget the budget of the event
     */
    public void setBudget(int budget) {
        this.budget = budget;
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
     * Checks if the event is over budget.
     *
     * @return true if the event is over budget, false otherwise
     */
    public boolean isOverBudget() {
        return expense > budget;
    }

    /**
     * Gets the remaining budget of the event.
     *
     * @return the remaining budget of the event
     */
    public int remainingBudget() {
        return budget - expense;
    }

    public String toString() {
        return description + " [budget]" + budget + " [expense]" + expense;
    }
}
