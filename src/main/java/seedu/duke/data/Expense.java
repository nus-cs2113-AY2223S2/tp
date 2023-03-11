package seedu.duke.data;

public class Expense {
    protected String description;
    protected String date; // to change to timestamp in the future
    protected float value;
    
    public Expense(String description, String date, float value) {
        this.description = description;
        this.date = date;
        this.value = value;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDate() {
        return date;
    }
    
    public float getValue() {
        return value;
    }
    
    public String toString() {
        return "Expense - " + getDescription() + " | Date - " + getDate() + " | value - " + getValue();
    }
}
