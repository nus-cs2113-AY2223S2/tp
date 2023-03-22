package chching.record;

/**
 * Models an abstract class that act as a template for Expense and Income
 */
public abstract class Record {
    protected String description;
    protected String date;
    protected double value;

    public Record(){
    }

    /**
     * Constructor template for Expense and Income classes
     *
     * @param description       String description
     * @param date      String of the date
     * @param value        value of the income/expense
     */
    public Record(String description, String date, double value) {
        this.description = description;
        this.date = date;
        this.value = value;

    }

    public String getCategory() {
        return "";
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    public String toString() {
        return "";
    }
}
