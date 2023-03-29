package entity;

public class Deadline {
    public String description;
    protected String dueDate;

    /**
     * Creates a deadline item with the deadline description and deadline timing passed through.
     *
     * @param description the description of deadline item.
     * @param dueDate     the deadline of the deadline item.
     */
    public Deadline(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * Returns a String of the deadline and due date for the deadline item in a special format.
     *
     * @return the string
     */
    public String toString() {
        return this.description + " by: " + this.dueDate;
    }

    public String savableString() {
        return this.description + "~|~" + this.dueDate;
    }
}
