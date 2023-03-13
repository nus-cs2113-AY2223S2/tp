package dinerDeadline;

public class Deadline {
    public String description;
    protected String dueDate;
  //  protected boolean isDone;

    /**
     * Creates a deadline item with the deadline description and deadline timing passed through.
     * @param description the description of deadline item.
     * @param dueDate the deadline of the deadline item.
     */
    public Deadline(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
      //  this.isDone = false;
    }

//    public String getStatusIcon() {
//        return (isDone ? "X" : " ");
//    }

    /**
     * Returns a String of the deadline and due date for the deadline item in a special format.
     * @return the string
     */
    public String toString() {
        //String status = getStatusIcon();
        return this.description + " by: " + this.dueDate;
    }
}
